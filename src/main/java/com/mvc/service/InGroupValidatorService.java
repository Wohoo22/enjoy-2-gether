package com.mvc.service;

public interface InGroupValidatorService {
	int checkUserGroupMatch(int iduser, int idgroup);

	int checkIfPostIsInGroup(int idpost, int idgroup);
}
