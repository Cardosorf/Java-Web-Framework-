package sv;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CadastroProfessor extends Cadastro implements Controle{

	private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/professores.txt";

	@Acao( form = FormProfessor.class )
	public String salvar( FormProfessor form ) {
		Map<Integer, Professor> map = new TreeMap<>();
		map = ProfessorDAO.leProfessores(path);
		for(int user : map.keySet())
			if(map.get(user).getID() == form.getID()){
				map.get(user).setNome(form.getNome());
				map.get(user).setCelular(form.getCelular());
				map.get(user).setEmail(form.getEmail());
			}
		ProfessorDAO.salva(map);
		System.out.println( "SALVOU" );
		return "./WEB-INF/ok.jsp";
	}


	@Override
	public Object preencheForm( int id ) {
		FormProfessor form = new FormProfessor();
		Professor professor = ProfessorDAO.recupera( id );
		form.setID( "" + professor.getID() );
		form.setCelular( professor.getCelular() );
		form.setEmail( professor.getEmail() );
		form.setNome( professor.getNome() );
		return form;
	}


	@Acao( form = FormListar.class )
	public String listar(FormListar form){
		ArrayList<String> nome = new ArrayList<>();
		for( Professor a : ProfessorDAO.getProfessores().values() ){
			nome.add( "<a href=\"SistaCad?control=sv.Cadastro&action=exibir&entidade=Professor&ID=" + a.getID() + "\">"
					+ a.getNome() + "</a>" + "    " + "<a href=\"SistaCad?control=sv.Cadastro&action=deletar&ID=" + a.getID() + "&entidade=");
		}
		form.setNome( nome );
		form.setSize( nome.size() );
		return "./WEB-INF/listar.jsp";
	}


	@Acao( form = FormProfessor.class )
	public String adicionar( FormProfessor form ) {
		Map<Integer, Professor> map = new TreeMap<>();
		map = ProfessorDAO.leProfessores(path);
		int valueID =form.getID();
		for(Integer m : map.keySet())
			valueID = m.intValue();
		form.setID(((Integer)(valueID+1)).toString());
		Professor professor = new Professor();
		professor.setID( ((Integer)form.getID()).toString() );
		professor.setNome( form.getNome() );
		professor.setCelular( form.getCelular() );
		professor.setEmail( form.getEmail() );
		map.put(form.getID(), professor);
		ProfessorDAO.salva(map);
		System.out.println( "CADASTROU" );
		return "./WEB-INF/ok.jsp";
	}


	@Acao( form = FormProfessor.class )
	public String remover( FormProfessor form ) {
		Map<Integer, Professor> map = new TreeMap<>();
		map = ProfessorDAO.leProfessores(path);
		map.remove(form.getID());
		ProfessorDAO.salva(map);
		System.out.println( "REMOVEU" );
		return "./WEB-INF/ok.jsp";
	}
}
