package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.GroupInfoDao;
import com.mvc.dao.PostDao;
import com.mvc.model.Groups_Users;
import com.mvc.service.InGroupValidatorService;

@Service
public class InGroupValidatorServiceImpl implements InGroupValidatorService {
	private final int userGroupMatch = 0;
	private final int error = 1;

	@Autowired
	private GroupInfoDao groupInfoDao;

	@Autowired
	private PostDao postDao;

	public int checkUserGroupMatch(int iduser, int idgroup) {
		// TODO Auto-generated method stub
		try {
			List<Groups_Users> userGroups = groupInfoDao.getAllGroupOfUser(iduser);
			for (Groups_Users groups_Users : userGroups) {
				if (groups_Users.getIdgroup() == idgroup) {
					return userGroupMatch;
				}
			}
			return error;
		} catch (Exception e) {
			return error;
		}
	}

	private final int postInGroup = 2;

	public int checkIfPostIsInGroup(int idpost, int idgroup) {
		// TODO Auto-generated method stub
		try {
			int groupParentId = postDao.GetGroupIdByPostId(idpost);
			if (groupParentId == idgroup) {
				return postInGroup;
			} else {
				return error;
			}
		} catch (Exception e) {
			return error;
		}
	}

}
