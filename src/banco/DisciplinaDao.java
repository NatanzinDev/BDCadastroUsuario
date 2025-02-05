package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dominio.Disciplina;

public class DisciplinaDao {
	public void CadastrarDisciplina(Disciplina d) throws ClassNotFoundException, SQLException {
		Connection c  = FabricaConexao.criarConexao();
		
		String sql = "INSERT INTO disciplina (nome,ch,id_aluno) VALUES (?,?,?)";
		
		PreparedStatement comando = c.prepareStatement(sql);
		comando.setString(1, d.getNomeDisciplina());
		comando.setInt(2, d.getCargaHoraria());
		comando.setInt(3, d.getAluno().getId());
		comando.execute();
		
		comando.close();
		c.close();
		
		JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso.");
	}
}
