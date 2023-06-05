package models.validations;

import infra.BookDAO;
import infra.MyBooksDAO;

public class BookValidation {
	
	public static BookDAO daoOfBook = new BookDAO();
	public static MyBooksDAO daoOfMybooks = new MyBooksDAO();
	
	public static boolean fieldIsNotEmpty(String nome, String autor, String editora){	
		return (!nome.trim().equals("")||
				!autor.trim().equals("")||
				!editora.trim().equals(""))? true : false;
	}
	
	public static boolean fieldIsNotEmptyNome(String nome){
		return (!nome.trim().equals(""))? true : false;
	}
	
	public static boolean fieldIsNotEmptyAutor(String autor){
		return (!autor.trim().equals(""))? true : false;
	}
	
	public static boolean fieldIsNotEmptyEditora(String editora){
		return (!editora.trim().equals(""))? true : false;
	}
	
	public static boolean fieldIsNotEmptyQuantidade(String qtd){
		return (!qtd.trim().equals(""))? true : false;
	}

}
