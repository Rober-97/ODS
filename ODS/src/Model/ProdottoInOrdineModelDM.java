package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.DriverManagerConnectionPool;

public class ProdottoInOrdineModelDM implements ProdottoModel<ProdottoBean>{

	private static final String TABLE_1 ="PRODOTTO";
	private static final String TABLE_2 ="PRODOTTO_IN_ORDINE";

	@Override
	public ProdottoBean doRetrieveByKey(int id_prodotto_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		ProdottoBean bean = null;
		
		String queryString ="Select " + TABLE_1 + ".id_prodotto, codice_prodotto, descrizione, marca, modello, categoria, foto, " + TABLE_2 +
		".prezzo_compl, " + TABLE_2 + ".iva, taglia, quantita FROM " + TABLE_1 + "join" + TABLE_2 + " WHERE " + TABLE_1 + ".id_prodotto_ordine = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, id_prodotto_ordine);
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
		
		String queryString ="Select " + TABLE_1 + ".id_prodotto, codice_prodotto, descrizione, marca, modello, categoria, foto, " 
		+ TABLE_2 + ".prezzo, " + TABLE_2 + ".iva, taglia, quantita FROM " + TABLE_1 + "join" + TABLE_2;

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
	public void doSave(ProdottoBean prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		String insertString_2=" INSERT INTO " + TABLE_2 + "(id_prodotto, prezzo_compl, iva, taglia, quantita) VALUES(?, ?, ?, ?, ?)";
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_2);

			statement.setInt(1, ((ProdottoInOrdineBean)prodotto).getIdProdotto());
			statement.setFloat(2, ((ProdottoInOrdineBean)prodotto).getPrezzoCompl());
			statement.setFloat(3, ((ProdottoInOrdineBean)prodotto).getIva());
			statement.setString(4, ((ProdottoInOrdineBean)prodotto).getTaglia());
			statement.setInt(5, ((ProdottoInOrdineBean)prodotto).getQuantita());
			statement.executeUpdate();
			connection.commit();
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		String insertString_1="SELECT last_insert_id() as last_id from " + TABLE_2 + ";";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_1);
			ResultSet rs = statement.executeQuery();
			rs.next();
			((ProdottoInOrdineBean) prodotto).setIdProdottoOrdine(rs.getInt("last_id"));
			
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

		String insertString_2="UPDATE " + TABLE_2 + " SET prezzo_compl = ?, iva = ?, taglia = ?, quantita = ? WHERE id_prodotto_ordine = ?";
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_2);

			statement.setFloat(1, ((ProdottoInOrdineBean)prodotto).getPrezzoCompl());
			statement.setInt(2, ((ProdottoInOrdineBean)prodotto).getIva());
			statement.setString(3, ((ProdottoInOrdineBean)prodotto).getTaglia());
			statement.setInt(4, ((ProdottoInOrdineBean)prodotto).getQuantita());
			statement.setInt(6, ((ProdottoInOrdineBean)prodotto).getIdProdottoOrdine());
			statement.executeUpdate();
			
			connection.commit();			
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public boolean doDelete(int id_prodotto_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		int result = 0;
		
		String deleteString ="DELETE FROM " + TABLE_2 + " WHERE id_prodotto_ordine = ?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteString);
			statement.setInt(1, id_prodotto_ordine);
			
			result = statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return result != 0;
	}
	
	public Collection<ProdottoBean> doRetrieveByOrder(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<ProdottoBean> listaBean = new ArrayList<ProdottoBean>();

		String queryString ="Select " + TABLE_1 + ".id_prodotto, codice_prodotto, descrizione, marca, modello, "
			+ "categoria, foto, id_prodotto_ordine, " + TABLE_2 + ".prezzo_compl, " + TABLE_2 + ".iva, taglia, "
			+ "quantita FROM " + TABLE_1 + " join " + TABLE_2 + " join ORDINAZIONE WHERE ordine = ? and ordine = "
			+ "id_prodotto_ordine and " + TABLE_1 + ".id_prodotto = " + TABLE_2 + ".id_prodotto";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, id_ordine);
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
		ProdottoBean bean = new ProdottoInOrdineBean();
		
		bean.setIdProdotto(rs.getInt("PRODOTTO.id_prodotto"));
		bean.setCodiceProdotto(rs.getString("promozione"));
		bean.setDescrizione(rs.getString("descrizione"));
		bean.setMarca(rs.getString("marca"));
		bean.setModello(rs.getString("modello"));
		bean.setFoto(rs.getString("foto"));
		bean.setCategoria(rs.getString("categoria"));
		((ProdottoInOrdineBean)bean).setIdProdottoOrdine(rs.getInt("id_prodotto_ordine"));
		((ProdottoInOrdineBean)bean).setPrezzoCompl(rs.getFloat("PRODOTTO_IN_ORDINE.prezzo_compl"));
		((ProdottoInOrdineBean)bean).setIva(rs.getInt("PRODOTTO_IN_ORDINE.iva"));
		((ProdottoInOrdineBean)bean).setQuantita(rs.getInt("quantita"));
		((ProdottoInOrdineBean)bean).setTaglia(rs.getString("taglia"));
		
		return bean;
	}
}