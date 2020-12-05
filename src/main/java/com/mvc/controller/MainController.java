package com.mvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.dao.GroupInfoDao;
import com.mvc.dao.UserInfoDao;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;
import com.mvc.model.UserInfo;
import com.mvc.model.UserRegisterForm;

@Controller
public class MainController {

	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private GroupInfoDao groupInfoDao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String welcomePage(Model model, Principal principal) {
		try {
			model.addAttribute("username", principal.getName());
		} catch (Exception e) {
		}
		return "homePage";
	}

	@RequestMapping(value = { "/info" }, method = RequestMethod.GET)
	public String infoPage(Model model, Principal principal) {
		try {
			model.addAttribute("username", principal.getName());
		} catch (Exception e) {
		}
		return "infoPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, Principal principal) {
		if (principal != null) {
			return "redirect:/userInfo";
		}
		return "loginPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {
		return "403page";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model, Principal principal) {
		if (principal != null) {
			return "redirect:/userInfo";
		}
		UserRegisterForm userRegisterForm = new UserRegisterForm();
		model.addAttribute("form", userRegisterForm);
		return "registerPage";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, @ModelAttribute("form") @Validated UserRegisterForm userRegisterForm,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult error");
			return "registerPage";
		}
		String formUsername = userRegisterForm.getUsername().replace("<", "&lt;").replace(">", "&gt;")
				.replace("'", "&#39;").replace("''", "&#34;");
		String username = userInfoDao.getUsernameByUsername(formUsername);
		if (username != null) {
			model.addAttribute("message", "⚠️ TÊN NGƯỜI DÙNG ĐÃ TỒN TẠI");
			model.addAttribute("alert", 1);
			return "registerPage";
		}
		String email = userInfoDao.getUserEmailByEmail(userRegisterForm.getEmail());
		if (email != null) {
			model.addAttribute("message", "⚠️ EMAIL ĐÃ TỒN TẠI");
			model.addAttribute("alert", 1);
			return "registerPage";
		}
		if (!this.emailValidator.isValid(userRegisterForm.getEmail())) {
			model.addAttribute("message", "⚠️ EMAIL KHÔNG HỢP LỆ");
			model.addAttribute("alert", 1);
			return "registerPage";
		}
		if (!userRegisterForm.getConfirmPassword().equals(userRegisterForm.getPassword())) {
			model.addAttribute("message", "⚠️ HAI MẬT KHẨU KHÔNG TRÙNG NHAU");
			model.addAttribute("alert", 1);
			return "registerPage";
		}

		try {
			UserInfo user1 = new UserInfo(formUsername, userRegisterForm.getPassword(), "USER",
					userRegisterForm.getEmail());
			userInfoDao.addUser(user1);
			return "redirect:/login?registered=true&email=" + user1.getEmail() + "&password=" + user1.getPassword();

		} catch (Exception e) {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Principal principal, Model model) {
		UserInfo user = userInfoDao.findUserInfo(principal.getName());
		model.addAttribute("username", user.getUserName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("balance", user.getBalance());
		return "userInfo";
	}

	@RequestMapping(value = "/userGroups", method = RequestMethod.GET)
	public String userGroups(Principal principal, Model model) {
		int iduser = userInfoDao.getUserIdByUsername(principal.getName());
		model.addAttribute("username", principal.getName());
		try {
			List<Groups_Users> groupList = groupInfoDao.getAllGroupOfUser(iduser);
			List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
			for (Groups_Users group_user : groupList) {
				GroupInfo gr = groupInfoDao.getGroup(group_user.getIdgroup());
				List<Groups_Users> userList = groupInfoDao.getAllGroupUsers(group_user.getIdgroup());
				int num = 0;
				for (int i = 0; i < userList.size(); i++) {
					num++;
				}
				gr.setMemberQuantity(num);
				groupInfoList.add(gr);
			}

			model.addAttribute("groupList", groupInfoList);
			return "userGroups";
		} catch (Exception e) {
			return "redirect:/groups/netflix";
		}
	}
}
