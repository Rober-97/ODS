<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>HEADER-ODS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <link rel="stylesheet" href="css/stile.css">
   <link rel="stylesheet" href="css/newStile.css">
   <script src="script/jquery.js" ></script>
</head>
<body>
 
    <%! String autButton = "Login";
    	String autCtrl = "login.jsp";
    	String areaUtenteCtrl = "login.jsp";
    	boolean amministratore = false;
    %>
    <%	if(session.getAttribute("amministratore") != null){
    		amministratore = (boolean) session.getAttribute("amministratore");
     		if(amministratore){
     			autButton = "Logout";
     			autCtrl = "LogoutControl";
     			areaUtenteCtrl = "amministratore_page.jsp";
     		} else {
     			autButton = "Logout";
     			autCtrl = "LogoutControl";
     			areaUtenteCtrl = "area_utente.jsp";
     		}
    	} else {
    		autButton = "Login";
        	autCtrl = "login.jsp";
        	areaUtenteCtrl = "login.jsp";
    	}
    %>
    

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
	<%
	if(!amministratore){
	%>
    <ul class="nav navbar-nav sinistra">
     
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><font color= midnightblue>UOMO</font> <span class="caret"></span></a>
        <ul class="dropdown-menu"> 
          <li><a href="CategoriaControl?categoria=giacca.uomo">Giacche</a></li>
          <li><a href="CategoriaControl?categoria=jeans.uomo">Jeans</a></li>
          <li><a href="CategoriaControl?categoria=camicie.uomo">Camicie</a></li>
          <li><a href="CategoriaControl?categoria=intimo.uomo">Intimo</a></li>
          <li><a href="CategoriaControl?categoria=shirt.uomo">Shirt e felpe</a></li>
          <li><a href="CategoriaControl?categoria=cappotti.uomo">Cappotti</a></li>
          <li><a href="CategoriaControl?categoria=pantaloni.uomo">Pantaloni</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><font color= #f2baa1>DONNA</font><span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="CategoriaControl?categoria=giacca.donna">Giacche</a></li>
          <li><a href="CategoriaControl?categoria=jeans.donna">Jeans</a></li>
          <li><a href="CategoriaControl?categoria=camicie.donna">Camicie</a></li>
          <li><a href="CategoriaControl?categoria=intimo.donna">Intimo</a></li>
          <li><a href="CategoriaControl?categoria=shirt.donna">Shirt e felpe</a></li>
          <li><a href="CategoriaControl?categoria=cappotti.donna">Cappotti</a></li>
          <li><a href="CategoriaControl?categoria=pantaloni.donna">Pantaloni</a></li>
        </ul>
      </li>
            
    </ul>
    <%
	}
    %>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    	<span id="logoHeader">
    	<a href= "index.jsp">
    	<img src="img/LOGO1.jpg" alt="Logo" height="94px" width="250px"> 
    	</a>
    	</span>

    <ul class="nav navbar-nav navbar-right">
    <%
	if(!amministratore){
	%>
      <li><a href=carrello.jsp><font color= GRAY><span class="glyphicon glyphicon-shopping-cart"></span> Carrello</font></a></li>
    <%
	}
    %>
      <li><a href=<%= areaUtenteCtrl %>><font color= GRAY><span class="glyphicon glyphicon-user" name="areaUtente"></span> Area Utente</font></a></li>
      <li><a href= <%= autCtrl %> ><font color= GRAY><span class="glyphicon glyphicon-log-in"></span> <%= autButton %></font></a></li>
      
    </ul>
  </div>
  <script src="script/jquery.js" ></script>
</nav>
   <script src="scripts/animation.js"></script> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>