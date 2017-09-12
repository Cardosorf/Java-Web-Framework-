package sv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProfessorDAO {
static private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/professores.txt";
	
    static private Map<Integer, Professor> professores = leProfessores( path );
    
    public static void salva( Map<Integer, Professor> map ) {
    	try{
    		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    		for(int user : map.keySet()) {
    			writer.write( map.get(user).getID()+ "|" + map.get(user).getNome()+ "|" 
    		+ map.get(user).getCelular()+ "|" + map.get(user).getEmail());
    			writer.newLine();
    		}
    	    writer.close();
    	    professores = leProfessores( path );   
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    public static Professor recupera( int ID ) {
        return professores.get( ID );
    }
    
    
    public static Map<Integer, Professor> getProfessores() {
        return professores;
    }

    
    static Map<Integer, Professor> leProfessores( String arquivo ) {
        Map<Integer, Professor> map = new TreeMap<>();
        List<String> linhas;
        try {
            linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );
            for( String linha : linhas ) {
                String w[] = linha.split( "[|]" );
                Professor professor = new Professor();
                professor.setID( w[0] );
                professor.setNome( w[1] );
                professor.setCelular( w[2] );
                professor.setEmail( w[3] );
                map.put( professor.getID(), professor );
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return map;
    }
}
