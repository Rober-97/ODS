package Model;
import java.util.Date;

public class UtenteBean {

	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean amministratore;
	private Date dataNascita;
	
	public UtenteBean() {
		
	}
	
	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
	}

	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	@Override
	/**
	 * @return true if the attribute idUtente is equals
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteBean other = (UtenteBean) obj;
		if (other.idUtente != idUtente)
				return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "ID: " + idUtente + ", Nome: " + nome + ", Cognome: " + cognome + ", Email: " + email + ", Password: "
				+ password + ", IsAmmministartore: " + amministratore + ", Data di nascita: " + dataNascita;
	}
}