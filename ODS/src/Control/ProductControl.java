package Control;
import Model.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel<ProdottoBean> model = new ProdottoModelDM();
	static TagliaModel<TagliaBean> model1 = new TagliaModelDM();

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Carrello<ProdottoBean>();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("detail")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("product", model.doRetrieveByKey(id));
					request.getRequestDispatcher("visualizzaProdotto.jsp").forward(request, response);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			//inserire dispatch alla jsp di errore 
		}
	}
}