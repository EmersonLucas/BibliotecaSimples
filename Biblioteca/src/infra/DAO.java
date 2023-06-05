package infra;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Book;
import models.MyBooks;
import models.User;
import models.validations.BookValidation;
import models.validations.UserValidation;

public class DAO <E>{
	
	private static EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;

	private Class<E> classObject;
	
	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("Biblioteca");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classObject ) {
		this.classObject = classObject;
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public DAO<E> openTransaction(){
		entityManager.getTransaction().begin();
		return this;
	}
	
	public DAO<E> closeTransaction(){
		entityManager.getTransaction().commit();
		return this;
	}
	
	public DAO<E> includeObject(E entity){
		entityManager.persist(entity);
		return this;
	}
	
	public DAO<E> includeAtomic(E entity){
		return this.openTransaction().includeObject(entity).closeTransaction();
	}
	
	public List<E> getAllEntity(){
		if (classObject == null) throw new UnsupportedOperationException("Class is null");
		String JPQL = "SELECT e FROM " + classObject.getName()+ " e";
		TypedQuery<E> query = entityManager.createQuery(JPQL, classObject);
		return query.getResultList();
	}
	
	public E getEntityId(Long id){
		return entityManager.find(classObject, id);
	}
	
	public E getEntityName(String nameQuery, String name){
		TypedQuery<E> query = entityManager.createNamedQuery(nameQuery, classObject);
		query.setParameter("name", name);
	
		try {
			return query.getSingleResult();
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		}	
	}
	
	
	public E getObjectEntity(String nameQuery, String name, String pass){
		TypedQuery<E> query = entityManager.createNamedQuery(nameQuery, classObject);
		query.setParameter("name", name);
		query.setParameter("password", pass);
		
		try {
			return query.getSingleResult();
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		}	
	}
	
	public List<MyBooks> getMyBooks(Long id){
		TypedQuery<MyBooks> query = entityManager.createNamedQuery("getMyBooksInDataBase", MyBooks.class);
		query.setParameter("userId", id);
		try {
			return query.getResultList();
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		}	
	}
	
	public DAO<E> deleteBook(int id){
		Book book = entityManager.find(Book.class, id);
		openTransaction();
		entityManager.remove(book);
		closeTransaction();
		return this;
		}
	
	public DAO<E> blockUser(int id, boolean bool){
		User user = entityManager.find(User.class, id);
		openTransaction();
		user.setBlocked(bool);
		closeTransaction();
		return this;
		}
	
	public DAO<E> editBook(int id, String nome, String autor, String Editora,Integer qtd){
		openTransaction();
		Book book = entityManager.find(Book.class, id);
		if(nome != null) book.setNome(nome);
		if(autor != null) book.setAutor(autor);
		if(Editora != null) book.setEditora(Editora);
		if(qtd != null) book.setQuantidade(qtd);
		closeTransaction();
		return this;
		}
	
	public DAO<E> removeBookMyList(Long idBook){
	
		try {
			Query  query = entityManager.createNamedQuery("myBookDeleteById");
			query.setParameter("id", idBook);
			
			MyBooks myBooks = BookValidation.daoOfMybooks.getEntityId(idBook);
			
			if(myBooks == null)throw new NullPointerException();
			else {
			openTransaction();
			Book book = BookValidation.daoOfBook.getEntityName("bookFindByNome" ,myBooks.getNome());
			book.setQuantidade(myBooks.getQuantidade() + book.getQuantidade());
			query.executeUpdate();
			closeTransaction();
			}

		} catch (Exception e) {
		
			return this;
		}
		return this;
	}
	
	public DAO<E> includeMyBook(Long id, Integer qtd) {
		try {
			Book book = BookValidation.daoOfBook.getEntityId(id);
			if(book == null){
				return this;
			}
			
			try {
				MyBooks bookInMyBooks = BookValidation.daoOfMybooks.getEntityName("myBooksFindByNome",  book.getNome());
				if(bookInMyBooks == null) throw new NullPointerException();	
				return this ;
				
			} catch (Exception e) {
			
				if(qtd > book.getQuantidade() || qtd <= 0) {
					return this;
					}
				else{
					openTransaction();
					book.setQuantidade(book.getQuantidade() - qtd);
					String nome = book.getNome();
					MyBooks myBook = new MyBooks(nome, qtd, UserValidation.user);
					entityManager.persist(myBook);
					closeTransaction();
				}
			
			}
						
		} catch (Exception e){
			System.out.println(e);
			closeEntityManager();
			return this;
		}
	    return this;
	}
		
	
	public void closeEntityManager(){
		entityManager.close();
	}
	
}
