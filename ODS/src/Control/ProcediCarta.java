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

@WebServlet("/ProcediCarta")
public class ProcediCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartaDiCreditoModel model = new CartaDiCreditoModelDM();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//aggiungere controllo log
		String numeroCarta = (String) request.getParameter("carta");
		CartaDiCreditoBean carta = null;
		try{
			carta = (CartaDiCreditoBean) model.doRetrieveByKey(numeroCarta);
		} catch(Exception e){
			e.printStackTrace();
			//inserire dispatch alla pagina di errore
		}
		request.getSession().setAttribute("carta", carta);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp");
		dispatcher.forward(request, response);
	}
}
