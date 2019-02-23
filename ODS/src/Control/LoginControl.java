package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UtenteModel<UtenteBean> model = new UtenteModelDM();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    boolean isAmministratore;
	    String n=request.getParameter("email");  
	    String p=request.getParameter("password");

        try {
			if(UtenteModelDM.validate(n, p)){
				UtenteBean utente = new UtenteBean();
				utente = model.doRetrieveByEmail(n);
				
				request.getSession().setAttribute("email", n);
				request.getSession().setAttribute("id", utente.getId_utente()); //assegno l'id
				request.getSession().setAttribute("nome", utente.getNome());

				isAmministratore = utente.isAmministratore();
				request.getSession().setAttribute("isAmministratore", isAmministratore);
				if(isAmministratore){
					RequestDispatcher rd=request.getRequestDispatcher("/magazzinierePage.jsp");  
					rd.forward(request,response);
				} else {
					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
					rd.forward(request,response);
				}
			} else {  
			    out.print("<p style=\"color:red\">Spiacente E-Mail o password invalidi, riprova</p><br>");  
			    out.print("<p style=\"color:blue\">Nuovo utente? <a href=\"registrazione.jsp\">Registrati subito!</a> </br></p>");
			    request.getSession().removeAttribute("tipo");

			    RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
			    rd.include(request,response);  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

        out.close();  
    }  

}
