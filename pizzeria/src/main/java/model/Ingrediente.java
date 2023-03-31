package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "nome")
	private String name;

	@ManyToMany
	@JoinTable(name = "pizza_ingrediente", joinColumns = @JoinColumn(name = "ingrediente_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizze;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ingrediente(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Ingrediente() {

	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", name=" + name + "]";
	}

}
