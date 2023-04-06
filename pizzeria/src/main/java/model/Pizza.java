package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pizza")
@XmlRootElement(name = "pizza")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "impasto_id", nullable = false)
	private Impasto impasto;

	@ManyToOne
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;

	@ManyToMany
	@JoinTable(name = "pizza_ingrediente", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
	private List<Ingrediente> ingredienti;

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getUtenteId() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public Pizza(int id, String nome, Utente utente, Impasto impasto) {
		super();
		this.id = id;
		this.nome = nome;
		this.utente = utente;
		this.impasto = impasto;
	}

	public Pizza() {
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nome=" + nome + ", utente=" + utente.getName() + ", impasto=" + impasto.getName()
				+ "]";
	}

}
