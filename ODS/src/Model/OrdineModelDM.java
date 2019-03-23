package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.DriverManagerConnectionPool;

public class OrdineModelDM implements OrdineModel{

	private static final String TABLE ="ORDINE";

	@Override
	public OrdineBean doRetrieveByKey(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		OrdineBean bean = null;
		
		String queryString ="Select * FROM " + TABLE + " WHERE id_ordine = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, id_ordine);
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
	public Collection<OrdineBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<OrdineBean> listaBean = new ArrayList<OrdineBean>();
		
		String queryString ="Select * FROM " + TABLE ;
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
	 * Salva l'ordine ordine nel db e set l'attributo id dell'oggetto ordine con il valore generato dal db
	 */
	public void doSave(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		
		String insertString=" INSERT INTO " + TABLE + " (data, carta_credito, "
				+ "indirizzo, utente, totale) VALUES(?, ?, ?, ?, ?)";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString);

			statement.setDate(1, ordine.getData());
			statement.setString(2, ordine.getCartaCredito());
			statement.setInt(3, ordine.getIndirizzo());
			statement.setInt(4, ordine.getUtente());
			statement.setFloat(5, ordine.getTotale());
			statement.executeUpdate();
			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		String insertString_1="SELECT last_insert_id() as last_id from " + TABLE + ";";
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString_1);
			ResultSet rs = statement.executeQuery();
			rs.next();
			ordine.setIdOrdine(rs.getInt("last_id"));
			
			connection.commit();
		} finally{
			if(statement != null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void doUpdate(OrdineBean ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;

		String insertString="UPDATE" + TABLE + " SET data = ?, carta_credito = ?, "
				+ "indirizzo = ?, utente = ?, totale = ? WHERE id_ordine = ?;";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString);

			statement.setDate(1, ordine.getData());
			statement.setString(2, ordine.getCartaCredito());
			statement.setInt(3, ordine.getIndirizzo());
			statement.setInt(4, ordine.getUtente());
			statement.setFloat(5, ordine.getTotale());
			statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	@Override
	public boolean doDelete(int id_ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		int result = 0;
		
		String deleteString ="DELETE FROM " + TABLE + " WHERE id_ordine = ?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteString);
			statement.setInt(1, id_ordine);
			
			result = statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return result != 0;
	}

	@Override
	public Collection<OrdineBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<OrdineBean> listaBean = new ArrayList<OrdineBean>();
		
		String queryString ="Select * FROM " + TABLE + " WHERE utente = ? order by data desc";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, utente);
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

	private static OrdineBean getBean(ResultSet rs) throws SQLException{
		OrdineBean bean = new OrdineBean();
		
		bean.setIdOrdine(rs.getInt("id_ordine"));
		bean.setData(rs.getDate("data"));
		bean.setCartaCredito(rs.getString("carta_credito"));
		bean.setIndirizzo(rs.getInt("indirizzo"));
		bean.setUtente(rs.getInt("utente"));
		bean.setTotale(rs.getFloat("totale"));
		
		return bean;
	}
}