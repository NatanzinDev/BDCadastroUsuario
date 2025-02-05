package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import banco.AlunoDao;
import banco.DisciplinaDao;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import dominio.Aluno;
import dominio.Disciplina;

public class CadastrarDisciplina extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCH;
	
	private SortedList<Aluno> alunosSugeridos = new SortedList<Aluno>(new BasicEventList<Aluno>());
	private JLabel lblAluno;
	private JComboBox cbAluno;

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
		
		lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(59, 166, 83, 14);
		contentPane.add(lblAluno);
		
		txtNome = new JTextField();
		txtNome.setBounds(104, 56, 184, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCH = new JTextField();
		txtCH.setBounds(104, 110, 184, 20);
		contentPane.add(txtCH);
		txtCH.setColumns(10);
		
		cbAluno = new JComboBox();
		cbAluno.setEditable(true);
		cbAluno.setBounds(104, 162, 184, 22);
		contentPane.add(cbAluno);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDisciplina();
			}
		});
		btnNewButton.setBounds(98, 215, 190, 23);
		contentPane.add(btnNewButton);
		
		AutoCompleteSupport.install(cbAluno, alunosSugeridos);
		
		cbAluno.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					buscarAluno();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		
		//fim do local de criação
	}
	
	protected void cadastrarDisciplina() {
		Disciplina d = new Disciplina();
		d.setNomeDisciplina(txtNome.getText());
		d.setCargaHoraria(Integer.parseInt(txtCH.getText()));
		
		Aluno a = (Aluno) cbAluno.getSelectedItem();
		
		try {
			DisciplinaDao dao = new DisciplinaDao();
			dao.CadastrarDisciplina(d);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no sistema");
			e.printStackTrace();
		}
		
	}

	public void buscarAluno() throws ClassNotFoundException {
		if(cbAluno.getEditor().getItem() == null && cbAluno.getEditor().getItem().toString().length() >= 3) {
			AlunoDao dao = new AlunoDao();
			List<Aluno> alunosEncontrados = new ArrayList<>();
			
			try {
				String nomeAluno = cbAluno.getEditor().getItem().toString();
				alunosEncontrados = dao.buscarAlunoPeloNome(nomeAluno);
				
				alunosSugeridos.clear();
				alunosSugeridos.addAll(alunosEncontrados);
			}catch(SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro no sistema");
			}
		}
	}
}
