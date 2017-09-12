import java.io.*;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.*;
import javax.servlet.http.*;

public class Validate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties properties;
	private ConcurrentMap<String, String> users;
	private ConcurrentMap<String, Integer> access;


	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try{
			File file = new File(config.getServletContext().getRealPath(""),"WEB-INF//config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			populateMaps();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter o = response.getWriter();
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");

		try{
			/*
			 * Check if a member exists. If a wrong password was submitted, then it verifies
			 * if the number of login's attempts of this user. If it exceeds 3, then the user
			 * is blocked. Otherwise, he'll be redirected to the access failure page and will
			 * be able to try to login again.
			 */
			if(validateUserPassword(userName, userPassword)){
				access.put(userName, access.get(userName)+1);
				if(access.get(userName) >= 3){
					redirectBlockedUser(request, response);
				}
				else{
					redirectWrongUser(request, response, userName);
				}
			}

			/*
			 * Check if the member is registered and if he typed the right password. If so,
			 * the user is redirect to the menu page, unless he has greater than or equal 
			 * to three login's attempts. In this case, the user will be redirected to the 
			 * user's blocked page.
			 */
			else if(validateUser(userName, userPassword)){
				if(access.get(userName) < 3)
					redirectWelcomeUser(request, response, userName);
				else
					redirectBlockedUser(request, response);
			}

			/*
			 * If a unregistered member tries to login, he'll be registered and then
			 * redirected to the access failure page. If the same member tries to login
			 * up to 3 times, he'll be redirected to the user's blocked page.
			 */
			else{

				if(access.get(userName) == null){
					access.put(userName, 1);
					redirectWrongUser(request, response, userName);
				}

				else if(access.get(userName) != null){
					if(access.get(userName) >= 2){
						redirectBlockedUser(request, response);
					}
					else{
						access.put(userName, access.get(userName)+1);
						redirectWrongUser(request, response, userName);
					}
				}
			}
		}	 catch(Exception e){
			o.println(e.getMessage());
		}
	}


	private void redirectWrongUser(HttpServletRequest request, HttpServletResponse response, String userName)
			throws ServletException, IOException {
		request.setAttribute( "accessError", "Usuario ou senha invalidos. Por favor, tente novamente.");
		request.setAttribute( "numberAttempts", "Voce tem " + ((Integer)3 - access.get(userName)) + " tentaviva(s).");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}


	private void redirectBlockedUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute( "loginError", "Excedeu o limite de tentativas. Por favor, contate o suporte para remover essa penalidade ou tente mais tarde.");
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/failure.jsp");
		rd.forward(request, response);
	}


	private void redirectWelcomeUser(HttpServletRequest request, HttpServletResponse response, String userName)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("user", userName);
		access.put(userName, 0);
		response.sendRedirect("SistaCad");
	}


	private boolean validateUserPassword(String userName, String userPassword) {
		return users.containsKey(userName) && !users.get(userName).equals(userPassword);
	}


	private boolean validateUser(String userName, String userPassword) {
		return users.containsKey(userName) && users.get(userName).equals(userPassword);
	}


	private void populateMaps() {
		access = new ConcurrentHashMap<String, Integer>();
		users = new ConcurrentHashMap<String, String>();
		for(String key: properties.stringPropertyNames()){
			users.put(key, properties.getProperty(key));
			access.put(key, 0);
		}
	}
}