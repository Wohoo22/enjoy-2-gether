package com.mvc.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.GroupInfoDao;
import com.mvc.mapper.GroupInfoMapper;
import com.mvc.mapper.Groups_UsersMapper;
import com.mvc.mapper.Groups_UsersUsernameBalanceMapper;
import com.mvc.model.GroupInfo;
import com.mvc.model.Groups_Users;

@Service
@Transactional
public class GroupInfoDaoImpl extends JdbcDaoSupport implements GroupInfoDao {
    @Autowired
    public GroupInfoDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public GroupInfo getGroup(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE idgroup = ?";
        Object[] param = new Object[]{id};
        GroupInfoMapper mapper = new GroupInfoMapper();
        try {
            GroupInfo groupInfo = this.getJdbcTemplate().queryForObject(sql, mapper, param);
            return groupInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<GroupInfo> getAllNetflixGroup(int loadNum) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='NETFLIX' LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        Object[] param = new Object[]{loadNum};
        try {
            List<GroupInfo> groupList = this.getJdbcTemplate().query(sql, param, mapper);
            return groupList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<GroupInfo> getAllNetflixGroupWithIncreaseMemberQuantitySort(int loadNum) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='NETFLIX' ORDER BY memberQuantity ASC LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        Object[] param = new Object[]{loadNum};
        try {
            List<GroupInfo> groupList = this.getJdbcTemplate().query(sql, param, mapper);
            return groupList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<GroupInfo> getAllNetflixGroupWithDecreaseMemberQuantitySort(int loadNum) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='NETFLIX' ORDER BY memberQuantity DESC LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        Object[] param = new Object[]{loadNum};
        try {
            List<GroupInfo> groupList = this.getJdbcTemplate().query(sql, param, mapper);
            return groupList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int addUserToGroup(int iduser, int idgroup) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO groups_users (iduser,idgroup) values (?,?)";
        Object[] param = new Object[]{iduser, idgroup};
        try {
            int counter = this.getJdbcTemplate().update(sql, param);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Groups_Users checkGroupsUsers(int iduser, int idgroup) {
        String sql = "SELECT * FROM groups_users where idgroup=? and iduser=?";
        Object[] param = new Object[]{idgroup, iduser};
        Groups_UsersMapper mapper = new Groups_UsersMapper();
        try {
            Groups_Users gu = this.getJdbcTemplate().queryForObject(sql, param, mapper);
            return gu;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Groups_Users> getAllGroupOfUser(int iduser) {
        // TODO Auto-generated method stub
        String sql = "SELECT iduser, idgroup FROM groups_users where iduser=?";
        Object[] param = new Object[]{iduser};
        Groups_UsersMapper mapper = new Groups_UsersMapper();
        try {
            List<Groups_Users> gu = this.getJdbcTemplate().query(sql, param, mapper);
            return gu;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Groups_Users> getAllGroupUsers(int idgroup) {
        // TODO Auto-generated method stub
        String sql = "SELECT iduser, idgroup FROM groups_users WHERE idgroup=?";
        Object[] param = new Object[]{idgroup};
        Groups_UsersMapper mapper = new Groups_UsersMapper();
        try {
            List<Groups_Users> gu = this.getJdbcTemplate().query(sql, param, mapper);
            return gu;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getGroupNameByGroupId(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT groupname FROM groups WHERE idgroup=?";
        Object[] params = new Object[]{id};
        try {
            String name = this.getJdbcTemplate().queryForObject(sql, params, String.class);
            return name;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Groups_Users> getFullGroups_UsersTable() {
        // TODO Auto-generated method stub
        String sql = "SELECT iduser, idgroup FROM groups_users";
        Groups_UsersMapper mapper = new Groups_UsersMapper();
        try {
            List<Groups_Users> table = this.getJdbcTemplate().query(sql, mapper);
            return table;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getGroupService(int idgroup) {
        // TODO Auto-generated method stub
        String sql = "SELECT service FROM groups WHERE idgroup=?";
        Object[] params = new Object[]{idgroup};
        try {
            String sv = this.getJdbcTemplate().queryForObject(sql, params, String.class);
            return sv;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Groups_Users> getUsersOfGroupsWithNameAndBalance(int idgroup) {
        // TODO Auto-generated method stub
        String sql = "SELECT groups_users.idgroup, groups_users.iduser, users.username, users.balance FROM groups_users INNER JOIN users ON groups_users.iduser = users.iduser and idgroup=?";
        Object[] params = new Object[]{idgroup};
        Groups_UsersUsernameBalanceMapper mapper = new Groups_UsersUsernameBalanceMapper();
        try {
            List<Groups_Users> list = this.getJdbcTemplate().query(sql, params, mapper);
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteUserFromGroup(int idgroup, int iduser) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM groups_users WHERE iduser=? and idgroup=?";
        Object[] params = new Object[]{iduser, idgroup};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createGroup(String groupname, String service) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO groups (groupname, service) VALUES (?, ?)";
        Object[] params = new Object[]{groupname, service};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNextAutoIncrementGroupId() {
        // TODO Auto-generated method stub
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'loginmvc' AND TABLE_NAME = 'groups'";
        try {
            int id = this.getJdbcTemplate().queryForObject(sql, Integer.class);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getGroupMemberQuantity(int idgroup) {
        // TODO Auto-generated method stub
        String sql = "SELECT memberQuantity FROM groups WHERE idgroup=?";
        Object[] param = new Object[]{idgroup};
        try {
            int id = this.getJdbcTemplate().queryForObject(sql, Integer.class, param);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public void updateGroupMemberQuantity(int memberQuantity, int idgroup) {
        // TODO Auto-generated method stub
        String sql = "UPDATE groups SET memberQuantity=? WHERE idgroup=?";
        Object[] param = new Object[]{memberQuantity, idgroup};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GroupInfo> getAllNetflixGroupWithFull5Member() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='NETFLIX' AND memberQuantity=5";
        GroupInfoMapper mapper = new GroupInfoMapper();
        try {
            List<GroupInfo> list = this.getJdbcTemplate().query(sql, mapper);
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<GroupInfo> getAllDisneyGroup(int loadNum) {
        String sql = "SELECT * FROM groups WHERE service='NETFLIX' LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        try {
            List<GroupInfo> list = this.getJdbcTemplate().query(sql, mapper);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GroupInfo> getAllDisneyGroupWithIncreaseMemberQuantitySort(int loadNum) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='DISNEY' ORDER BY memberQuantity ASC LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        Object[] param = new Object[]{loadNum};
        try {
            List<GroupInfo> groupList = this.getJdbcTemplate().query(sql, param, mapper);
            return groupList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<GroupInfo> getAllDisneyGroupWithDecreaseMemberQuantitySort(int loadNum) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM groups WHERE service='DISNEY' ORDER BY memberQuantity DESC LIMIT 0,?";
        GroupInfoMapper mapper = new GroupInfoMapper();
        Object[] param = new Object[]{loadNum};
        try {
            List<GroupInfo> groupList = this.getJdbcTemplate().query(sql, param, mapper);
            return groupList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
