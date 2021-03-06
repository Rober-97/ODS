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
			} else if(action.equalsIgnoreCase("insert")) {
				
				int id = Integer.parseInt(request.getParameter("Id"));
				String codice= request.getParameter("codice");
				String descrizione= request.getParameter("description");
				String marca = request.getParameter("marca");
				String modello = request.getParameter("modello");
				String foto = request.getParameter("foto");
				String categoria = request.getParameter("categoria");
				int ivaV = Integer.parseInt(request.getParameter("ivaVendita"));     //iva vendita;
				float prezzoV =Float.parseFloat(request.getParameter("prezzoVendita")); //prezzovendita
				int codC= Integer.parseInt(request.getParameter("codiceC")); //codice categoria
				int quantita= Integer.parseInt(request.getParameter("quantity"));
				ProdottoBean bean = new ProdottoBean();
				bean.setIdProdotto(id);
				bean.setCodiceProdotto(codice);
				bean.setDescrizione(descrizione);
				bean.setMarca(marca);
				bean.setModello(modello);
				bean.setFoto(foto);
				bean.setCategoria(categoria);
				((ProdottoBean)bean).setPrezzoCompl(prezzoV);
				((ProdottoBean)bean).setIva(ivaV);
				((ProdottoInOrdineBean)bean).setQuantita(quantita);				

				model.doSave(bean);
				request.getRequestDispatcher("amministratorePage.jsp").forward(request, response);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			//inserire dispatch alla jsp di errore 
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}