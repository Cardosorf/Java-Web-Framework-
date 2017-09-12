package sv;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CadastroAluno extends Cadastro implements Controle {

	private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/alunos.txt";

	@Acao( form = FormAluno.class )
	public String salvar( FormAluno form ) {
		Map<Integer, Aluno> map = new TreeMap<>();
		map = AlunoDAO.leAlunos(path);
		for(int user : map.keySet()){
			if(map.get(user).getID() == form.getID()){
				map.get(user).setNome(form.getNome());
				map.get(user).setCelular(form.getCelular());
				map.get(user).setEmail(form.getEmail());
			}
		}
		AlunoDAO.salva(map);
		System.out.println( "SALVOU" );
		return "./WEB-INF/ok.jsp";
	}


	@Override
	public Object preencheForm( int id ) {
		FormAluno form = new FormAluno();
		Aluno aluno = AlunoDAO.recupera( id );
		form.setID( "" + aluno.getID() );
		form.setCelular( aluno.getCelular() );
		form.setEmail( aluno.getEmail() );
		form.setNome( aluno.getNome() );
		return form;
	}


	@Acao( form = FormListar.class )
	public String listar(FormListar form){
		ArrayList<String> nome = new ArrayList<>();
		for( Aluno a : AlunoDAO.getAlunos().values() ){
			nome.add( "<a href=\"SistaCad?control=sv.Cadastro&action=exibir&entidade=Aluno&ID=" + a.getID() + "\">"
					+ a.getNome() + "</a>" + "    " + "<a href=\"SistaCad?control=sv.Cadastro&action=deletar&ID=" + a.getID() + "&entidade=");
		}
		form.setNome( nome );
		form.setSize( nome.size() );
		return "./WEB-INF/listar.jsp";
	}


	@Acao( form = FormAluno.class )
	public String adicionar( FormAluno form ) {
		Map<Integer, Aluno> map = new TreeMap<>();
		map = AlunoDAO.leAlunos(path);
		int valueID =form.getID();
		for(Integer m : map.keySet())
			valueID = m.intValue();
		form.setID(((Integer)(valueID+1)).toString());
		Aluno aluno = new Aluno();
		aluno.setID( ((Integer)form.getID()).toString() );
		aluno.setNome( form.getNome() );
		aluno.setCelular( form.getCelular() );
		aluno.setEmail( form.getEmail() );
		map.put(form.getID(), aluno);
		AlunoDAO.salva(map);
		System.out.println( "CADASTROU" );
		return "./WEB-INF/ok.jsp";
	}


	@Acao( form = FormAluno.class )
	public String remover( FormAluno form ) {
		Map<Integer, Aluno> map = new TreeMap<>();
		map = AlunoDAO.leAlunos(path);
		map.remove(form.getID());
		AlunoDAO.salva(map);
		System.out.println( "REMOVEU" );
		return "./WEB-INF/ok.jsp";
	}
}
