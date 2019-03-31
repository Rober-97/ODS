package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.*;

@WebServlet("/CercaTagliaControl")
public class CercaTagliaControl extends HttpServlet {
	TagliaModel model = new TagliaModelDM();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        int id = Integer.parseInt(request.getParameter("idProdotto"));
        Collection<TagliaBean> taglie = new ArrayList();
        try {
			taglie = model.doRetrieveByProdotto(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        List<String> t = new ArrayList<String>();
        for(TagliaBean b : taglie){
        	System.out.println(b.getTaglia());
        	t.add(b.getTaglia());
        }
        response.getWriter().write(new Gson().toJson(t));
	}
}