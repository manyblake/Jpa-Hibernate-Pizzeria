package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "impasto")
public class Impasto {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "nome")
	private String name;

	public int getId() {
		return id;
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

	public Impasto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Impasto() {

	}

	@Override
	public String toString() {
		return "Impasto [id=" + id + ", name=" + name + "]";
	}

}
