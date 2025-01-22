package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Usuario;

public class UsuarioDao {
	public Usuario encontrarUsuarioPorEmailESenha(String email, String senhaCriptografada) throws ClassNotFoundException, SQLException {
		
		
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "SELECT * FROM usuario u WHERE u.email LIKE ? AND U.senha = ? ";
		
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, email);
		comando.setString(2, senhaCriptografada);
		
		ResultSet resultado = comando.executeQuery();
		
		if(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setSenha(resultado.getString("senha"));
			
			return usuario;
		}
		
		comando.close();
		conexao.close();
		
		return null;
	}
}
