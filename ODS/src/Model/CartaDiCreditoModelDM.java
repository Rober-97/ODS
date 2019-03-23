package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.DriverManagerConnectionPool;

public class CartaDiCreditoModelDM implements CartaDiCreditoModel<CartaDiCreditoBean>{

	private static final String TABLE ="CARTA_CREDITO";

	@Override
	public CartaDiCreditoBean doRetrieveByKey(String numeroCarta) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		CartaDiCreditoBean bean = null;
		
		String queryString ="Select * FROM " + TABLE + " WHERE numero_carta = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setString(1, numeroCarta);
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
	public Collection<CartaDiCreditoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		Collection<CartaDiCreditoBean> listaBean = new ArrayList<CartaDiCreditoBean>();
		
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
	public void doSave(CartaDiCreditoBean carta) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;

		String insertString=" INSERT INTO " + TABLE + " (numero_carta, data_scadenza, cvv,"
				+ " nome_proprietario, cognome_proprietario, utente) VALUES(?, ?, ?, ?, ?, ?)";
		
		try{ 
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(insertString);

			statement.setString(1, carta.getNumeroCarta());
			statement.setDate(2, carta.getDataScadenza());
			statement.setString(3, carta.getCvv());
			statement.setString(4, carta.getNomeProprietario());
			statement.setString(5, carta.getCognomeProprietario());
			statement.setInt(6, carta.getUtente());
			statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void doUpdate(CartaDiCreditoBean carta) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;

		String insertSQL = "UPDATE " + TABLE + " SET data_scadenza = ?, cvv = ?, "
				+ "nome_proprietario = ?, cognome_proprietario = ? WHERE numero_carta = ?;";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			statement = connection.prepareStatement(insertSQL);
			statement.setDate(1, carta.getDataScadenza());
			statement.setString(2, carta.getCvv());
			statement.setString(3, carta.getNomeProprietario());
			statement.setString(4, carta.getCognomeProprietario());
			statement.setString(5, carta.getNumeroCarta());
			statement.executeUpdate();

			connection.commit();
		} finally{
			if(statement!= null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}

	@Override
	public boolean doDelete(String numeroCarta) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		int result = 0;
		
		String deleteString ="DELETE FROM " + TABLE + " WHERE numero_carta = ?";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteString);
			statement.setString(1, numeroCarta);
			result = statement.executeUpdate();
			
			connection.commit();
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		
		return result != 0;
	}

	@Override
	public ArrayList<CartaDiCreditoBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement statement=null;
		ArrayList<CartaDiCreditoBean> bean = new ArrayList<CartaDiCreditoBean>();
		
		String queryString ="Select * FROM " + TABLE + " WHERE utente = ?";
		
		try{
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(queryString);
			statement.setInt(1, utente);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				bean.add( getBean(result));
			}
		} finally{
			if(statement!=null) statement.close();
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return bean;
	}

	public static boolean checkCarta(String numeroCarta) throws SQLException { // verifica se carta esiste gia 
		boolean flag =false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String checkSQL="Select numero_carta  from "+TABLE+" where numero_carta= ?;" ;
		
		try {
			try {
				connection = DriverManagerConnectionPool.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // crea la connessione se non esiste
			preparedStatement = connection.prepareStatement(checkSQL);

			preparedStatement.setString(1,numeroCarta);
		

			System.out.println("validate..." + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery(); // la query viene eseguita
			
			flag=rs.next();
			
		} finally {

			try {
				if(preparedStatement != null ) {
					preparedStatement.close(); // rilascio risorse
				}
			}
			finally {
				DriverManagerConnectionPool.releaseConnection(connection); // evita di far reinstanziare ogni volta una connection
				// la connection viene "conservata" nella collection Pool
			}
		}

		return flag;
	}

	private static CartaDiCreditoBean getBean(ResultSet rs) throws SQLException{
		CartaDiCreditoBean bean = new CartaDiCreditoBean();
		
		bean.setNumeroCarta(rs.getString("numero_carta"));
		bean.setDataScadenza(rs.getDate("data_scadenza"));
		bean.setCvv(rs.getString("cvv"));
		bean.setNomeProprietario(rs.getString("nome_proprietario"));
		bean.setCognomeProprietario(rs.getString("cognome_proprietario"));
		bean.setUtente(rs.getInt("utente"));
		
		return bean;
	}	
}