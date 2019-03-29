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
	CartaDiCreditoModel cModel = new CartaDiCreditoModelDM();
	IndirizzoModelDM iModel = new IndirizzoModelDM();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//aggiungere controllo log
		int idIndirizzo = Integer.parseInt(request.getParameter("indirizzo"));
		IndirizzoBean indirizzo = null;
		
		int idUtente = (int) request.getSession().getAttribute("id");
		Collection<CartaDiCreditoBean> carte = null;
		try {
			indirizzo = iModel.doRetrieveByKey(idIndirizzo);
			carte = cModel.doRetrieveByUtente(idUtente);
		} catch (SQLException e) {
			e.printStackTrace();
			//aggiungere dispatch alla pagina di errore
		}
		request.getSession().setAttribute("indirizzo", indirizzo);
		
		request.getSession().setAttribute("carte", carte);
		RequestDispatcher dispatcher = request.getRequestDispatcher("carrello_carta.jsp");
		dispatcher.forward(request, response);			
	}
}
