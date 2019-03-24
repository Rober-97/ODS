package Model;

public class ProdottoInCarrello extends ProdottoBean{

	private static final long serialVersionUID = 1L;
	
	private int quantita;
	private String taglia;
	
	public ProdottoInCarrello(ProdottoBean prod){
		this.setIdProdotto(prod.getIdProdotto());
		this.setCodiceProdotto(prod.getCodiceProdotto());
		this.setDescrizione(prod.getDescrizione());
		this.setMarca(prod.getMarca());
		this.setModello(prod.getModello());
		this.setFoto(prod.getFoto());
		this.setCategoria(prod.getCategoria());
		this.setPrezzoCompl(prod.getPrezzoCompl());
		this.setIva(prod.getIva());
		this.setInVendita(prod.isInVendita());
	}
	
	public ProdottoInCarrello(){

	}

	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ProdottoInCarrello other = (ProdottoInCarrello) obj;
		if(other.getIdProdotto() != this.getIdProdotto())
			return false;
		if(!other.getTaglia().equalsIgnoreCase(this.getTaglia()))
			return false;
		
		return true;
	}
}