package Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class CartControl
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static ProdottoModel<ProdottoBean> model = new ProdottoModelDM();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("cart", cart);
		}
		
		String invia = request.getParameter("invia");
		if(invia != null) {
			if(invia.equals("Aggiungi al carrello")) {
				ProdottoBean prod = (ProdottoBean) request.getSession().getAttribute("product");
				String taglia = (String) request.getParameter("beantype");
				ProdottoInCarrello prodotto = new ProdottoInCarrello(prod);
				prodotto.setQuantita(1);
				prodotto.setTaglia(taglia);
				cart.addProd(prodotto);
			} else if(invia.equalsIgnoreCase("rimuovi")) {
				int id = Integer.parseInt(request.getParameter("id"));
				cart.rimElemento(id);
			}				
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response); // passo la chiamata alla jsp, da rivedere
		}
		request.getSession().setAttribute("cart", cart);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
		dispatcher.forward(request, response); // passo la chiamata alla jsp	
	}
}