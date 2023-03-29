package model;

public class Pizza {
	private int id;
	private String nome;
	private int utenteId;
	private int impastoId;

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

	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public int getImpastoId() {
		return impastoId;
	}

	public void setImpastoId(int impastoId) {
		this.impastoId = impastoId;
	}

	public Pizza(int id, String nome, int utenteId, int impastoId) {
		super();
		this.id = id;
		this.nome = nome;
		this.utenteId = utenteId;
		this.impastoId = impastoId;
	}
	
	public Pizza() {
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nome=" + nome + ", utenteId=" + utenteId + ", impastoId=" + impastoId + "]";
	}

}
