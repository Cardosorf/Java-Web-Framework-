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

public class AlunoDAO {

	static private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/alunos.txt";

	static private Map<Integer, Aluno> alunos = leAlunos( path );

	public static void salva( Map<Integer, Aluno> map ) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for(int user : map.keySet()) {
				writer.write( map.get(user).getID()+ "|" + map.get(user).getNome()+ "|" 
						+ map.get(user).getCelular()+ "|" + map.get(user).getEmail());
				writer.newLine();
			}
			writer.close();
			alunos = leAlunos( path );   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Aluno recupera( int ID ) {
		return alunos.get( ID );
	}


	public static Map<Integer, Aluno> getAlunos() {
		return alunos;
	}


	static Map<Integer, Aluno> leAlunos( String arquivo ) {
		Map<Integer, Aluno> map = new TreeMap<>();
		List<String> linhas;
		try {
			linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );
			for( String linha : linhas ) {
				String w[] = linha.split( "[|]" );
				Aluno aluno = new Aluno();
				aluno.setID( w[0] );
				aluno.setNome( w[1] );
				aluno.setCelular( w[2] );
				aluno.setEmail( w[3] );
				map.put( aluno.getID(), aluno );
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return map;
	}
}
