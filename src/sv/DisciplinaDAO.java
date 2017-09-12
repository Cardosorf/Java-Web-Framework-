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

public class DisciplinaDAO {

	static private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/disciplinas.txt";

	static private Map<Integer, Disciplina> disciplinas = leDisciplinas( path );

	public static void salva( Map<Integer, Disciplina> map ) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for(int user : map.keySet()) {
				writer.write( map.get(user).getID()+ "|" + map.get(user).getNome());
				writer.newLine();
			}
			writer.close();
			disciplinas = leDisciplinas( path );   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Disciplina recupera( int ID ) {
		return disciplinas.get( ID );
	}


	public static Map<Integer, Disciplina> getDisciplinas() {
		return disciplinas;
	}


	static Map<Integer, Disciplina> leDisciplinas( String arquivo ) {
		Map<Integer, Disciplina> map = new TreeMap<>();
		List<String> linhas;
		try {
			linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );
			for( String linha : linhas ) {
				String w[] = linha.split( "[|]" );
				Disciplina disciplina = new Disciplina();
				disciplina.setID( w[0] );
				disciplina.setNome( w[1] );
				map.put( disciplina.getID(), disciplina );
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return map;
	}
}
