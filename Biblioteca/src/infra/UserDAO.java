package infra;


import models.User;

public class UserDAO extends DAO<User>{
	public UserDAO() {
		super(User.class);
	}
	
}
