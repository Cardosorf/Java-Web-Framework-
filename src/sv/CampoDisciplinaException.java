package sv;

public class CampoDisciplinaException extends Exception{
    public CampoDisciplinaException( String nomeParam ) {
        super( "O campo " + nomeParam + " deve ser preenchido com um nome v�lido." );
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
}
