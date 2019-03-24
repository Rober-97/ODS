package Model;

public class ProdottoInOrdineBean extends ProdottoBean{

	private static final long serialVersionUID = 1L;

	private int idProdottoOrdine;
	private float prezzoCompl;
	private int iva;
	private String taglia;
	private int quantita;
	

	public int getIdProdottoOrdine() {
		return idProdottoOrdine;
	}

	public void setIdProdottoOrdine(int idProdottoOrdine) {
		this.idProdottoOrdine = idProdottoOrdine;
	}

	@Override
	public float getPrezzoCompl() {
		return prezzoCompl;
	}

	@Override
	public void setPrezzoCompl(float prezzoCompl) {
		this.prezzoCompl = prezzoCompl;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}