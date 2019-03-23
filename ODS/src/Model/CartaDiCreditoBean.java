package Model;
import java.sql.Date;

public class CartaDiCreditoBean {

	private String numeroCarta;
	private Date dataScadenza;
	private String cvv;
	private String nomeProprietario;
	private String cognomeProprietario;
	private int utente;

	
	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getCognomeProprietario() {
		return cognomeProprietario;
	}

	public void setCognomeProprietario(String cognomeProprietario) {
		this.cognomeProprietario = cognomeProprietario;
	}

	public int getUtente() {
		return utente;
	}

	public void setUtente(int utente) {
		this.utente = utente;
	}

	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		CartaDiCreditoBean other = (CartaDiCreditoBean) obj;
		if(this.numeroCarta != other.getNumeroCarta())
			return false;
		
		return true;
	}
}