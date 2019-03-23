package Model;
import java.sql.Date;
public class OrdineBean {
	
	private int idOrdine;
	private Date data;
	private String cartaCredito;	//riferimento
	private int indirizzo;			//riferimento
	private int utente;			//riferimento
	private float totale;
	
	
	public int getIdOrdine() {
		return idOrdine;
	}
	
	public void setIdOrdine(int id_ordine) {
		this.idOrdine = id_ordine;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getCartaCredito() {
		return cartaCredito;
	}
	
	public void setCartaCredito(String cartaCredito) {
		this.cartaCredito = cartaCredito;
	}
	
	public int getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(int indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public int getUtente() {
		return utente;
	}
	
	public void setUtente(int utente) {
		this.utente = utente;
	}
		
	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}
	

	public boolean equals(Object otherObject){
		if(otherObject==null)
			return false;
		if(this.getClass()!=otherObject.getClass())
			return false;
		OrdineBean a=(OrdineBean) otherObject;
		if(this.idOrdine != a.getIdOrdine())
			return false;
		return true;
	}	
}