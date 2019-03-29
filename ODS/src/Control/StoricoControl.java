package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class StoricoControl
 */
@WebServlet("/StoricoControl")
public class StoricoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("id") != null){
			int id_utente = (int) request.getSession().getAttribute("id");
			OrdineModel oModel = new OrdineModelDM();
			Collection<OrdineBean> listaOrdini = new ArrayList<OrdineBean>();
			try {
				listaOrdini = oModel.doRetrieveByUtente(id_utente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("listaOrdini", listaOrdini);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/storico.jsp");
			dispatcher.forward(request, response); // passo la chiamata alla jsp
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response); // passo la chiamata alla jsp
		}
	}
}