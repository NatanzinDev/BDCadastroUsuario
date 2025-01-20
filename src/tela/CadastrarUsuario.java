package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import banco.FabricaConexao;
import dominio.Usuario;
import util.CriptografiaUtils;

public class CadastrarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(117, 11, 204, 14);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(150, 81, 118, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(150, 241, 118, 20);
		contentPane.add(txtSenha);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(150, 155, 118, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(178, 56, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(178, 130, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(178, 216, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarUsuario();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btCadastrar.setBounds(150, 307, 118, 23);
		contentPane.add(btCadastrar);
		//fim da criação
	}

	protected void cadastrarUsuario() throws ClassNotFoundException, SQLException {
		String email = txtEmail.getText().toString();
		String nome = txtNome.getText().toString();
		String senha = new String(txtSenha.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);
		
		if (nome == null || nome.isEmpty()) {
			exibirMsgErro("Nome não pode ser vazio");
			return;
		}
		
		if (email == null || email.isEmpty()) {
			exibirMsgErro("Email não pode ser vazio");
			return;
		}
		if (senha == null || senha.isEmpty()) {
			exibirMsgErro("Senha não pode ser vazio");
			return;
		}
		
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senhaCriptografada);
		
		Connection conexao = FabricaConexao.criarConexao();
		String sql = "INSERT INTO usuario (nome,email,senha) VALUES (?,?,?)";
		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, u.getNome());
		comando.setString(2, u.getEmail());
		comando.setString(3, u.getSenha());
		comando.execute();
		
		comando.close();
		conexao.close();
		
		exibirMsgErro("Usuário - " + nome + "- Cadastrado com sucesso!");
		
		txtNome.setText("");
		txtSenha.setText("");
		txtEmail.setText("");
	}

	private void exibirMsgErro(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}
}
