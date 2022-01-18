package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Contato;

public class ContatoDAO {
	public static void create(Contato contato) {
		String sql = "INSERT INTO Contato(nome, idade, email, ddd, telefone) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getEmail());
			pstm.setInt(4, contato.getDdd());
			pstm.setInt(5, contato.getTelefone());
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update(Contato contato) {
		String sql = "UPDATE Contato SET nome = ?, idade = ?, email = ?, ddd = ?, telefone = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setString(3, contato.getEmail());
			pstm.setInt(4, contato.getDdd());
			pstm.setInt(5, contato.getTelefone());
			pstm.setInt(6, contato.getId());
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(int id) {
		String sql = "DELETE FROM Contato WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Contato read(int id) {
		String sql = "SELECT * FROM Contato WHERE id = ?";
		Contato contato = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				contato = new Contato();
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setEmail(rset.getString("email"));
				contato.setDdd(rset.getInt("ddd"));
				contato.setTelefone(rset.getInt("telefone"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return contato;
	}
	
	public static List<Contato> readAll() {
		String sql = "SELECT * FROM Contato";
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Contato contato = new Contato();
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setEmail(rset.getString("email"));
				contato.setDdd(rset.getInt("ddd"));
				contato.setTelefone(rset.getInt("telefone"));
				contatos.add(contato);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
}
