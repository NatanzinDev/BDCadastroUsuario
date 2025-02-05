package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Aluno;

public class AlunoDao {
	
	public List<Aluno> buscarAlunoPeloNome(String nome) throws ClassNotFoundException, SQLException{
		 
		Connection c = FabricaConexao.criarConexao();
		String  sql = "SELECT * FROM aluno WHERE 1 = 1";
		
		if(nome != null && !nome.isEmpty()) {
			sql += "AND upper(nome) LIKE ?";
		}
		
		PreparedStatement comando = c.prepareStatement(sql.toString());
		int i = 1;
		
		if(nome != null && !nome.isEmpty()) {
			comando.setString(i++, "%"+ nome.toUpperCase() + "%");
		}
		
		ResultSet resultado =  comando.executeQuery();
		List<Aluno> alunosCadastrados = new ArrayList<>();
		
		while(resultado.next()) {
			Aluno a = new Aluno();
			
			a.setId(resultado.getInt("id_aluno"));
			a.setNome(resultado.getString("nome"));
			a.setCurso(resultado.getString("curso"));
			a.setMatricula(resultado.getString("matricula"));
			
			
			alunosCadastrados.add(a);
		}
		
		return alunosCadastrados;
	}
}
