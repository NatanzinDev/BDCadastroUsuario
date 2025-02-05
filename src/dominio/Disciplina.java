package dominio;

public class Disciplina {
	private int idDisciplina;
	private String nome;
	private int cargaHoraria;
	private Aluno aluno;
	
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public String getNomeDisciplina() {
		return nome;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nome = nomeDisciplina;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
