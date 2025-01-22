package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import banco.UsuarioDao;
import dominio.Usuario;
import util.CriptografiaUtils;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(138, 68, 202, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(138, 172, 202, 19);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(201, 10, 112, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(69, 74, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(69, 175, 45, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btEntrar = new JButton("Entrar");
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fazerLogin();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btEntrar.setBounds(138, 247, 202, 21);
		contentPane.add(btEntrar);
	}

	protected void fazerLogin() throws ClassNotFoundException, SQLException {
		String email = txtEmail.getText();
		String senha = new String(txtSenha.getPassword());
		String senhaCriptografado = CriptografiaUtils.criptografarMD5(senha);
		
		UsuarioDao dao = new UsuarioDao();
		
		Usuario u = dao.encontrarUsuarioPorEmailESenha(email, senhaCriptografado);
		
		if(u == null) {
			JOptionPane.showMessageDialog(null, "Não foi encontrado usuários.");
		}else {
			Principal b = new Principal();
			b.setLocationRelativeTo(null);
			b.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			b.setVisible(true);
		}
		
		
		
	}
}
