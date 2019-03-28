package Control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * implementa la registrazione, è chiamata da registrazione.jsp
 * da ricontrollare
 */
@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UtenteModel<UtenteBean> model = new UtenteModelDM();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String sData= request.getParameter("data_nascita");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date data_nascita = new Date(formatter.parse(sData).getTime());

				String password = request.getParameter("password");
				String eMail = request.getParameter("email");					
			
				UtenteBean newUser = new UtenteBean();
				newUser.setNome(nome);
				newUser.setCognome(cognome);
				newUser.setEmail(eMail);
				newUser.setPassword(password);
				newUser.setDataNascita(data_nascita);
				newUser.setAmministratore(false);
				
				model.doSave(newUser);
//				request.getSession().setAttribute("email", eMail); da controllare
				request.getSession().setAttribute("amministratore", newUser.isAmministratore());
				request.getSession().setAttribute("id", newUser.getIdUtente());
					
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrationSuccess.jsp");
				dispatcher.forward(request, response);				
		}catch (SQLException e) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrationFailed.jsp");
			dispatcher.forward(request, response);
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrationFailed.jsp");
			dispatcher.forward(request, response);

			e.printStackTrace();
		}						
	}
}
