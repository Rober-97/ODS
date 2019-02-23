package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class VerificaCarta
 */
@WebServlet("/VerificaCarta")
public class VerificaCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String flag = request.getParameter("carta");
		if (flag != null) {
			System.out.println(flag);
			CartaDiCreditoModelDM model = new CartaDiCreditoModelDM();
			CartaDiCreditoBean carta = new CartaDiCreditoBean();
			try {
				carta = model.doRetrieveByKey(flag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("carta", carta);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/riepilogo_ordine.jsp");
			dispatcher.forward(request, response);
		}
		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Seleziona una carta');");
			out.println("location='carrello_carta.jsp';");
			out.println("</script>");
		}   
	}
}
