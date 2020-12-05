package com.mvc.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.dao.GroupInfoDao;
import com.mvc.dao.UserInfoDao;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;
import com.mvc.model.UserInfo;
import com.mvc.service.NetflixJoinGroupValidatorService;

@org.springframework.stereotype.Controller
public class NetflixGroupController {
	private final String service = "NETFLIX";

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private GroupInfoDao groupInfoDao;

	@Autowired
	private NetflixJoinGroupValidatorService netflixValidator;

	@RequestMapping(value = "/groupInfo/netflix/{id}", method = RequestMethod.GET)
	public String group(@PathVariable("id") int id, Model model, Principal principal) {
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());
			GroupInfo groupInfo = netflixValidator.CheckUserQualityService(iduser, id);
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
				return "netflixGroupPage";
			}
			if (groupInfo.getTag() == 1) {
				model.addAttribute("checkNum", 1);// group ton tai
				model.addAttribute("code", 200);// user cua gr khac
				model.addAttribute("groupName", groupInfo.getGroupname());
				model.addAttribute("service", groupInfo.getService());
				model.addAttribute("username", principal.getName());
				model.addAttribute("quantity", groupInfo.getQuantity());
				return "netflixGroupPage";
			}
			if (groupInfo.getTag() == 2) {
				model.addAttribute("checkNum", 1);// group ton tai
				model.addAttribute("code", 400);// group full
				model.addAttribute("groupName", groupInfo.getGroupname());
				model.addAttribute("service", groupInfo.getService());
				model.addAttribute("username", principal.getName());
				model.addAttribute("quantity", groupInfo.getQuantity());
				return "netflixGroupPage";
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
				return "netflixGroupPage";
			}

			return "redirect:/403";
		} catch (Exception e) {
			return "redirect:/403";
		}

	}

	@RequestMapping(value = "/groupInfo/netflix/{id}", method = RequestMethod.POST)
	public String addUserToGroup(Model model, @ModelAttribute("formGroupUser") @Validated Groups_Users formGroupUser,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult error");
			return "redirect:/403";
		}

		// userCanJoin = 0;
		// userOfAnotherGroup = 1;
		// groupFull = 2;
		// groupNull = 3;
		// userOfGroup = 4;

		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());
			// chek iduser, idgroup tren form co hop le khong
			if ((iduser == formGroupUser.getIduser()) && (id == formGroupUser.getIdgroup())) {
				// Hop le rui thi check quality
				GroupInfo group = netflixValidator.CheckUserQualityService(formGroupUser.getIduser(),
						formGroupUser.getIdgroup());
				if (group.getTag() == 0) {
					// add user to group
					groupInfoDao.addUserToGroup(formGroupUser.getIduser(), formGroupUser.getIdgroup());

					// update group quantity
					groupInfoDao.updateGroupMemberQuantity(groupInfoDao.getGroupMemberQuantity(id) + 1, id);

					// add date join group cho user
					userInfoDao.insertUserDateJoinGroup(formGroupUser.getIduser(), Date.valueOf(LocalDate.now()));

					String url = "/groupInfo/netflix/" + formGroupUser.getIdgroup();
					return "redirect:" + url;
				} else {
					return "redirect:/403";

				}
			} else {
				return "redirect:/403";
			}

		} catch (Exception e) {
			return "redirect:/403";
		}

	}

	@RequestMapping(value = "/groups/netflix", method = RequestMethod.GET)
	public String groups(Model model, Principal principal, @RequestParam int loadNum, @RequestParam int sort) {

		// sort: 1-memberQuantityIncrease
		// sort: 2-memberQuantityDecrease
		if (sort == 1) {
			List<GroupInfo> groupList = groupInfoDao.getAllNetflixGroupWithIncreaseMemberQuantitySort(loadNum);
			model.addAttribute("groupList", groupList);
		} else if (sort == 2) {
			List<GroupInfo> groupList = groupInfoDao.getAllNetflixGroupWithDecreaseMemberQuantitySort(loadNum);
			model.addAttribute("groupList", groupList);
		} else {
			List<GroupInfo> groupList = groupInfoDao.getAllNetflixGroup(loadNum);
			model.addAttribute("groupList", groupList);
		}
		// xem user da o gr nao cung service chua
		// de hien thi button tao group hoac vao group cua ban
		int iduser = userInfoDao.getUserIdByUsername(principal.getName());
		int checkNum = netflixValidator.CheckIfUserHadBeenInAGroupWithSameService(iduser, service);

		// userNotInGroupWithSameService = 0
		// userInGroup w/ same service: return checkNum = idgroup
		if (checkNum == 0) {
			// tao nhom
			model.addAttribute("groupCheck", 0);
		} else {
			// vao gr cua ban
			model.addAttribute("groupCheck", -1);
			model.addAttribute("idgroupOfUser", checkNum);
		}

		model.addAttribute("sort", sort);
		model.addAttribute("loadNum", loadNum + 5);
		model.addAttribute("username", principal.getName());
		return "netflixGroupList";
	}

	@RequestMapping(value = "/groups/netflix/create", method = RequestMethod.GET)
	public String createGroup(Model model, Principal principal) {

		model.addAttribute("username", principal.getName());
		model.addAttribute("service", service);
		model.addAttribute("groupName", "GROUP_" + groupInfoDao.getNextAutoIncrementGroupId());
		return "netflixCreateGroup";
	}

	@RequestMapping(value = "/groups/netflix/create", method = RequestMethod.POST)
	public String addGroup(Principal principal) {
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());

			// check xem user da o group nao co cung service
			int checkNum = netflixValidator.CheckIfUserHadBeenInAGroupWithSameService(iduser, service);

			// userNotInGroupWithSameService = 0
			// userInGroup w/ same service: return checkNum = idgroup
			if (checkNum == 0) {
				int idgroup = groupInfoDao.getNextAutoIncrementGroupId();
				groupInfoDao.createGroup("GROUP_" + idgroup, service);

				// tao nhom xong add user vua tao vao nhom luon
				groupInfoDao.addUserToGroup(iduser, idgroup);

				// update group member quantity
				groupInfoDao.updateGroupMemberQuantity(groupInfoDao.getGroupMemberQuantity(idgroup) + 1, idgroup);

				return "redirect:/groupInfo/netflix/" + idgroup;

			} else {
				return "redirect:/403";
			}
		} catch (Exception e) {
			return "redirect:/403";
		}
	}
}
