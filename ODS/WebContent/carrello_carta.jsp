<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/css/bootstrap.min.css" rel="stylesheet" >
<title>CARRELLO - Scegli metodo di pagamento</title>
</head>
<body>

<%@ include file="header.jsp" %>
<%@ page import="java.util.ArrayList, java.util.Iterator, Model.*" %>
<%
if(request.getSession().getAttribute("tipo") != null){
	int tipo = (int) session.getAttribute("tipo");
	if( tipo ==2 || tipo ==3 || tipo == 4)
		response.sendRedirect("index.jsp");
}
%>
<% 
	ArrayList<CartaDiCreditoBean> carte = (ArrayList<CartaDiCreditoBean>) request.getSession().getAttribute("carte");
%>	 

<hr>
<h3 class="scrittacarrello , bordo1" >&emsp; &emsp; &emsp;SCEGLI UNA CARTA DI CREDITO</h3>
<hr>
 <div class="bottoni">
<a href="carrello_indirizzo.jsp" class="shiny-button2" id="dx">
 <strong>INDIETRO</strong>
  </a>



</div>
<%
		if((carte != null) && (carte.size() > 0)) {
			Iterator<CartaDiCreditoBean> it = carte.iterator();
%>
<%		
			while(it.hasNext()) {
				CartaDiCreditoBean bean = (CartaDiCreditoBean) it.next();
%>
 <form action="ProcediCarta">
	 <table class="table">
	  <thead class="thead-dark">
	    <tr>
	    <th></th>
	     <th scope="col">Numero Carta</th>
	      <th scope="col">Data Scandenza</th>
	      <th scope="col">CVV</th>
	      <th scope="col">Nome Proprietario</th>
	      <th scope="col">Cognome Proprietario</th>
	      
	      <th></th>
	    </tr>
	    
	  </thead>
	 
	  <tbody class="bordo1" id="ye">
	  
	  <tr>

	 
	      <th scope="row">
	      			<input type="radio" name="carta" required value="<%= bean.getNumeroCarta()%>"><br>  
	      		
	      	</th>
	      <td><%= bean.getNumeroCarta() %></td>
	      <td><%= bean.getDataScadenza() %></td>
	      <td><%= bean.getCvv() %></td>
	      <td><%= bean.getNomeProprietario() %></td>
	      <td><%= bean.getCognomeProprietario() %></td>
	     
<%
			}
%>	  
	    </tr>
	    
	  
	  </tbody>
	      	
	</table>
<%
			}
%>	  

<div class="bottoni">
<a href="aggiungiCarta.jsp" class="shiny-button2" id="dx">
 <strong>AGGIUNGI UNA CARTA DI CREDITO</strong>
  </a>

<%
	if((carte != null) && (carte.size() > 0)) {
%>
 
   <input id="sx" type="submit" value="PROCEDI CON L'ORDINE">
<%
	}
%>	  
</div>

 </form>

<%@ include file="footer.jsp" %>

</body>
</html>