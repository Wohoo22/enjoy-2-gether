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
import com.mvc.model.Comment;
import com.mvc.model.Groups_Accounts;
import com.mvc.model.Groups_Users;
import com.mvc.model.Post;
import com.mvc.service.InGroupValidatorService;

@Controller
public class InGroupController {
	private final int netflixPrice = 75000;
	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private GroupInfoDao groupInfoDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private InGroupValidatorService inGroupValidatorService;

	@Autowired
	private AccountDao accountDao;

	@RequestMapping(value = "/inGroup/{id}", method = RequestMethod.GET)
	public String inGroup(@PathVariable("id") int id, Model model, Principal principal, @RequestParam int postNum) {
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());

			// userGroupMatch = 0;
			// error = 1;

			int checkNum = inGroupValidatorService.checkUserGroupMatch(iduser, id);

			if (checkNum == 0) {
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

				// username and idgroup
				model.addAttribute("username", principal.getName());
				model.addAttribute("id", id);

				// get service cua group dang dung
				String service = groupInfoDao.getGroupService(id);
				if (service.equals("NETFLIX")) {
					model.addAttribute("service", "NETFLIX");
					model.addAttribute("servicePrice", netflixPrice);
				}
				// if (service.equals("DISNEY")) {
				// model.addAttribute("service", "DISNEY+");
				// model.addAttribute("servicePrice", disneyPrice);
				// }

				// get account cua group
				// attribute: 0 la co account, 1 la ko co account, 2 la account
				// het han
				try {
					Groups_Accounts ga = accountDao.getAccountDetailsOfGroupByGroupId(id);

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
				model.addAttribute("currentPostNum", postNum);
				model.addAttribute("postNum", postNum + 5);

				return "inGroup";

			} else {
				return "redirect:/403";
			}
		} catch (Exception e) {
			return "loginPage";
		}
	}

	@RequestMapping(value = "/inGroup/{id}/submitPost", method = RequestMethod.POST)
	public String postSubmit(Model model, @ModelAttribute("postForm") @Validated Post postForm,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult error");
			return "redirect:/403";
		}
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());
			// check xem co dung user cua group ko
			int checkNum = inGroupValidatorService.checkUserGroupMatch(iduser, id);

			// userGroupMatch = 0;
			// error = 1;
			if (checkNum == 0) {
				// match rui thi add post ne
				postDao.mapPostIdWithGroupId(postDao.getNextAutoIncrementPostId(), id);
				postDao.mapPostIdWithUserId(postDao.getNextAutoIncrementPostId(), iduser);
				postDao.createPost(postForm.getPostDetail());
				return "redirect:/inGroup/" + id + "?postNum=5";
			} else {
				return "redirect:/403";
			}

		} catch (Exception e) {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/ingroup/{id}/{idpost}/deletePost/{postNum}", method = RequestMethod.POST)
	public String postDelete(@PathVariable("idpost") int idpost, @PathVariable("id") int id,
			@PathVariable("postNum") int postNum, Principal principal, Model model) {
		try {
			int iduser = postDao.GetPostUserIdByPostId(idpost);
			System.out.println(iduser);
			// check xem co phai user viet post nay khong
			if (iduser == userInfoDao.getUserIdByUsername(principal.getName())) {

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

				return "redirect:/inGroup/" + id + "?postNum=" + postNum;
			} else {
				return "redirect:/403";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/inGroup/{id}/{idpost}/submitComment/{postNum}", method = RequestMethod.POST)
	public String commentSubmit(Model model, @ModelAttribute("commentForm") @Validated Comment commentForm,
			BindingResult bindingResult, final RedirectAttributes redirectAttributes, @PathVariable("id") int id,
			@PathVariable("idpost") int idpost, @PathVariable("postNum") int postNum, Principal principal) {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult error");
			return "redirect:/403";
		}
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());
			// check user co thuoc group ko
			int checkNum = inGroupValidatorService.checkUserGroupMatch(iduser, id);
			// check post co thuoc group ko
			int checkNum2 = inGroupValidatorService.checkIfPostIsInGroup(idpost, id);

			// error: 1
			// userGroupMatch: 0
			// postInGroup: 2
			if (checkNum == 0 && checkNum2 == 2) {
				int idcomment = commentDao.getNextAutoIncrementCommentId();
				commentDao.mapCommentIdWithPostId(idcomment, idpost);
				commentDao.mapCommentIdWithUserId(idcomment, iduser);
				commentDao.createComment(commentForm.getCommentDetail());
				return "redirect:/inGroup/" + id + "?postNum=" + postNum;
			} else {
				return "redirect:/403";
			}
		} catch (Exception e) {
			return "redirect:/403";
		}
	}

	@RequestMapping(value = "/ingroup/{id}/{idcomment}/deleteComment/{postNum}", method = RequestMethod.POST)
	public String commentDelete(@PathVariable("idcomment") int idcomment, @PathVariable("id") int id,
			@PathVariable("postNum") int postNum, Principal principal, Model model) {
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());
			int idOwnerComment = commentDao.getCommentOwnerIdByIdComment(idcomment);

			// check xem user co phai owner cua comment
			if (iduser == idOwnerComment) {
				commentDao.deleteCommentByCommentId(idcomment);
				commentDao.deleteCommentIdAndPostIdMap(idcomment);
				commentDao.deleteCommentIdAndUserIdMap(idcomment);
				return "redirect:/inGroup/" + id + "?postNum=" + postNum;
			} else {
				return "redirect:/403";

			}
		} catch (Exception e) {
			return "redirect:/403";

		}
	}

	@RequestMapping(value = "/outgroup/{id}", method = RequestMethod.POST)
	public String outGroup(@PathVariable("id") int id, Principal principal, Model model) {
		try {
			int iduser = userInfoDao.getUserIdByUsername(principal.getName());

			// check user co thuoc group ko
			int checkNum = inGroupValidatorService.checkUserGroupMatch(iduser, id);
			// userGroupMatch = 0
			// error =1

			if (checkNum == 0) {
				groupInfoDao.deleteUserFromGroup(id, iduser);

				// update group member quantity
				groupInfoDao.updateGroupMemberQuantity(groupInfoDao.getGroupMemberQuantity(id) - 1, id);

				return "redirect:/groupInfo/netflix/" + id;
			} else {
				return "redirect:/403";
			}

		} catch (Exception e) {
			return "redirect:/403";
		}
	}
}
