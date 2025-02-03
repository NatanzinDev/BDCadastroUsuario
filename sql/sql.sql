create database faculdade;
create table aluno(
	id_aluno int not null auto_increment primary key,
	nome text,
	matricula text,
	curso text,
	telefone text
    );
    
    
create table usuario(
	id int not null auto_increment primary key,
	email text not null,
	senha text not null,
	nome text not null
);

create table disciplina (
    id_disciplina integer not null auto_increment,
    nome varchar(255) not null,
    ch int not null,
    id_aluno int not null,
    primary key (id_disciplina),
    foreign key (id_aluno) references aluno (id_aluno)
);

select * from aluno;
select * from usuario;
