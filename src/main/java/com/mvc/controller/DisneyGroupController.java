package com.mvc.controller;

import com.mvc.dao.GroupInfoDao;
import com.mvc.dao.UserInfoDao;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;
import com.mvc.service.DisneyJoinGroupValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class DisneyGroupController {
    private final String service = "DISNEY";

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private GroupInfoDao groupInfoDao;

    @Autowired
    private DisneyJoinGroupValidatorService disneyValidator;

    @RequestMapping(value = "/groupInfo/disney/{id}", method = RequestMethod.GET)
    public String group(@PathVariable("id") int id, Model model, Principal principal) {
        try {
            int iduser = userInfoDao.getUserIdByUsername(principal.getName());
            GroupInfo groupInfo = disneyValidator.CheckUserQualityService(iduser, id);
            // userCanJoin = 0;
            // userOfAnotherGroup = 1;
            // groupFull = 2;
            // groupNull = 3;
            // userOfGroup = 4;
            if (groupInfo.getTag() == 0) {
                model.addAttribute("checkNum", 1);// group ton tai
                model.addAttribute("code", 300);// user can join

                model.addAttribute("groupName", groupInfo.getGroupname());
                model.addAttribute("service", groupInfo.getService());
                model.addAttribute("idUser", iduser);
                model.addAttribute("idGroup", id);
                model.addAttribute("formGroupUser", new Groups_Users());
                model.addAttribute("username", principal.getName());
                model.addAttribute("quantity", groupInfo.getQuantity());
                return "disneyGroupPage";
            }
            if (groupInfo.getTag() == 1) {
                model.addAttribute("checkNum", 1);// group ton tai
                model.addAttribute("code", 200);// user cua gr khac
                model.addAttribute("groupName", groupInfo.getGroupname());
                model.addAttribute("service", groupInfo.getService());
                model.addAttribute("username", principal.getName());
                model.addAttribute("quantity", groupInfo.getQuantity());
                return "disneyGroupPage";
            }
            if (groupInfo.getTag() == 2) {
                model.addAttribute("checkNum", 1);// group ton tai
                model.addAttribute("code", 400);// group full
                model.addAttribute("groupName", groupInfo.getGroupname());
                model.addAttribute("service", groupInfo.getService());
                model.addAttribute("username", principal.getName());
                model.addAttribute("quantity", groupInfo.getQuantity());
                return "disneyGroupPage";
            }
            if (groupInfo.getTag() == 3) {
                return "403page";
            }
            if (groupInfo.getTag() == 4) {
                model.addAttribute("checkNum", 1);// group ton tai
                model.addAttribute("code", 100);// user cua gr
                model.addAttribute("groupName", groupInfo.getGroupname());
                model.addAttribute("service", groupInfo.getService());
                model.addAttribute("idgroup", id);
                model.addAttribute("username", principal.getName());
                model.addAttribute("quantity", groupInfo.getQuantity());
                return "disneyGroupPage";
            }

            return "redirect:/403";
        } catch (Exception e) {
            return "redirect:/403";
        }

    }
}
