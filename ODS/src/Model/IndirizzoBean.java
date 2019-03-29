package Model;

import java.io.Serializable;

public class IndirizzoBean implements Serializable {
	
	private int idIndirizzo;
	private String nome;
	private String cognome;
	private String cap;
	private String provincia;
	private String citta;
	private String via;
	private String cellulare;
	private int utente;

		
	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public int getUtente() {
		return utente;
	}

	public void setUtente(int utente) {
		this.utente = utente;
	}

	public boolean equals(Object otherObject){
		if(otherObject == null)
			return false;
		if(this.getClass() != otherObject.getClass())
			return false;
		IndirizzoBean a = (IndirizzoBean) otherObject;
		if(this.idIndirizzo != a.getIdIndirizzo())
			return false;
		if(this.provincia != a.getProvincia())
			return false;
		if(this.cap != a.getCap())
			return false;
		if(this.citta != a.getCitta())
			return false;
		if(this.via != a.getVia())
			return false;
		if(this.cellulare != a.getCellulare())
			return false;
		
		return true;
	}
}
