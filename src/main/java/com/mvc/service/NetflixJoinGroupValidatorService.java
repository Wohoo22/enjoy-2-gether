package com.mvc.service;

import com.mvc.model.GroupInfo;

public interface NetflixJoinGroupValidatorService {
	GroupInfo CheckUserQualityService(int iduser, int idgroup);

	int CheckIfUserHadBeenInAGroupWithSameService(int iduser, String service);
}
