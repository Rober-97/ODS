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

/**
 * Servlet implementation class InsPrezzoControl
 */
@WebServlet("/InsPrezzoControl")
public class InsPrezzoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProdottoModel<ProdottoBean> model = new ProdottoModelDM();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id_prodotto"));
     	float prezzo = Float.parseFloat(request.getParameter("prezzo"));
     	try {
     		ProdottoBean prodotto = model.doRetrieveByKey(id);
     		prodotto.setPrezzoCompl(prezzo);
			prodotto.setIva(22);
			prodotto.setInVendita(true);
     		model.doUpdate(prodotto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
     	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodottiSenzaPrezzo.jsp");
		dispatcher.forward(request, response); // passo la chiamata alla jsp;
	}
}