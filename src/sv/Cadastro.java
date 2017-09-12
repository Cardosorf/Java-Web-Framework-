package sv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import sv.Campo;

public class Cadastro {

	@Acao( form = FormListar.class )
	public String listar( FormListar form ) throws Exception {
		System.out.println( "Entidade " + form.getEntidade() );
		String proximaPagina = "";
		try {
			Class<?> classeDoCadastro = Class.forName( "sv.Cadastro" + form.getEntidade() );
			Controle controle = (Controle) classeDoCadastro.newInstance();
			Method metodos[] = controle.getClass().getDeclaredMethods();
			for( Method m : metodos ){
				if(m.getName().equals( "listar" )){
					proximaPagina = m.invoke(controle, form).toString();
				}
			}
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
			throw new ClassNotFoundException("Classe nao encontrada");
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return proximaPagina; 
	}


	@Acao( form = FormExibir.class )
	public String exibir( FormExibir form ) throws Exception {
		try {
			Class<?> classeDoCadastro = Class.forName( "sv.Cadastro" + form.getEntidade() );
			Controle controle = (Controle) classeDoCadastro.newInstance();
			Object formEntidade = controle.preencheForm( Integer.valueOf( form.getId() ) );
			Map<String, String> campos = new TreeMap<>();
			for( Method m : formEntidade.getClass().getMethods() )
				if( m.isAnnotationPresent( Campo.class ) ) {
					String label = m.getName().substring( 3 );
					campos.put( label, chamaGetter( formEntidade, "get" + label ) );
				}
			form.setCampos( campos );
		} catch( Exception e ) {
			e.printStackTrace();
			throw new Exception( "O nome do metodo anotado por @Campo deve comecar por 'set'" );
		}
		if( form.getEditar().equals( "true" ) )
			return "./WEB-INF/editar.jsp";
		else
			return "./WEB-INF/exibir.jsp";
	}


	private String chamaGetter( Object formEntidade, String nomeMetodo ) throws NoSuchMethodException, SecurityException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = formEntidade.getClass().getMethod( nomeMetodo );
		return m.invoke( formEntidade ).toString();
	}


	public Object preencheForm( Class<?> formClass, Map<String, String> param, Method match ) throws Exception {
		Object form = formClass.newInstance();
		for( Method m : formClass.getMethods() ){
			if(match.getName().equals("salvar")){
				if(m.getName().equals("setID")){
					String parametro = m.getName().replaceAll("set", "").toLowerCase();
					String vParametro = param.get(parametro);
					m.invoke(form, vParametro);
				}
			}
			if( m.getAnnotation( Campo.class ) != null )
				if( m.getName().substring( 0, 3 ).equals( "set" ) ) {
					String nomeParam = m.getName().substring( 3 ).toLowerCase();
					String valorParam = param.get( nomeParam );
					if( m.getAnnotation( Campo.class ).obrigatorio() && (valorParam == null || valorParam.trim().equals( "" )) )
						throw new CampoNaoPreenchidoException( nomeParam );
					else
						m.invoke( form, valorParam );
				} else
					throw new Exception( "O nome do metodo anotado por @Campo deve comecar por 'set'" );
		}
		return form;
	}


	@Acao( form = FormCampos.class )
	public String cadastrar( FormCampos form ) throws Exception {
		try {
			Class<?> classeDoCadastro = Class.forName( "sv.Form" + form.getEntidade() );
			Map<String, String> campos = new TreeMap<>();
			for( Method m : classeDoCadastro.getMethods() )
				if( m.isAnnotationPresent( Campo.class ) ) {
					String label = m.getName().substring( 3 );
					campos.put( label, "" );
				}
			form.setCampos( campos );
		}  catch( Exception e ) {
			e.printStackTrace();
			throw new Exception( "O nome do metodo anotado por @Campo deve comecar por 'set'" );
		}
		return "./WEB-INF/cadastrar.jsp";
	}

	@Acao( form = FormExibir.class )
	public String deletar( FormExibir form ) throws Exception {
		System.out.println( "Entidade " + form.getEntidade() );
		String proximaPagina = "";
		try {
			Class<?> classeDoCadastro = Class.forName( "sv.Cadastro" + form.getEntidade() );
			Controle controle = (Controle) classeDoCadastro.newInstance();
			Object formEntidade = controle.preencheForm( Integer.valueOf( form.getId() ) );
			Method metodos[] = controle.getClass().getDeclaredMethods();
			Map<String, String> campos = new TreeMap<>();
			for( Method m : formEntidade.getClass().getMethods() )
				if( m.isAnnotationPresent( Campo.class ) ) {
					String label = m.getName().substring( 3 );
					campos.put( label, chamaGetter( formEntidade, "get" + label ) );
				}
			form.setCampos( campos );
			for( Method m : metodos )
				if(m.getName().equals( "remover" ))
					proximaPagina = m.invoke(controle, formEntidade).toString();
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
			throw new ClassNotFoundException("Classe nao encontrada");
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return proximaPagina; 
	}
}
