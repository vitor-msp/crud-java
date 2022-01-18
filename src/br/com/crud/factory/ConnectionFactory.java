package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/CRUD";
	
	public static Connection createConnectionToMySQL() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
		return connection;
	}

	public static void main(String[] args) throws Exception{
		try {			
			Connection con = createConnectionToMySQL();
			if(con != null) {
				System.out.println("Conexão obtida com sucesso! - " + con);
				con.close();
			}
		}catch(Exception erro) {			
			System.out.println("Erro na conexão! - " + erro);
		}
	}

}
