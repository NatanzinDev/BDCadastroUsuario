package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.FabricaConexao;
import dominio.Aluno;
import java.awt.Color;

public class CadastrarAluno extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldMatricula;
	private JTextField textFieldCurso;
	private JTextField textFieldTelefone;
	private JList listarAlunos;
	private Aluno alunoEdicao;
	private JButton btnNewButtonCadastrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAluno frame = new CadastrarAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public CadastrarAluno() throws ClassNotFoundException, SQLException {
		setTitle("Cadastro de Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 203, 273);
		contentPane.add(panel);
		panel.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(49, 36, 135, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(49, 77, 135, 20);
		panel.add(textFieldMatricula);

		textFieldCurso = new JTextField();
		textFieldCurso.setColumns(10);
		textFieldCurso.setBounds(49, 119, 135, 20);
		panel.add(textFieldCurso);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(49, 164, 135, 20);
		panel.add(textFieldTelefone);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(49, 22, 58, 14);
		panel.add(lblNewLabel);

		JLabel lblMatrcula = new JLabel("Matrícula");
		lblMatrcula.setBounds(49, 64, 68, 14);
		panel.add(lblMatrcula);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(49, 104, 58, 14);
		panel.add(lblCurso);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(49, 151, 58, 14);
		panel.add(lblTelefone);

		btnNewButtonCadastrar = new JButton("Cadastrar");
		btnNewButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarAluno();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonCadastrar.setBounds(49, 195, 135, 23);
		panel.add(btnNewButtonCadastrar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Listagem de Alunos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(223, 11, 335, 273);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 315, 200);
		panel_1.add(scrollPane);

		listarAlunos = new JList();
		scrollPane.setViewportView(listarAlunos);

		JButton btnNewButtonExibir = new JButton("Exibir Dados");
		btnNewButtonExibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoSelecionado = (Aluno) listarAlunos.getSelectedValue();

				String msg = "Nome: " + alunoSelecionado.getNome() + "\nMatricula: " + alunoSelecionado.getMatricula()
						+ "\nCurso: " + alunoSelecionado.getCurso() + "\nTelefone: " + alunoSelecionado.getTelefone();

				ExibirMensagem(msg);

			}
		});
		btnNewButtonExibir.setBounds(10, 232, 106, 23);
		panel_1.add(btnNewButtonExibir);

		JButton btnEditarDados = new JButton("Editar Dados");
		btnEditarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarEdicaoAluno();
			}
		});
		btnEditarDados.setBounds(121, 232, 100, 23);
		panel_1.add(btnEditarDados);

		JButton btnNewButtonRemover = new JButton("Remover");
		btnNewButtonRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removerDados();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonRemover.setBounds(227, 232, 100, 23);
		panel_1.add(btnNewButtonRemover);

		atualizarListagemAlunos();
	}

	protected void removerDados() throws ClassNotFoundException, SQLException {
		
		if (listarAlunos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um aluno");
		}

		alunoEdicao = (Aluno) listarAlunos.getSelectedValue();
		
		Connection conexao = FabricaConexao.criarConexao();

		
		String sql = "DELETE FROM ALUNO WHERE ID_ALUNO = ?";

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setInt(1, alunoEdicao.getId());
		comando.executeUpdate();

		ExibirMensagem("Dados Removido");
		
		atualizarListagemAlunos();

		comando.close();
		conexao.close();
		
	}

	protected void iniciarEdicaoAluno() {
		if (listarAlunos.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione um aluno");
		}

		alunoEdicao = (Aluno) listarAlunos.getSelectedValue();
		textFieldNome.setText(alunoEdicao.getNome());
		textFieldMatricula.setText(alunoEdicao.getMatricula());
		textFieldCurso.setText(alunoEdicao.getCurso());
		textFieldTelefone.setText(alunoEdicao.getTelefone());

		btnNewButtonCadastrar.setText("Editar Dados");
		
	}

	protected void ExibirMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

	}

	private void atualizarListagemAlunos() throws ClassNotFoundException, SQLException {

		Connection conexao = FabricaConexao.criarConexao();

		String sql = "SELECT * FROM ALUNO";

		PreparedStatement comando = conexao.prepareStatement(sql);

		ResultSet resultado = comando.executeQuery();

		List<Aluno> alunosCadastrados = new ArrayList<Aluno>();

		while (resultado.next()) {
			Aluno a = new Aluno();

			a.setId(resultado.getInt("id_aluno"));
			a.setNome(resultado.getString("nome"));
			a.setMatricula(resultado.getString("matricula"));
			a.setCurso(resultado.getString("curso"));
			a.setTelefone(resultado.getString("telefone"));

			alunosCadastrados.add(a);
		}

		DefaultListModel<Aluno> modelo = new DefaultListModel<>();

		for (int i = 0; i < alunosCadastrados.size(); i++) {
			Aluno a = alunosCadastrados.get(i);
			modelo.addElement(a);
		}

		listarAlunos.setModel(modelo);

		comando.close();
		conexao.close();

	}

	protected void cadastrarAluno() throws ClassNotFoundException, SQLException {

		if (textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
			return;
		}

		if (textFieldMatricula.getText() == null || textFieldMatricula.getText().isEmpty()) {
			exibirMensagemErro("Matricula não pode ser vazio");
			return;
		}

		if (textFieldCurso.getText() == null || textFieldCurso.getText().isEmpty()) {
			exibirMensagemErro("Curso não pode ser vazio");
			return;
		}

		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().isEmpty()) {
			exibirMensagemErro("Telefone não pode ser vazio");
			return;
		}

		if (btnNewButtonCadastrar.getText().equals("Cadastrar")) {

			Connection conexao = FabricaConexao.criarConexao();

			String sql = "INSERT INTO ALUNO (nome, matricula, curso, telefone) VALUES (?,?,?,?)";

			Aluno a = new Aluno();

			a.setNome(textFieldNome.getText());
			a.setMatricula(textFieldMatricula.getText());
			a.setCurso(textFieldCurso.getText());
			a.setTelefone(textFieldTelefone.getText());

			PreparedStatement comando = conexao.prepareStatement(sql);

			comando.setString(1, a.getNome());
			comando.setString(2, a.getMatricula());
			comando.setString(3, a.getCurso());
			comando.setString(4, a.getTelefone());
			comando.execute();

			System.out.println("Fechando Conexão...");

			comando.close();
			conexao.close();

			JOptionPane.showMessageDialog(null, "Aluno foi cadastrado com sucesso", "Info",
					JOptionPane.INFORMATION_MESSAGE);

		} else if (btnNewButtonCadastrar.getText().equals("Editar Dados")) {

			Connection conexao = FabricaConexao.criarConexao();

			alunoEdicao.setNome(textFieldNome.getText());
			alunoEdicao.setMatricula(textFieldMatricula.getText());
			alunoEdicao.setCurso(textFieldCurso.getText());
			alunoEdicao.setTelefone(textFieldTelefone.getText());

			String sql = "UPDATE ALUNO SET NOME=?, MATRICULA=?, CURSO=?, TELEFONE=? WHERE ID_ALUNO=?";

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, alunoEdicao.getNome());
			comando.setString(2, alunoEdicao.getMatricula());
			comando.setString(3, alunoEdicao.getCurso());
			comando.setString(4, alunoEdicao.getTelefone());
			comando.setInt(5, alunoEdicao.getId());
			comando.executeUpdate();

			ExibirMensagem("Dados Alterado");

			comando.close();
			conexao.close();

			alunoEdicao = null;

		}

		atualizarListagemAlunos();
		
		textFieldNome.setText("");
		textFieldMatricula.setText("");
		textFieldCurso.setText("");
		textFieldTelefone.setText("");
		btnNewButtonCadastrar.setText("Cadastrar");

	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE);

	}
}
