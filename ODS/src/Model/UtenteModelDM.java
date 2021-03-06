package Model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.DriverManagerConnectionPool;

public class UtenteModelDM implements UtenteModel<UtenteBean> {
	
	private static final String TABLE ="UTENTE";

	@Override
	public UtenteBean doRetrieveByKey(int idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		UtenteBean bean = null;
		
		String queryString ="Select * FROM " + TABLE + " WHERE id_utente = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, idUtente);
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
	public Collection<UtenteBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<UtenteBean> listaBean = new ArrayList<UtenteBean>();
		
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
	 * @param <em>utente</em> The user to be saved;
	 * Save the user into DB and set the attribute <em>idUtente</em> of <em>utente</em> to the id allocated in the DB.
	 */
	public void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;

		String insertString1=" INSERT INTO " + TABLE + " (nome, cognome, email, "
				+ "password, amministratore, data_nascita) VALUES(?, ?, ?, ?, ?, ?)";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString1);

			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassword());
			statement.setBoolean(5, utente.isAmministratore());
			statement.setDate(6, (Date) utente.getDataNascita());
			statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		String insertString2 = "SELECT last_insert_id() as last from " + TABLE + ";";
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString2);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			utente.setIdUtente(rs.getInt("last"));
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void doUpdate(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;

		String insertSQL = "UPDATE " + TABLE + " SET nome = ?, cognome = ?, email = ?,"
				+ " password = ?, amministratore = ?, data_nascita = ? WHERE id_utente = ?;";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			statement = connection.prepareStatement(insertSQL);
			
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassword());
			statement.setBoolean(5, utente.isAmministratore());
			statement.setDate(6, (Date) utente.getDataNascita());
			statement.setInt(7, utente.getIdUtente());
			statement.executeUpdate();

			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}


	@Override
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		int result = 0;
		
		String deleteString ="DELETE FROM " + TABLE + " WHERE id_utente = ?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteString);
			statement.setInt(1, id);
			
			result = statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return result != 0;
	}

	@Override
	public UtenteBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		UtenteBean bean = null;
		
		String queryString ="Select * FROM " + TABLE + " WHERE email = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setString(1, email);
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
	
	
	/**
	 * @return <em>UtenteBean</em> that represents the user which match with <em>email</em> and <em>password</em> if he's registered, null otherwise
	 */
	public UtenteBean validate(String email, String password) throws SQLException { //verifica se email e password coincidono per il login
		Connection connection = null;
		PreparedStatement statement=null;
		UtenteBean bean = null;

		String selectSQL = "SELECT * FROM " + TABLE + " WHERE email = ? and password = ?;";		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			statement.setString(1, email);
			statement.setString(2, password);
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
	
	
	private static UtenteBean getBean(ResultSet rs) throws SQLException{
		UtenteBean bean = new UtenteBean();
		
		bean.setIdUtente(rs.getInt("id_utente"));
		bean.setNome(rs.getString("nome"));
		bean.setCognome(rs.getString("cognome"));
		bean.setEmail(rs.getString("email"));
		bean.setPassword(rs.getString("password"));
		bean.setAmministratore(rs.getBoolean("amministratore"));
		bean.setDataNascita(rs.getDate("data_nascita"));
		
		return bean;
	}	
}