package sv;

import java.util.Map;

public class FormExibir {

	private String entidade;
	private String id;
	private Map<String,String> campos;
	private String editar = "";

	public String getEditar() {
		return editar == null ? "" : editar;
	}

	@Campo
	public void setEditar( String editar ) {
		this.editar = editar;
	}

	public String getEntidade() {
		return entidade;
	}

	@Campo( obrigatorio = true )
	public void setEntidade( String entidade ) {
		this.entidade = entidade;
	}

	public String getId() {
		return id;
	}

	@Campo( obrigatorio = true )
	public void setId( String id ) {
		this.id = id;
	}

	public Map<String,String> getCampos() {
		return campos;
	}

	public void setCampos( Map<String,String> campos ) {
		this.campos = campos;
	}

}
