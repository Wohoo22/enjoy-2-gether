package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dao.UserInfoDao;
import com.mvc.ultis.BcryptEncoder;
import com.mvc.ultis.RandomVerifyCode;
import com.mvc.ultis.SendEmail;

@Controller
public class ForgetPasswordController {

	@Autowired
	private UserInfoDao userInfoDao;

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String getEmailForgetPassword() {
		return "formEmailForgetPassword";
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public String sendVerifyCode(@RequestParam String email, Model model) {
		// check email co ton tai k
		try {
			// co ton tai
			String userEmail = userInfoDao.getUserEmailByEmail(email);
			System.out.println(userEmail);

			// send verify code
			RandomVerifyCode rand = new RandomVerifyCode();
			int verifyCode = rand.randomCode();
			SendEmail sendEmail = new SendEmail();
			sendEmail.sendEmail(userEmail, verifyCode);

			// xoa verifycode cu
			userInfoDao.truncateEmailVerifyCodeTable();

			// save email-verifycode vao database
			userInfoDao.saveEmailAndVerifyCode(userEmail, verifyCode);

			model.addAttribute("email", userEmail);
			return "formCheckVerifyCode";

		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "⚠️ EMAIL KHÔNG TỒN TẠI ! ");
			return "formEmailForgetPassword";

		}
	}

	@RequestMapping(value = "/checkVerifyCode", method = RequestMethod.GET)
	public String checkVerifyCode(@RequestParam int code, @RequestParam String email, Model model) {
		int verifyCodeDatabase = userInfoDao.getVerifyCodeOfEmailByEmail(email);

		if (verifyCodeDatabase == code) {
			// ok confirm dc rui
			model.addAttribute("code", code);
			model.addAttribute("email", email);
			// truyen vao de dung tiep
			return "changePassword";
		} else {
			model.addAttribute("message", "⚠️ SAI MÃ KHÔI PHỤC !");
			model.addAttribute("email", email);
			return "formCheckVerifyCode";
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(@RequestParam int code, @RequestParam String email, @RequestParam String newPassword) {
		int verifyCodeDatabase = userInfoDao.getVerifyCodeOfEmailByEmail(email);
		if (verifyCodeDatabase == code) {
			BcryptEncoder bcryptEncoder = new BcryptEncoder();
			userInfoDao.updatePasswordByUserEmail(bcryptEncoder.BcryptEncoder(newPassword), email);
			userInfoDao.truncateEmailVerifyCodeTable();
			return "loginPage";
		} else {
			return "403page";
		}
	}

}
