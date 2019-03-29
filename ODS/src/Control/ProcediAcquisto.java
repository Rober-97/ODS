package Control;
import Model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcediAcquisto
 */
@WebServlet("/ProcediAcquisto")
public class ProcediAcquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello<ProdottoInCarrello> cart = (Carrello<ProdottoInCarrello>) request.getSession().getAttribute("cart");
		IndirizzoModel model = new IndirizzoModelDM();
		HttpSession session = request.getSession();
		
		if((cart == null) || (cart.isEmpty())){
			RequestDispatcher dispatcher = request.getRequestDispatcher("carrello.jsp");
			dispatcher.forward(request, response);
		} else if(session.getAttribute("id") == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			int id = (int) session.getAttribute("id");
			Collection<IndirizzoBean> indirizzi = new ArrayList<IndirizzoBean>();
			try {
				indirizzi = model.doRetrieveByUtente(id);
			} catch (SQLException e) {
				e.printStackTrace(); // aggiungere dispatch alla pagina di errore
			}
			request.getSession().setAttribute("indirizzi", indirizzi);
			RequestDispatcher dispatcher = request.getRequestDispatcher("carrello_indirizzo.jsp");
			dispatcher.forward(request, response);			
		}
	}	
}