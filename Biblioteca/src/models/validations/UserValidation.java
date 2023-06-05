package models.validations;

import java.io.IOException;

import controllers.PagesControllers;
import infra.UserDAO;
import models.ScenesEnum;
import models.User;

public class UserValidation {
	
	public static UserDAO daoOfUser = new UserDAO();
    public static String viewNameUser;
    public static User user;
	 
	public static boolean fieldIsNotEmpty(String name, String pass, String cpf, String telNumber){
		return(!name.trim().equals("")||
				!pass.trim().equals("")||
				!cpf.trim().equals("")||
				!telNumber.trim().equals("")) ? true : false;
	}
	
	public static boolean fieldIsNotEmpty(String name, String pass){	
		return (!name.trim().equals("")|| !pass.trim().equals(""))? true : false;
	}
	
	public static boolean validationLogin(String name, String pass) throws IOException{
		if(fieldIsNotEmpty(name, pass)){
			user = daoOfUser.getObjectEntity("getUserInDataBase", name, pass );
			if(user == null)return false;
			else if(user.isEmployee()) {
			    viewNameUser = user.getName();
				PagesControllers.changePage(ScenesEnum.EMPLOYEE);
				}
			else {
			    viewNameUser = user.getName();
				PagesControllers.changePage(ScenesEnum.CLIENT);	
			}
		}else return false;
	return true;
	}
	
}
