package sv;

public class CampoProfessorException extends Exception{
    public CampoProfessorException( String nomeParam ) {
        super( "O campo " + nomeParam + " deve ser preenchido com um nome válido." );
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
