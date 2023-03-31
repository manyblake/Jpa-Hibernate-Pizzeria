package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utente {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "username")
	private String user;
	
	@Column(name = "pwd")
	private String password;

	@OneToMany(mappedBy = "utente")
	private List<Pizza> pizze;

	public int getId() {
		return id;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utente(int id, String name, String user, String password) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.password = password;
	}

	public Utente() {
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", name=" + name + ", user=" + user + ", password=" + password + "]";
	}

}
