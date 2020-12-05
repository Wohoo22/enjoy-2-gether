package com.mvc.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mvc.dao.UserInfoDao;
import com.mvc.model.UserInfo;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
	@Autowired
	private UserInfoDao userInfoDao;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoDao.findUserByEmail(email);

		if (userInfo == null) {
			throw new UsernameNotFoundException("Email " + email + " was not found in the database");
		}
		String role = userInfo.getRole();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (role != null) {

			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
			grantList.add(authority);

		}

		UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
				userInfo.getPassword(), grantList);
		return userDetails;
	}

}
