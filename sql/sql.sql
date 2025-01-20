create database faculdade;
create table aluno(
	id_aluno int not null auto_increment primary key,
	nome text,
	matricula text,
	curso text,
	telefone text
    );
    
    drop table aluno;
create table usuario(
	id int not null auto_increment primary key,
	email text,
	senha text,
	nome text
);

select * from aluno;
select * from usuario;
