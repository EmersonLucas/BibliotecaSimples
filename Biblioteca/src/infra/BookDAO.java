package infra;

import models.Book;

public class BookDAO extends DAO<Book> {
	public BookDAO() {
		super(Book.class);
	}
}
