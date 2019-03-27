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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    boolean amministratore;
	    String email = request.getParameter("email");  
	    String password = request.getParameter("password");

        try {
			UtenteBean utente = null;
			utente = model.validate(email, password);
			
			if(utente != null){				
//				request.getSession().setAttribute("email", email);  da controllare
				request.getSession().setAttribute("id", utente.getIdUtente()); //setto l'id in sessione
//				request.setAttribute("nome", utente.getNome()); //setto il nome nella richiesta per la jsp; da ricontrollare
				amministratore = utente.isAmministratore();
				request.getSession().setAttribute("amministratore", amministratore);
				if(amministratore){
					RequestDispatcher rd=request.getRequestDispatcher("/magazzinierePage.jsp");  
					rd.forward(request,response);
				} else {
					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
					rd.forward(request,response);
				}
			} else {  
			    out.print("<p style=\"color:red\">Spiacente E-Mail o password invalidi, riprova</p><br>");  
			    out.print("<p style=\"color:blue\">Nuovo utente? <a href=\"registrazione.jsp\">Registrati subito!</a> </br></p>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.close();  
    }
}