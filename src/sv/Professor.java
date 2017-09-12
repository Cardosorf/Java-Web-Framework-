package sv;

public class Professor {
	private int id = -1;
	private String nome = "";
	private String celular = "";
	private String email = "";

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = (nome == null ? "" : nome);
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular( String celular ) {
		this.celular = (celular == null ? "" : celular);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = (email == null ? "" : email);
	}

	public String toString() {
		return getNome() + "/" + getCelular() + "/" + getEmail();
	}

	public void setID( String valor ) {
		id = Integer.valueOf( valor );
	}

	public int getID() {
		return id;
	}
}
