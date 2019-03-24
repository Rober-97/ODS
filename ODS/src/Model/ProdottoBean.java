package Model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idProdotto;
	private String codiceProdotto;
	private String descrizione;
	private String marca;
	private String modello;
	private float prezzoCompl;
	private int iva;
	private boolean inVendita;
	private String categoria;
	private String foto; //url

	
	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public float getPrezzoCompl() {
		return prezzoCompl;
	}

	public void setPrezzoCompl(float prezzoCompl) {
		this.prezzoCompl = prezzoCompl;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public boolean isInVendita() {
		return inVendita;
	}

	public void setInVendita(boolean inVendita) {
		this.inVendita = inVendita;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	@Override
	public boolean equals (Object obj){
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		ProdottoBean other = (ProdottoBean) obj;
		if (idProdotto != other.getIdProdotto())
			return false;
		return true;
	}
}