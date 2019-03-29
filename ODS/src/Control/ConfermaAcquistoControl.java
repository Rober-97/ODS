package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

@WebServlet("/ConfermaAcquistoControl")
public class ConfermaAcquistoControl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		IndirizzoBean indirizzo = null;
		CartaDiCreditoBean carta = null;
		int utente;
		Carrello cart = null;
		
		if(request.getSession().getAttribute("indirizzo") != null){
			indirizzo = (IndirizzoBean) request.getSession().getAttribute("indirizzo");
		
			if(request.getSession().getAttribute("carta") != null){
				carta = (CartaDiCreditoBean) request.getSession().getAttribute("carta");
		
				if(request.getSession().getAttribute("id") != null){
					utente = (int) request.getSession().getAttribute("id");
		
					if(request.getSession().getAttribute("cart") != null){
						cart = (Carrello) request.getSession().getAttribute("cart");
						try {
							cart.acquista(carta.getNumeroCarta(), utente, indirizzo.getIdIndirizzo());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.getSession().removeAttribute("indirizzo");
						request.getSession().removeAttribute("indirizzi");
						request.getSession().removeAttribute("carta");
						request.getSession().removeAttribute("carte");
						request.getSession().removeAttribute("cart");
						request.getSession().setAttribute("cart", cart);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("acquisto_completato.jsp");
						dispatcher.forward(request, response); // passo la chiamata alla jsp
					}
				}
			}
		}
	}
}