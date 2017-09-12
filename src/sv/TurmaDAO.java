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

public class TurmaDAO {

	static private String pathTurma = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/turmas.txt";

	static private Map<Integer, Turma> turmas = leTurmas( pathTurma );

	public static void salva( Map<Integer, Turma> map ) { //throws Exception
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(pathTurma));
			for(int user : map.keySet()) {
				writer.write( map.get(user).getID()+ "|" + map.get(user).getNome()+ "|" 
						+ map.get(user).getProfessor()+ "|" + map.get(user).getDisciplina());
				writer.newLine();
			}
			writer.close();
			turmas = leTurmas( pathTurma );   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Turma recupera( int ID ) {
		return turmas.get( ID );
	}


	public static Map<Integer, Turma> getTurmas() {
		return turmas;
	}


	static Map<Integer, Turma> leTurmas( String arquivo ) {
		Map<Integer, Turma> map = new TreeMap<>();
		List<String> linhas;
		try {
			linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );
			for( String linha : linhas ) {
				String w[] = linha.split( "[|]" );
				Turma turma = new Turma();
				turma.setID( w[0] );
				turma.setNome( w[1] );
				turma.setProfessor( w[2] );
				turma.setDisciplina( w[3] );
				map.put( turma.getID(), turma );
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return map;
	}
}
