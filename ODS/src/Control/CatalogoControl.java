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

/**
 * Servlet implementation class CategoriaControl
 */
@WebServlet("/CatalogoControl")
public class CatalogoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
     ProdottoModelDM model = new ProdottoModelDM();
     Collection<ProdottoBean> lista = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoria = request.getParameter("categoria");
		try {
			lista = model.doRetrieveByCategory(categoria);
		} catch (SQLException e) {
			e.printStackTrace();
			//inserire dispatch alla pagina di errore
		}
		request.setAttribute("prodotti", lista);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response); // passo la chiamata alla jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}