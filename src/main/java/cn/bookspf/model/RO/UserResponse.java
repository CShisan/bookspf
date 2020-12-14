package cn.bookspf.model.RO;

import java.util.ArrayList;

import cn.bookspf.model.Dao.User;

public class UserResponse extends Response{
	private ArrayList<User> users;
	
	public UserResponse() {}
	public UserResponse(ArrayList<User> users) {
		this.users=users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	
}
