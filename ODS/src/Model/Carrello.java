package Model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.OrdinazioneBean;
import Model.OrdinazioneModel;
import Model.OrdinazioneModelDM;
import Model.OrdineBean;
import Model.OrdineModel;
import Model.OrdineModelDM;
import Model.ProdottoBean;
import Model.ProdottoInOrdineBean;
import Model.ProdottoInOrdineModelDM;
import Model.ProdottoModel;

@SuppressWarnings("hiding")
public class Carrello<ProdottoInCarrello> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	List<ProdottoInCarrello> list;
	
	public Carrello() {
		list = new ArrayList<ProdottoInCarrello>(); //costruttore
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void addProd(ProdottoInCarrello prodotto) {
		//controllo se esiste ed eventualmente incremento quantita
		boolean incrementato = false;
		for(ProdottoInCarrello p : list){
			if(p.equals(prodotto)){
				((Model.ProdottoInCarrello)p).setQuantita(((Model.ProdottoInCarrello)p).getQuantita() + 1);
				incrementato = true;
				break;
			}
		}
		//in caso contrario aggiungo
		if(!incrementato)
			list.add(prodotto);
	}
	
	public void rimElemento(int id_prodotto) {
		for(ProdottoInCarrello p : list) {
			if(((Model.ProdottoInCarrello)p).getIdProdotto() == id_prodotto) {
				((Model.ProdottoInCarrello)p).setQuantita(((Model.ProdottoInCarrello)p).getQuantita() - 1);
				if(((Model.ProdottoInCarrello)p).getQuantita() <= 0)
					list.remove(p);
				break;
			}	
		}
	}
	
	public List<ProdottoInCarrello> ottieniElem() {
		return list;
	}
	
	public float getTotale(){
		float totale = 0;
		for(ProdottoInCarrello p : list){
			totale += ((Model.ProdottoInCarrello)p).getPrezzoCompl();
		}
		return totale;
	}
	
	public void acquista(String carta_credito, int utente, int indirizzo) throws SQLException {
		ProdottoModel<ProdottoBean> prodottoModel = new ProdottoInOrdineModelDM();
		OrdineModel ordineModel = new OrdineModelDM();
		OrdinazioneModel ordinazioneModel = new OrdinazioneModelDM();
		OrdineBean ordBean = new OrdineBean();
		
		ordBean.setCartaCredito(carta_credito);
		ordBean.setUtente(utente);
		ordBean.setIndirizzo(indirizzo);
		ordBean.setTotale(this.getTotale());
		ordBean.setData(new java.sql.Date(new java.util.Date().getTime()));	//getTime di java.util.Date restituisce long al costruttore di java.sql.Date
		
		ordineModel.doSave(ordBean);	//salvo nel db
		int idOrdine = ordBean.getIdOrdine();	//prelevo l'id assegnato dal db e settato dal metodo doSave
		
		for(ProdottoInCarrello prod : list){
			ProdottoInOrdineBean prodBean = new ProdottoInOrdineBean();
			
			prodBean.setIdProdotto(((Model.ProdottoInCarrello)prod).getIdProdotto());
			prodBean.setPrezzoCompl(((Model.ProdottoInCarrello)prod).getPrezzoCompl());
			prodBean.setIva(((Model.ProdottoInCarrello)prod).getIva());
			prodBean.setQuantita(((Model.ProdottoInCarrello)prod).getQuantita());
			prodBean.setTaglia(((Model.ProdottoInCarrello)prod).getTaglia().toUpperCase());
			
			prodottoModel.doSave(prodBean);	//salvo nel db
			int id = prodBean.getIdProdottoOrdine();	//prelevo lid generato dal db e settato dal metodo doSave
			
			OrdinazioneBean ordinazioneBean = new OrdinazioneBean(idOrdine, id);
			ordinazioneModel.doSave(ordinazioneBean);
		}

		list = new ArrayList<ProdottoInCarrello>();
	}	
}