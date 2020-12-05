package com.mvc.dao;

import java.util.List;

import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;

public interface GroupInfoDao {
    public GroupInfo getGroup(int id);

    public int addUserToGroup(int iduser, int idgroup);

    public Groups_Users checkGroupsUsers(int iduser, int idgroup);

    public List<Groups_Users> getAllGroupOfUser(int iduser);

    public List<Groups_Users> getAllGroupUsers(int idgroup);

    public List<GroupInfo> getAllNetflixGroup(int loadNum);

    public List<GroupInfo> getAllNetflixGroupWithIncreaseMemberQuantitySort(int loadNum);

    public List<GroupInfo> getAllNetflixGroupWithDecreaseMemberQuantitySort(int loadNum);

    public String getGroupNameByGroupId(int id);

    public List<Groups_Users> getFullGroups_UsersTable();

    public String getGroupService(int idgroup);

    public List<Groups_Users> getUsersOfGroupsWithNameAndBalance(int idgroup);

    void deleteUserFromGroup(int idgroup, int iduser);

    void createGroup(String groupname, String service);

    int getNextAutoIncrementGroupId();

    int getGroupMemberQuantity(int idgroup);

    void updateGroupMemberQuantity(int memberQuantity, int idgroup);

    List<GroupInfo> getAllNetflixGroupWithFull5Member();

    //disney +

    List<GroupInfo> getAllDisneyGroup(int loadNum);

    public List<GroupInfo> getAllDisneyGroupWithIncreaseMemberQuantitySort(int loadNum);

    public List<GroupInfo> getAllDisneyGroupWithDecreaseMemberQuantitySort(int loadNum);
}
