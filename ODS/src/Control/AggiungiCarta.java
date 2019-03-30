package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

@WebServlet("/AggiungiCarta")
public class AggiungiCarta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CartaDiCreditoModel<CartaDiCreditoBean> model = new CartaDiCreditoModelDM();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//aggiungere controllo log e conferma aggiunta carta
		CartaDiCreditoBean carta = new CartaDiCreditoBean();
		int id = ((int) request.getSession().getAttribute("id"));
		carta.setUtente(id);
		carta.setNumeroCarta(request.getParameter("numero_carta"));
		carta.setCvv(request.getParameter("cvv"));
		carta.setCognomeProprietario(request.getParameter("cognome"));
		int anno=Integer.parseInt(request.getParameter("anno"));
		int mese=Integer.parseInt(request.getParameter("mese"));
		int giorno=Integer.parseInt(request.getParameter("giorno"));
		@SuppressWarnings("deprecation")
		Date data=new Date(anno-1900,mese-1,giorno+1);
		carta.setDataScadenza(data);
		carta.setNomeProprietario((request.getParameter("nome")));
		boolean esiste = false;
		try {
			for(CartaDiCreditoBean b : model.doRetrieveByUtente(id)){
				if(b.equals(carta))
					esiste = true;
			}
			if(!esiste){
				model.doSave(carta);
				out.print("<p style=\"color:green\">Carta aggiunta con successo</p><br>");
			}
		} catch (SQLException e) {
			// aggiungere dispatch alla pagina di errore
			e.printStackTrace();
		}
		request.setAttribute("carta", carta);
		RequestDispatcher rd = request.getRequestDispatcher("/mostra_carte.jsp");  
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}