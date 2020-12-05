package com.mvc.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.dao.AccountDao;
import com.mvc.dao.CommentDao;
import com.mvc.dao.GroupInfoDao;
import com.mvc.dao.PostDao;
import com.mvc.dao.UserInfoDao;
import com.mvc.model.Account;
import com.mvc.model.Comment;
import com.mvc.model.CreateAccountForm;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Accounts;
import com.mvc.model.Groups_Users;
import com.mvc.model.Post;
import com.mvc.model.UpdateOldAccountExpiredDate;

@Controller
public class AdminController {

	private final int netflixPrice = 75000;

	@Autowired
	private GroupInfoDao groupInfoDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private AccountDao accountDao;

	@RequestMapping(value = "/admin/groups/netflix", method = RequestMethod.GET)
	public String netflixGroups(Model model, Principal principal, @RequestParam int loadNum, @RequestParam int sort) {
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
		model.addAttribute("sort", sort);
		model.addAttribute("loadNum", loadNum + 5);
		return "adminNetflixGroups";
	}

	@RequestMapping(value = "/admin/inGroup/{id}", method = RequestMethod.GET)
	public String inGroup(@PathVariable("id") int id, Model model, Principal principal, @RequestParam int postNum) {

		// Posts and comments
		List<Post> postList = postDao.getGroupPosts(id, postNum);
		List<Comment> commentList = new ArrayList<Comment>();
		for (Post post : postList) {
			List<Comment> cmtList = commentDao.getPostComments(post.getIdpost());
			for (Comment comment : cmtList) {
				commentList.add(comment);
			}
		}

		// create post and comment
		Post postForm = new Post();
		Comment commentForm = new Comment();
		model.addAttribute("commentForm", commentForm);
		model.addAttribute("postForm", postForm);
		model.addAttribute("postList", postList);
		model.addAttribute("commentList", commentList);

		// user list
		List<Groups_Users> userList = groupInfoDao.getUsersOfGroupsWithNameAndBalance(id);
		model.addAttribute("userList", userList);

		// get service cua group dang dung
		String service = groupInfoDao.getGroupService(id);
		if (service.equals("NETFLIX")) {
			model.addAttribute("service", "NETFLIX");
			model.addAttribute("servicePrice", netflixPrice);
		}

		// get account cua group
		// attribute: 0 la co account, 1 la ko co account, 2 la account
		// het han
		try {
			Groups_Accounts ga = accountDao.getAccountDetailsOfGroupByGroupId(id);
			System.out.println(ga.getExpiredDate());
			if (ga.getExpiredDate().after(Date.valueOf(LocalDate.now()))) {
				model.addAttribute("haveAccount", 0);
				model.addAttribute("accountEmail", ga.getEmail());
				model.addAttribute("accountPassword", ga.getPassword());
				model.addAttribute("expiredDate", ga.getExpiredDate());
			} else {
				model.addAttribute("haveAccount", 2);
			}
		} catch (Exception e) {
			model.addAttribute("haveAccount", 1);
		}

		// so post muon load
		model.addAttribute("postNum", postNum + 5);
		model.addAttribute("username", principal.getName());
		return "adminInGroup";

	}

	@RequestMapping(value = "/admin/inGroup/{id}/submitPost", method = RequestMethod.POST)
	public String adminPost(Model model, @ModelAttribute("postForm") @Validated Post postForm,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		int iduser = userInfoDao.getUserIdByUsername(principal.getName());
		if (role.equals("ADMIN")) {
			postDao.mapPostIdWithGroupId(postDao.getNextAutoIncrementPostId(), id);
			postDao.mapPostIdWithUserId(postDao.getNextAutoIncrementPostId(), iduser);
			postDao.createPost(postForm.getPostDetail());
			return "redirect:/admin/inGroup/" + id + "?postNum=5";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/inGroup/{id}/{idpost}/submitComment", method = RequestMethod.POST)
	public String adminComment(Model model, @ModelAttribute("commentForm") @Validated Comment commentForm,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			@PathVariable("idpost") int idpost, Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		int iduser = userInfoDao.getUserIdByUsername(principal.getName());

		if (role.equals("ADMIN")) {
			int idcomment = commentDao.getNextAutoIncrementCommentId();
			commentDao.mapCommentIdWithPostId(idcomment, idpost);
			commentDao.mapCommentIdWithUserId(idcomment, iduser);
			commentDao.createComment(commentForm.getCommentDetail());
			return "redirect:/admin/inGroup/" + id + "?postNum=5";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/ingroup/{id}/{idpost}/deletePost", method = RequestMethod.POST)
	public String adminDeletePost(@PathVariable("idpost") int idpost, @PathVariable("id") int id, Principal principal,
			Model model) {

		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			// delete comments of post first
			List<Integer> commentIds = postDao.getAllCommentIdOfPost(idpost);
			for (Integer idcmt : commentIds) {
				commentDao.deleteCommentByCommentId(idcmt);
				commentDao.deleteCommentIdAndPostIdMap(idcmt);
				commentDao.deleteCommentIdAndUserIdMap(idcmt);
			}

			// delete post
			postDao.deletePost(idpost);
			postDao.deletePostIdAndGroupIdMap(idpost);
			postDao.deletePostIdAndUserIdMap(idpost);

			return "redirect:/admin/inGroup/" + id + "?postNum=5";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/ingroup/{id}/{idcomment}/deleteComment", method = RequestMethod.POST)
	public String adminDeleteComment(@PathVariable("idcomment") int idcomment, @PathVariable("id") int id,
			Principal principal, Model model) {

		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			commentDao.deleteCommentByCommentId(idcomment);
			commentDao.deleteCommentIdAndPostIdMap(idcomment);
			commentDao.deleteCommentIdAndUserIdMap(idcomment);
			return "redirect:/admin/inGroup/" + id + "?postNum=5";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/groups/netflix/insufficientBalance", method = RequestMethod.GET)
	public String getAllNetFlixGroupNotEnoughBalance(Model model, @RequestParam int sort) {
		List<GroupInfo> groupList = groupInfoDao.getAllNetflixGroupWithFull5Member();

		// add group chua du tien vao day
		List<Groups_Users> usersOfInsufficientGroups = new ArrayList<Groups_Users>();
		List<GroupInfo> insufficientGroups = new ArrayList<GroupInfo>();

		for (GroupInfo groupInfo : groupList) {
			// ta co list users voi ten va balance cua group o day
			List<Groups_Users> userList = groupInfoDao.getUsersOfGroupsWithNameAndBalance(groupInfo.getIdgroup());
			for (Groups_Users user : userList) {
				// chi can co it nhat 1 user chua du tien thui
				if (user.getBalance() < netflixPrice) {
					// get ca account cua group
					try {
						Groups_Accounts acc = accountDao.getAccountDetailsOfGroupByGroupId(groupInfo.getIdgroup());
						groupInfo.setAccount(new Account(acc.getEmail(), acc.getPassword(), acc.getExpiredDate()));
					} catch (Exception e) {

					}
					// add group k du tien
					insufficientGroups.add(groupInfo);
					// add users cua group k du tien
					for (Groups_Users users : userList) {
						usersOfInsufficientGroups.add(users);
					}
					break;
				}
			}
		}

		// add them date join group cua user vao moi user
		for (Groups_Users user : usersOfInsufficientGroups) {
			user.setDateJoinGroup(userInfoDao.getUserDateJoinGroup(user.getIduser()));
		}
		// 0-group chua co acc
		List<GroupInfo> groupsNoAcc = new ArrayList<GroupInfo>();
		for (GroupInfo group : insufficientGroups) {
			try {
				Groups_Accounts acc = accountDao.getAccountDetailsOfGroupByGroupId(group.getIdgroup());
				System.out.println(acc.getEmail() + acc.getPassword());
			} catch (Exception e) {
				// TODO: handle exception
				groupsNoAcc.add(group);
				continue;
			}
		}

		// 1-group da co acc nhung ma het han
		List<GroupInfo> groupsExpireAcc = new ArrayList<GroupInfo>();
		for (GroupInfo group : insufficientGroups) {
			try {
				Groups_Accounts acc = accountDao.getAccountDetailsOfGroupByGroupId(group.getIdgroup());
				System.out.println(acc.getEmail() + acc.getPassword());
				if (acc.getExpiredDate().before(Date.valueOf(LocalDate.now()))) {
					groupsExpireAcc.add(group);
				}
			} catch (Exception e) {
				continue;
			}
		}

		// 2-group da co acc chua het han (vua moi add acc/gia han acc xong)
		List<GroupInfo> groupsActiveAcc = new ArrayList<GroupInfo>();
		for (GroupInfo group : insufficientGroups) {
			try {
				Groups_Accounts acc = accountDao.getAccountDetailsOfGroupByGroupId(group.getIdgroup());
				System.out.println(acc.getEmail() + acc.getPassword());
				if (acc.getExpiredDate().after(Date.valueOf(LocalDate.now()))) {
					groupsActiveAcc.add(group);
				}
			} catch (Exception e) {
				continue;
			}
		}

		// 3-get all groups

		if (sort == 0) {// gr chua co acc
			model.addAttribute("groupList", groupsNoAcc);

		}
		if (sort == 1) {// da co acc nma het han
			model.addAttribute("groupList", groupsExpireAcc);

		}
		if (sort == 2) {// da co acc va chua het han (vua add xong)
			model.addAttribute("groupList", groupsActiveAcc);

		}
		if (sort == 3) {// get all
			model.addAttribute("groupList", insufficientGroups);
		}
		model.addAttribute("usersOfGroupsList", usersOfInsufficientGroups);
		return "adminNetflixGroupsWithInsufficientMoney";
	}

	@RequestMapping(value = "/admin/groups/netflix/sufficientBalance", method = RequestMethod.GET)
	public String getAllNetFlixGroupWithEnoughBalance(Model model) {
		List<GroupInfo> groupList = groupInfoDao.getAllNetflixGroupWithFull5Member();

		// check xong add group da du tien vao day
		List<GroupInfo> sufficientGroups = new ArrayList<GroupInfo>();
		List<Groups_Users> usersOfSufficientGroups = new ArrayList<Groups_Users>();

		for (GroupInfo group : groupList) {
			int i = 0;
			List<Groups_Users> userList = groupInfoDao.getUsersOfGroupsWithNameAndBalance(group.getIdgroup());
			for (Groups_Users user : userList) {
				if (user.getBalance() < netflixPrice) {
					i++;
				}
			}
			if (i == 0) {
				// get account cua gr neu co
				try {
					Groups_Accounts acc = accountDao.getAccountDetailsOfGroupByGroupId(group.getIdgroup());
					group.setAccount(new Account(acc.getEmail(), acc.getPassword(), acc.getExpiredDate()));
				} catch (Exception e) {

				}
				sufficientGroups.add(group);
				for (Groups_Users qualifiedUser : userList) {
					usersOfSufficientGroups.add(qualifiedUser);
				}
			}
		}

		// add them date join group cua user vao moi user
		for (Groups_Users user : usersOfSufficientGroups) {
			user.setDateJoinGroup(userInfoDao.getUserDateJoinGroup(user.getIduser()));
		}

		model.addAttribute("accountInfoForm", new CreateAccountForm());
		model.addAttribute("updateExpiredDateForm", new UpdateOldAccountExpiredDate());

		model.addAttribute("groupList", sufficientGroups);
		model.addAttribute("usersOfGroupsList", usersOfSufficientGroups);
		return "adminNetflixGroupsWithSufficientMoney";
	}

	@RequestMapping(value = "/admin/kickUserForm", method = RequestMethod.GET)
	public String formKickUser() {
		return "adminKickUser";
	}

	@RequestMapping(value = "/admin/kickUser", method = RequestMethod.GET)
	public String receiveKickUserRequest(@RequestParam int idgroup, @RequestParam String username, Model model,
			Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			int iduser = userInfoDao.getUserIdByUsername(username);
			if (iduser != 0) {
				groupInfoDao.deleteUserFromGroup(idgroup, iduser);
				int oldQuantity = groupInfoDao.getGroupMemberQuantity(idgroup);
				groupInfoDao.updateGroupMemberQuantity(oldQuantity - 1, idgroup);

				model.addAttribute("message", "da kick user: " + username + " out of group_" + idgroup);
				return "adminKickUser";
			} else {
				return "redirect:/403";
			}
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/addNewAccount/netflix", method = RequestMethod.POST)
	public String addNewAccountToGroup(Model model,
			@ModelAttribute("accountInfoForm") @Validated CreateAccountForm accountForm, BindingResult bindingResult,
			final RedirectAttributes redirectAttributes, Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			// get date sign va expire
			LocalDate signedDate = LocalDate.of(accountForm.getSignedYear(), accountForm.getSignedMonth(),
					accountForm.getSignedDay());
			LocalDate expiredDate = LocalDate.of(accountForm.getExpiredYear(), accountForm.getExpiredMonth(),
					accountForm.getExpiredDay());

			// add map trc
			accountDao.mapAccountIdWithGroupId(accountDao.getNextAutoIncrementAccountId(), accountForm.getIdgroup());

			// tao account moi trong dtbs
			Account acc = new Account();
			acc.setEmail(accountForm.getEmail());
			acc.setPassword(accountForm.getPassword());
			acc.setIdgroup(accountForm.getIdgroup());
			acc.setService("NETFLIX");
			acc.setSignedDate(Date.valueOf(signedDate));
			acc.setExpiredDate(Date.valueOf(expiredDate));
			accountDao.addNewAccountInfo(acc);

			// tru balance cua cac user trong group
			List<Groups_Users> userList = groupInfoDao.getUsersOfGroupsWithNameAndBalance(accountForm.getIdgroup());
			for (Groups_Users user : userList) {
				userInfoDao.updateUserBalanceByUsername(user.getBalance() - netflixPrice, user.getUsername());
			}

			return "redirect:/admin/groups/netflix/sufficientBalance";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/updateAccountExpiredDate/netflix", method = RequestMethod.POST)
	public String updateOldAccountExpiredDate(
			@ModelAttribute("updateExpiredDateForm") @Validated UpdateOldAccountExpiredDate form,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			LocalDate date = LocalDate.of(form.getYear(), form.getMonth(), form.getDay());
			accountDao.updateAccountExpiredDate(Date.valueOf(date), form.getEmail());

			// get idgroup cua account
			int idaccount = accountDao.getAccountIdByAccountEmail(form.getEmail());
			int idgroup = accountDao.getGroupIdByAccountId(idaccount);

			// tru balance cua cac user trong group
			List<Groups_Users> userList = groupInfoDao.getUsersOfGroupsWithNameAndBalance(idgroup);
			for (Groups_Users user : userList) {
				userInfoDao.updateUserBalanceByUsername(user.getBalance() - netflixPrice, user.getUsername());
			}
			return "redirect:/admin/groups/netflix/sufficientBalance";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/addBalance", method = RequestMethod.GET)
	public String addBalance() {
		return "adminAddBalanceForm";
	}

	@RequestMapping(value = "/admin/receiveAddBalanceRequest", method = RequestMethod.GET)
	public String receiveAddBalanceRequest(@RequestParam String username, @RequestParam int money, Model model,
			Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			int oldBalance = userInfoDao.getUserBalanceByUsername(username);
			userInfoDao.updateUserBalanceByUsername(oldBalance + money, username);

			model.addAttribute("oldBalance", oldBalance);
			model.addAttribute("newBalance", oldBalance + money);
			model.addAttribute("username", username);
			return "adminAddBalanceForm";
		} else {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/admin/changeAccountPassword", method = RequestMethod.GET)
	public String changeAccountPasswordForm() {
		return "adminChangeAccountPassword";
	}

	@RequestMapping(value = "/admin/receiveChangeAccountPasswordRequest", method = RequestMethod.GET)
	public String receiveChangeAccountPasswordRequest(@RequestParam String email, @RequestParam String password,
			Model model, Principal principal) {
		String role = userInfoDao.getUserRoles(principal.getName());
		if (role.equals("ADMIN")) {
			accountDao.updateAccountPassword(email, password);
			model.addAttribute("message", "changed password of service account: " + email);
			return "adminChangeAccountPassword";
		} else {
			return "redirect:/403";
		}
	}
}
