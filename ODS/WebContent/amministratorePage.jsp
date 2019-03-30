<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amministratore</title>
<link rel="stylesheet" href="css/css/bootstrap.min.css">
<link href="css/stile.css" rel="stylesheet" >
</head>
<body>

<%@ include file="header.jsp" %>
<%@ page import = "java.util.*, java.text.DecimalFormat" %>

<%	
UtenteModelDM model = new UtenteModelDM();
UtenteBean utente = model.doRetrieveByKey((int)session.getAttribute("id"));
%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="css/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	

    


<div class="row">

  <div class="col-sm-6">
    <div class="card">
      <h4 class="card-header bg-dark text-white"><%=utente.getNome() %>
      </h4>
      <div class="card-body">
          <div class="image float-left user-l">
            <img src="http://31.media.tumblr.com/tumblr_lw2lhqjrel1qfmi03o9_r1_500.gif" class="img-thumbnail" alt="avatar"/>
          </div>
        <h4 class="card-title">Magazziniere</h4>
          <p class="card-text"><br><%= utente.getCognome()%><br> <%=utente.getEmail()%><br> <%=utente.getDataNascita()%></p>
      </div>
    </div>
  </div>
            

</div>

<div class="btn-group-vertical pull-left">
  <button type="button" class="btn btn-secondary btn-lg" onclick="visualizza()" name="ins">Inserisci prodotto</button>
  <button type="button" class="btn btn-secondary btn-lg" onclick=location.href="prodottiSenzaPrezzo.jsp">Scegli il prezzo dei Prodotti</button>
</div>

 <form class="container col-sm-6"  action="ProductControl" name="insert" method="post">
  <input type="hidden" name="insert" value="insert">
  <div class="form-group" id='ins'>

  <label for="categoria">Categoria</label>
  <select name="categoria">
  <option value="giacca">Giacche</option>
  <option value="jeans">Jeans</option>
  <option value="camicia">Camicie</option>
  <option value="intimo">Intimo</option>
  <option value="shirt">Shirts</option>
  <option value="cappotto">Cappotti</option>
  <option value="pantalone">Pantalone</option>
</select>  
 <input type="text" class="form-control form-control-lg"  placeholder="Inserisci codice" name="codice" required oninput ="controlloCognome2()"> 
<input type="text" class="form-control form-control-lg"  placeholder="Inserisci marca" name="marca" required oninput ="controlloCognome2()"> 
<input type="text" class="form-control form-control-lg"  placeholder="Inserisci modello" name="modello" required oninput ="controlloCognome2()">
<input type="text" class="form-control form-control-lg"  placeholder="Inserisci descrizione" name="descrizione" required oninput ="controlloCognome2()">
<input type="file" accept="image/*" class="form-control form-control-lg"   name="foto" > 

<label for="taglia">Taglia</label>
<select name="taglia">
  <option value="xs">XS</option>
  <option value="s">S</option>
  <option value="m">M</option>
  <option value="l">L</option>
  <option value="xl">XL</option>
  <option value="xxl">XXL</option>
  
  </select>  
<input type="text" class="form-control form-control-lg"  placeholder="Inserisci quantità" name="quantita"> 
<label for="tipo">Uomo/Donna</label>
<select name="tipo">
  <option value="uomo">Uomo</option>
  <option value="donna">Donna</option>
 </select>  
 

  	<button type="submit" class="btn btn-secondary btn-lg" onclick="validazione2();" name="insProd" >Inserisci</button> 
  </div>
  
  </form>
<hr>

 <script > 

 document.getElementById("ins").style.display="none";
 function visualizza(){
    document.getElementById("ins").style.display="block";
 }
 </script>
 <br>
 <hr>
 <%@ include file="footer.jsp" %>
<script src="scripts/ValidazioneAddCarta.js"></script>
</body>
</html>