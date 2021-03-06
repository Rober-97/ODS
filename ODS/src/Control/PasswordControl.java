package Control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class PasswordControl
 */
@WebServlet("/PasswordControl")
public class PasswordControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtenteModel<UtenteBean> model = new UtenteModelDM();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) (request.getSession().getAttribute("id"));
		String password = request.getParameter("password");
        try {
        	UtenteBean u = model.doRetrieveByKey(id);
        	u.setPassword(password);
        	model.doUpdate(u);
        } catch (SQLException e) {
			//
        	e.printStackTrace();
		}
     	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/area_utente.jsp");
     	dispatcher.forward(request, response); // passo la chiamata alla jsp;    
	}
}