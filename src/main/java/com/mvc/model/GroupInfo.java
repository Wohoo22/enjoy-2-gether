package com.mvc.model;

public class GroupInfo {
	private int idgroup;
	private String groupname;
	private int memberQuantity;
	private String service;
	private int tag;
	private int quantity;
	private Account account;

	public GroupInfo() {

	}

	public GroupInfo(int idgroup, String groupname, String service, int memberQuantity) {
		this.idgroup = idgroup;
		this.groupname = groupname;
		this.service = service;
		this.memberQuantity = memberQuantity;
	}

	public GroupInfo(int tag, int idgroup, String groupname, String service, int quantity) {
		this.tag = tag;
		this.idgroup = idgroup;
		this.groupname = groupname;
		this.service = service;
		this.quantity = quantity;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public int getMemberQuantity() {
		return memberQuantity;
	}

	public void setMemberQuantity(int memberQuantity) {
		this.memberQuantity = memberQuantity;
	}

}
