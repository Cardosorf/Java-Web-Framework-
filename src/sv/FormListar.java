package sv;

import java.util.ArrayList;

public class FormListar {

	private String entidade;
	private ArrayList<String> nome;
	private int size;

	public String getEntidade() {
		return entidade;
	}

	@Campo( obrigatorio = true )
	public void setEntidade( String entidade ) {
		this.entidade = entidade;
	}

	public ArrayList<String> getNome() {
		return nome;
	}

	public void setNome( ArrayList<String> nome ) {
		this.nome = nome;
	}

	public int getSize() {
		return size;
	}

	public void setSize( int size ) {
		this.size = size;
	}

}
