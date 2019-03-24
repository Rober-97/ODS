package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.DriverManagerConnectionPool;

public class ProdottoModelDM implements ProdottoModel<ProdottoBean>{

	private static final String TABLE_1 ="PRODOTTO";
	
	@Override
	public ProdottoBean doRetrieveByKey(int id_prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		ProdottoBean bean = null;
		
		String queryString ="Select * FROM " + TABLE_1 + " WHERE id_prodotto = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, id_prodotto);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				bean = getBean(result);
			}
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return bean;
	}

	@Override
	public Collection<ProdottoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();
		
		String queryString ="Select * FROM " + TABLE_1;
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				listaBean.add(getBean(result));
			}
			
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return listaBean;
	}

	/**
	 * @return Una lista di ProdottoBean che contiene tutti i prodotti contrassegnati come <em>inVendita</em>
	 */
	public Collection<ProdottoBean> doRetrieveAllInVendita() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();
		
		String queryString ="Select * FROM " + TABLE_1 + "WHERE in_vendita = true";
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				listaBean.add(getBean(result));
			}
			
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return listaBean;
	}

	/**
	 * @return Una lista di ProdottoBean che contiene tutti i prodotti con l'attributo <em>inVendita</em> a false
	 */
	public Collection<ProdottoBean> doRetrieveAllNotInVendita() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();
		
		String queryString ="Select * FROM " + TABLE_1 + "WHERE in_vendita = false";
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				listaBean.add(getBean(result));
			}
			
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return listaBean;
	}

	@Override
	/**
	 * salva il prodotto passato nel database e al termine setta l'attibuto id con quello generato dal db
	 */
	public void doSave(ProdottoBean prodotto) throws SQLException {	//fare inserimento per ogni taglia
		Connection connection = null;
		PreparedStatement statement = null;

		String insertString_1=" INSERT INTO " + TABLE_1 + " (codice_prodotto, descrizione, marca, modello, "
				+ "prezzo_compl, iva, in_vendita, categoria,foto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_1);

			statement.setString(1, prodotto.getCodiceProdotto());
			statement.setString(2, prodotto.getDescrizione());
			statement.setString(3, prodotto.getMarca());
			statement.setString(4, prodotto.getModello());
			statement.setFloat(5, 0);	//metto 0 perche il prezzo è aggiunto successivamente
			statement.setInt(6, 0);		//idem
			statement.setBoolean(7, false);	//contrassegno come non in vendita, lo diventerà una volta aggiunto il prezzo
			statement.setString(8, prodotto.getCategoria());
			statement.setString(9, prodotto.getFoto());
			statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		String insertString="SELECT last_insert_id() as last_id";
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString);
			ResultSet rs = statement.executeQuery();
			rs.next();
			prodotto.setIdProdotto(rs.getInt("last_id"));
			
			connection.commit();
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void doUpdate(ProdottoBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		String insertString_1=" UPDATE " + TABLE_1 + " SET codice_prodotto = ?, decrizione = ?, marca = ?, modello = ?, "
				+ "prezzo_compl = ?, iva = ?, in_vendita = ?, categoria = ?, foto = ? WHERE id_prodotto = ?;";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_1);

			statement.setString(1, prodotto.getCodiceProdotto());
			statement.setString(2, prodotto.getDescrizione());
			statement.setString(3, prodotto.getMarca());
			statement.setString(4, prodotto.getModello());
			statement.setFloat(5, prodotto.getPrezzoCompl());
			statement.setInt(6, prodotto.getIva());
			statement.setBoolean(7, prodotto.isInVendita());
			statement.setString(8, prodotto.getCategoria());
			statement.setString(9, prodotto.getFoto());
			statement.setInt(11, prodotto.getIdProdotto());
			statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	/**
	 * elimina il prodotto dal catalogo rendendolo non visibile al cliente, se esso è associato ad uno ordine viene comunque visualizzato
	 */
	public boolean doDelete(int id_prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		int result = 0;
		
		String deleteString ="UPDATE " + TABLE_1 + " SET prezzo_compl = 0, iva = 0, in_vendita = false WHERE id_prodotto = ?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteString);
			statement.setInt(1, id_prodotto);
			
			result = statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return result != 0;
	}

	/**
	 * @param categoria Stringa che identifica la categorie di apparteneza dei prodotti da ricercare
	 * @return Lista di ProdottoBean composta dai prodotti appartenenti alla categoria <em>categoria</em> che sono contrasseganti come <em>inVendita</em>
	 */
	public Collection<ProdottoBean> doRetrieveByCategory(String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();
		
		String queryString ="Select * FROM " + TABLE_1 + " WHERE categoria = ? and in_vendita = true";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setString(1, categoria);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				listaBean.add(getBean(result));
			}
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return listaBean;
	}
	
	private static ProdottoBean getBean(ResultSet rs) throws SQLException{
		ProdottoBean bean = new ProdottoBean();
		
		bean.setIdProdotto(rs.getInt("id_prodotto"));
		bean.setCodiceProdotto(rs.getString("codice_prodotto"));
		bean.setDescrizione(rs.getString("descrizione"));
		bean.setMarca(rs.getString("marca"));
		bean.setModello(rs.getString("modello"));
		bean.setPrezzoCompl(rs.getFloat("prezzo_compl"));
		bean.setIva(rs.getInt("iva"));
		bean.setInVendita(rs.getBoolean("in_vendita"));
		bean.setFoto(rs.getString("foto"));
		bean.setCategoria(rs.getString("categoria"));
		
		return bean;
	}
}