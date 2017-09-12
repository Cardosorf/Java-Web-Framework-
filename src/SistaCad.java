import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.Acao;
import sv.CampoNaoPreenchidoException;
import sv.CampoProfessorException;
import sv.Cadastro;
import sv.CampoDisciplinaException;

/**
 * Servlet implementation class SistaCad
 */
@WebServlet( "/SistaCad" )
public class SistaCad extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		doGetPost( request, response );
	}


	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		doGetPost( request, response );
	}


	protected void doGetPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String control = request.getParameter( "control" );
		String action = request.getParameter( "action" );
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		String proximaPagina = "/index.jsp";

		if(isLogged(user, action, session)){
			if( isValid( control ) && isValid( action ) ) {
				try {
					proximaPagina = doAction( request, control, action );
				}catch( Exception e ) {
					e.printStackTrace();
				}
			}
			else{
				proximaPagina = "./WEB-INF/menu.jsp";
			}
		}
		else{
			proximaPagina = "/index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher( proximaPagina );
		rd.forward( request, response );
	}


	private boolean isValid( String name ) {
		return name != null && !name.trim().equals( "" );
	}


	private boolean isLogged(String name, String action, HttpSession session){
		if(isValid(action) && name != null){
			if(action.equals("logout")){
				session.invalidate();
				return false;
			}
		}
		return name == null ? false : true;
	}


	private String doAction( HttpServletRequest request, String control, String action ) throws Exception {
		Class<?> controlClass;
		try {
			controlClass = Class.forName( control );
			Map<String, String> params = filtraParams( request );
			Cadastro controlObject = (Cadastro) controlClass.newInstance();
			System.out.println( "Objeto criado: " + controlObject.getClass().getName() );
			Method metodos[] = controlClass.getDeclaredMethods();
			for( Method m : metodos )
				if( m.getName().equals( action ) )
					if( m.isAnnotationPresent( Acao.class ) ) {
						Class<?> formClass = m.getAnnotation( Acao.class ).form();
						String proximaPagina = "";
						if( formClass != Object.class ) {
							Object form = controlObject.preencheForm( formClass, params, m );
							proximaPagina = m.invoke( controlObject, form ).toString();
							request.setAttribute( "form", form );
						} else {
							proximaPagina = m.invoke( controlObject ).toString();
						}
						return proximaPagina;
					} else {
						return "./WEB-INF/AcessoNegado.jsp";
					}
		} catch (InvocationTargetException e){
			if(e.getCause() instanceof CampoProfessorException){
				e.printStackTrace();
				request.setAttribute("erro", e.getCause());
				return "./WEB-INF/erro.jsp";
			}
			if(e.getCause() instanceof CampoDisciplinaException){
				e.printStackTrace();
				request.setAttribute("erro", e.getCause());
				return "./WEB-INF/erro.jsp";
			}
		} catch( CampoNaoPreenchidoException e ) {
			e.printStackTrace();
			request.setAttribute( "erro", e );
			return "./WEB-INF/erro.jsp";
		} catch( Exception e ) {
			e.printStackTrace();
			request.setAttribute( "erro", e );
			return "./WEB-INF/erro.jsp";
		}
		return "";
	}


	private Map<String, String> filtraParams( HttpServletRequest request ) {
		TreeMap<String, String> map = new TreeMap<>();
		for( String param : request.getParameterMap().keySet() )
			if( !param.equals( "control" ) && !param.equals( "action" ) )
				map.put( param.toLowerCase(), request.getParameter( param ) );
		return map;
	}
}
