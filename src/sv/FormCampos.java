package sv;

import java.util.Map;

public class FormCampos {

    private String entidade;
    private Map<String,String> campos;

    public String getEntidade() {
        return entidade;
    }
    
    @Campo( obrigatorio = true )
    public void setEntidade( String entidade ) {
        this.entidade = entidade;
    }

    public Map<String,String> getCampos() {
        return campos;
    }

    public void setCampos( Map<String,String> campos ) {
        this.campos = campos;
    }

}
