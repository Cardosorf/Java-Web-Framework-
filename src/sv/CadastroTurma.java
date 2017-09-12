package sv;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CadastroTurma extends Cadastro implements Controle{

	private String path = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/turmas.txt";
	static private String pathProfessor = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/professores.txt";
	static private String pathDisciplina = "C:/Users/Rafael/workspace/Tarefa_Final5/WebContent/WEB-INF/disciplinas.txt";

	static private Map<Integer, Professor> professores = ProfessorDAO.leProfessores( pathProfessor );
	static private Map<Integer, Disciplina> disciplinas = DisciplinaDAO.leDisciplinas( pathDisciplina );

	@Acao( form = FormTurma.class )
	public String salvar( FormTurma form ) throws Exception {
		Map<Integer, Turma> map = new TreeMap<>();
		map = TurmaDAO.leTurmas(path);
		ArrayList<String> nomeProfessor = new ArrayList<String>();
		ArrayList<String> nomeDisciplina = new ArrayList<String>();
		professores = ProfessorDAO.leProfessores( pathProfessor );
		disciplinas = DisciplinaDAO.leDisciplinas( pathDisciplina );
		for(int nome : professores.keySet())
			nomeProfessor.add(professores.get(nome).getNome());
		for(int nome : disciplinas.keySet())
			nomeDisciplina.add(disciplinas.get(nome).getNome());
		if(nomeProfessor.contains(form.getProfessor()) && nomeDisciplina.contains(form.getDisciplina())){
			for(int user : map.keySet()){
				if(map.get(user).getID() == form.getID()){
					map.get(user).setNome(form.getNome());
					map.get(user).setProfessor(form.getProfessor());
					map.get(user).setDisciplina(form.getDisciplina());
				}
			}
			TurmaDAO.salva(map);
		}
		if(nomeDisciplina.contains(form.getDisciplina()) == false)
			throw new CampoDisciplinaException( "Disciplina" );
		if(nomeProfessor.contains(form.getProfessor()) == false)
			throw new CampoProfessorException( "Professor" );
		System.out.println( "SALVOU" );
		return "./WEB-INF/ok.jsp";
	}


	@Override
	public Object preencheForm( int id ) {
		FormTurma form = new FormTurma();
		Turma turma = TurmaDAO.recupera( id );
		form.setID( "" + turma.getID() );
		form.setProfessor( turma.getProfessor() );
		form.setDisciplina( turma.getDisciplina() );
		form.setNome( turma.getNome() );
		return form;
	}


	@Acao( form = FormListar.class )
	public String listar(FormListar form){
		ArrayList<String> nome = new ArrayList<>();
		for( Turma a : TurmaDAO.getTurmas().values() ){
			nome.add( "<a href=\"SistaCad?control=sv.Cadastro&action=exibir&entidade=Turma&ID=" + a.getID() + "\">"
					+ a.getNome() + "</a>" + "    " + "<a href=\"SistaCad?control=sv.Cadastro&action=deletar&ID=" + a.getID() + "&entidade=");
		}
		form.setNome( nome );
		form.setSize( nome.size() );
		return "./WEB-INF/listar.jsp";
	}


	@Acao( form = FormTurma.class )
	public String adicionar( FormTurma form ) throws Exception {
		Map<Integer, Turma> map = new TreeMap<>();
		map = TurmaDAO.leTurmas(path);
		int valueID =form.getID();
		for(Integer m : map.keySet())
			valueID = m.intValue();
		ArrayList<String> nomeProfessor = new ArrayList<String>();
		ArrayList<String> nomeDisciplina = new ArrayList<String>();
		professores = ProfessorDAO.leProfessores( pathProfessor );
		disciplinas = DisciplinaDAO.leDisciplinas( pathDisciplina );
		for(int nome : professores.keySet())
			nomeProfessor.add(professores.get(nome).getNome());
		for(int nome : disciplinas.keySet())
			nomeDisciplina.add(disciplinas.get(nome).getNome());
		if(nomeProfessor.contains(form.getProfessor()) && nomeDisciplina.contains(form.getDisciplina())){
			form.setID(((Integer)(valueID+1)).toString());
			Turma turma = new Turma();
			turma.setID( ((Integer)form.getID()).toString() );
			turma.setNome( form.getNome() );
			turma.setProfessor( form.getProfessor() );
			turma.setDisciplina( form.getDisciplina() );
			map.put(form.getID(), turma);
			TurmaDAO.salva(map);   
		}
		if(nomeDisciplina.contains(form.getDisciplina()) == false)
			throw new CampoDisciplinaException( "Disciplina" );
		if(nomeProfessor.contains(form.getProfessor()) == false)
			throw new CampoProfessorException( "Professor" );
		System.out.println( "CADASTROU" );
		return "./WEB-INF/ok.jsp";
	}


	@Acao( form = FormTurma.class )
	public String remover( FormTurma form ) {
		Map<Integer, Turma> map = new TreeMap<>();
		map = TurmaDAO.leTurmas(path);
		map.remove(form.getID());
		TurmaDAO.salva(map);
		System.out.println( "REMOVEU" );
		return "./WEB-INF/ok.jsp";
	}
}
