package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CadastrarDisciplina extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarDisciplina frame = new CadastrarDisciplina();
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
	public CadastrarDisciplina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(59, 59, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCargaHorria = new JLabel("Carga Horária:");
		lblCargaHorria.setBounds(25, 113, 83, 14);
		contentPane.add(lblCargaHorria);
		
		JLabel lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(59, 166, 83, 14);
		contentPane.add(lblAluno);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(104, 56, 184, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCH = new JTextField();
		textFieldCH.setBounds(104, 110, 184, 20);
		contentPane.add(textFieldCH);
		textFieldCH.setColumns(10);
		
		JComboBox comboBoxAluno = new JComboBox();
		comboBoxAluno.setEditable(true);
		comboBoxAluno.setBounds(104, 162, 184, 22);
		contentPane.add(comboBoxAluno);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(98, 215, 190, 23);
		contentPane.add(btnNewButton);
	}
}
