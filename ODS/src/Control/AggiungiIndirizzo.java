package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;

/**
 * Servlet implementation class AggiungiIndirizzo a
 */
@WebServlet("/AggiungiIndirizzo")
public class AggiungiIndirizzo extends HttpServlet {
	IndirizzoModel<IndirizzoBean> model = new IndirizzoModelDM();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();  

        String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String citta = request.getParameter("citta");
		String via = request.getParameter("via");
		String cap = request.getParameter("cap");
		String provincia =request.getParameter("provincia");
		String nCel = request.getParameter("cellulare");
		int id = ((int)request.getSession().getAttribute("id"));
		IndirizzoBean indirizzo = new IndirizzoBean();
		indirizzo.setNome(nome);
		indirizzo.setCognome(cognome);
		indirizzo.setCitta(citta);
		indirizzo.setVia(via);
		indirizzo.setCap(cap);
		indirizzo.setProvincia(provincia);
		indirizzo.setCellulare(nCel);
	    indirizzo.setUtente(id);	
		try {
			boolean esiste = false;
			for(IndirizzoBean b : model.doRetrieveByUtente(id)){
				if(b.equals(indirizzo))
					esiste = true;
			}
			if(!esiste){
				model.doSave(indirizzo);
				//Dispatch into success page
		    	 out.print("<p style=\"color:green\">Indirizzo Aggiunto con successo</p><br>");  
		
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("mostra_indirizzi.jsp");
				requestDispatcher.forward(request, response);
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("mostra_indirizzi.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// err page
			e.printStackTrace();
		}


	}
}