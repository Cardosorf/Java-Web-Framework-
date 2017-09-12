package sv;

public class FormDisciplina {
	private int id = -1;
	private String nome = "";
	private String entidade = "Disciplina";

	public String getEntidade() {
		return entidade;
	}

	/**
	 * O nome da disciplina. Nao pode ser deixado em branco.
	 * 
	 * @return retorna o nome, ou null.
	 */
	public String getNome() {
		return nome;
	}

	@Campo( obrigatorio = true ) 
	public void setNome( String nome ) {
		this.nome = (nome == null ? "" : nome);
	}

	@Override
	public String toString() {
		return getNome();
	}

	public static void main( String[] args ) {
		System.out.println( new FormDisciplina().getClass().getCanonicalName() );
	}

	public void setID( String valor ) {
		id = Integer.valueOf( valor );
	}

	public int getID() {
		return id;
	}

}
