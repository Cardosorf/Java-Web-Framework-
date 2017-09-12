package sv;

public class CampoNaoPreenchidoException extends Exception {

    public CampoNaoPreenchidoException( String nomeParam ) {
        super( "O campo " + nomeParam + " deve ser preenchido." );
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
