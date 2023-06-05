package models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String password;
	
	private String cpf;
	
	private String telephoneNumber;
	
	private boolean employee;
	
	private boolean blocked;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<MyBooks> books;

	
	public List<MyBooks> getBooks() {
		return books;
	}

	public void setBooks(List<MyBooks> books) {
		this.books = books;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password, String cpf, String telephoneNumber, boolean employee) {
		super();
		this.name = name;
		this.password = password;
		this.cpf = cpf;
		this.telephoneNumber = telephoneNumber;
		this.employee = employee;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String mame) {
		this.name = mame;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@Override
	public String toString() {
		return "User [name=" + name;
	}	
	
	
}
