package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.GroupInfoDao;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;
import com.mvc.service.NetflixJoinGroupValidatorService;

@Service
public class NetflixJoinGroupValidatorServiceImpl implements NetflixJoinGroupValidatorService {
	private final int userCanJoin = 0;
	private final int userOfAnotherGroup = 1;
	private final int groupFull = 2;
	private final int groupNull = 3;
	private final int userOfGroup = 4;

	@Autowired
	private GroupInfoDao groupInfoDao;

	public GroupInfo CheckUserQualityService(int iduser, int idgroup) {
		// TODO Auto-generated method stub

		// Check group co ton tai khong
		try {
			GroupInfo group = groupInfoDao.getGroup(idgroup);
			System.out.println(group.getGroupname());
		} catch (Exception e) {
			return new GroupInfo(groupNull, 0, "null", "null", 0);
		}

		// so luong member cua group
		List<Groups_Users> userList = groupInfoDao.getAllGroupUsers(idgroup);
		int num = 0;
		for (int i = 0; i < userList.size(); i++) {
			num++;
		}

		// check user co o group nao k
		try {
			List<Groups_Users> gr_us = groupInfoDao.getAllGroupOfUser(iduser);
			// user da o trong gr nao do
			// check group cua user co == group desired or khac
			for (Groups_Users groups_Users : gr_us) {
				// user of group
				if (groups_Users.getIdgroup() == idgroup) {
					return new GroupInfo(userOfGroup, idgroup, groupInfoDao.getGroupNameByGroupId(idgroup),
							groupInfoDao.getGroupService(idgroup), num);
				} else {
					// check service
					// service giong nhau -> return userOfAnotherGroup
					if (groupInfoDao.getGroupService(idgroup)
							.equals(groupInfoDao.getGroupService(groups_Users.getIdgroup()))) {
						return new GroupInfo(userOfAnotherGroup, idgroup, groupInfoDao.getGroupNameByGroupId(idgroup),
								groupInfoDao.getGroupService(idgroup), num);
					}
				}
			}
			// user gan nhu co the join neu k dinh 2 dieu kien trong loop
			// check xem group da du nguoi chua(5 nguoi Netflix)

			// check <5 hay da du 5 nguoi
			if (num < 5) {
				// ok bay h user co the join
				return new GroupInfo(userCanJoin, idgroup, groupInfoDao.getGroupNameByGroupId(idgroup),
						groupInfoDao.getGroupService(idgroup), num);
			} else {
				// group full
				return new GroupInfo(groupFull, idgroup, groupInfoDao.getGroupNameByGroupId(idgroup),
						groupInfoDao.getGroupService(idgroup), num);
			}
		} catch (Exception e) {
			// group k co ai thi se catch vao day
			// nhung dieu nay kho co the xay ra lam
			return new GroupInfo(userCanJoin, idgroup, groupInfoDao.getGroupNameByGroupId(idgroup),
					groupInfoDao.getGroupService(idgroup), num);
		}

	}

	private final int userNotInGroupWithSameService = 0;

	public int CheckIfUserHadBeenInAGroupWithSameService(int iduser, String service) {
		// TODO Auto-generated method stub
		try {
			// get tat ca groupid cua user
			List<Groups_Users> gu = groupInfoDao.getAllGroupOfUser(iduser);
			for (Groups_Users groups_Users : gu) {
				// service cua 1 group cua user = service desired -> user da o 1
				// group roi
				if (service.equals(groupInfoDao.getGroupService(groups_Users.getIdgroup()))) {
					return groups_Users.getIdgroup();
				}
			}
			return userNotInGroupWithSameService;
		} catch (Exception e) {
			// TODO: handle exception
			return userNotInGroupWithSameService;
		}
	}

}
