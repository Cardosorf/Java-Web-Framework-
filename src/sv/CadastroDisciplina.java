package sv;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CadastroDisciplina extends Cadastro implements Controle {
	private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/disciplinas.txt";

	@Acao( form = FormDisciplina.class )
	public String salvar( FormDisciplina form ) {
		Map<Integer, Disciplina> map = new TreeMap<>();
		map = DisciplinaDAO.leDisciplinas(path);
		for(int user : map.keySet())
			if(map.get(user).getID() == form.getID())
				map.get(user).setNome(form.getNome());
		DisciplinaDAO.salva(map);
		System.out.println( "SALVOU" );
		return "./WEB-INF/ok.jsp";
	}


	@Override
	public Object preencheForm( int id ) {
		FormDisciplina form = new FormDisciplina();
		Disciplina disciplina = DisciplinaDAO.recupera( id );
		form.setID( "" + disciplina.getID() );
		form.setNome( disciplina.getNome() );
		return form;
	}


	@Acao( form = FormListar.class )
	public String listar(FormListar form){
		ArrayList<String> nome = new ArrayList<>();
		for( Disciplina a : DisciplinaDAO.getDisciplinas().values() ){
			nome.add( "<a href=\"SistaCad?control=sv.Cadastro&action=exibir&entidade=Disciplina&ID=" + a.getID() + "\">"
					+ a.getNome() + "</a>" + "    " + "<a href=\"SistaCad?control=sv.Cadastro&action=deletar&ID=" + a.getID() + "&entidade=");
		}
		form.setNome( nome );
		form.setSize( nome.size() );
		return "./WEB-INF/listar.jsp";
	}


	@Acao( form = FormDisciplina.class )
	public String adicionar( FormDisciplina form ) {
		Map<Integer, Disciplina> map = new TreeMap<>();
		map = DisciplinaDAO.leDisciplinas(path);
		int valueID =form.getID();
		for(Integer m : map.keySet()){
			valueID = m.intValue();
		}
		form.setID(((Integer)(valueID+1)).toString());
		Disciplina disciplina = new Disciplina();
		disciplina.setID( ((Integer)form.getID()).toString() );
		disciplina.setNome( form.getNome() );
		map.put(form.getID(), disciplina);
		DisciplinaDAO.salva(map);
		System.out.println( "CADASTROU" );
		return "./WEB-INF/ok.jsp";
	}


	@Acao( form = FormDisciplina.class )
	public String remover( FormDisciplina form ) {
		Map<Integer, Disciplina> map = new TreeMap<>();
		map = DisciplinaDAO.leDisciplinas(path);
		map.remove(form.getID());
		DisciplinaDAO.salva(map);
		System.out.println( "REMOVEU" );
		return "./WEB-INF/ok.jsp";
	}
}
