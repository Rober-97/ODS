<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/newStile.css">
<title>Registrazione Effetuata</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
%>	
	
<div class="benvenuto">
	<hr>
	<h2>
	Benvenuto <% String h = (String) request.getParameter("nome");%>
			<% out.println(h); %>
	</h2>
	<h4>Ti confermiamo che la registrazione è stata effettuata con successo,
	controlla la tua email per confermare il tuo nuovo account!
	<br>Torna alla <a href="index.jsp">home</a> per iniziare a comprare.
	</h4>
</div>	
 <%@ include file="footer.jsp" %>
</body>
</html>