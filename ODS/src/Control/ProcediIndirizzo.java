package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

@WebServlet("/ProcediIndirizzo")
public class ProcediIndirizzo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	CartaDiCreditoModel model = new CartaDiCreditoModelDM();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//aggiungere controllo log
		int idIndirizzo = Integer.parseInt(request.getParameter("indirizzo"));
		request.getSession().setAttribute("idIndirizzo", idIndirizzo);
		
		int idUtente = (int) request.getSession().getAttribute("id");
		Collection<CartaDiCreditoBean> carte = null;
		try {
			carte = model.doRetrieveByUtente(idUtente);
		} catch (SQLException e) {
			e.printStackTrace();
			//aggiungere dispatch alla pagina di errore
		}
		request.setAttribute("carte", carte);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("carrello_carta.jsp");
		dispatcher.forward(request, response);			
	}
}
