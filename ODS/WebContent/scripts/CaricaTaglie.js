/**Al caricamento della scheda del prodotto nasconde le taglie e disabilita il tasto aggiungi al carrello,
 * successivamente viene fatta la chiamata ajax da un altro script
 */

$(document).ready(function(){// caricato il documento
	$('#XS').toggle();//nasconde i radio button delle taglie 
	$('#S').toggle();//nasconde i radio button delle taglie 
	$('#M').toggle();//nasconde i radio button delle taglie 
	$('#L').toggle();//nasconde i radio button delle taglie 
	$('#XL').toggle();//nasconde i radio button delle taglie 
	$('h5.sizes').hide();
	$('#aggiungi').prop("disabled", true);//disabilita il tasto aggiungi al carrello
	$("h5.sizes").show();
});


var idProdotto = $('#idProdotto').val();
jQuery.get('CercaTagliaControl', {"idProdotto" : idProdotto}, function(resp){
	$.each(resp, function(i, item){
		var id = '#' + item;
//		alert(id);
		$(id).toggle();
	})
});