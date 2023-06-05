package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_meusLivros")
public class MyBooks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer quantidade;
	

	@ManyToOne(cascade = CascadeType.ALL)
	User user;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MyBooks() {
		// TODO Auto-generated constructor stub
	}

	
	
	public MyBooks(String nome, Integer quantidade, User user) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.user = user;
	}

	public MyBooks(Long id, String nome, Integer quantidade,User user) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.user = user;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MyBooks [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", user=" + user + "]";
	}

	
	
	
}
