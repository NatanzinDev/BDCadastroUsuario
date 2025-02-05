package dominio;

public class Aluno implements Comparable<Aluno> {

	@Override
	public String toString() {
		return "Nome: " + nome + " - " + " Matricula: " + matricula;
	}

	private int id;
	private String nome;
	private String matricula;
	private String curso;
	private String telefone;
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int compareTo(Aluno o) {
		return nome.compareTo(o.getNome());
	}

}
