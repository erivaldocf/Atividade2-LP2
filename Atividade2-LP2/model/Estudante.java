package model;

import java.io.Serializable;

public class Estudante implements Usuario, Serializable{
  String nome;
  String cpf;
  String matricula;
  String dataNascimento;
  String curso;

  public Estudante(String nome, String cpf, String matricula, String dataNascimento, String curso){
    this.nome = nome;
    this.cpf = cpf;
    this.matricula = matricula;
    this.dataNascimento = dataNascimento;
    this.curso = curso;
  }

  public void exibirDetalhes(){
    System.out.println("Nome: " + getNome());
    System.out.println("CPF: " + getCpf());
    System.out.println("Matricula: " + getMatricula());
    System.out.println("Data de Nascimento: " + getDataNascimento());
    System.out.println("Curso: " + getCurso());
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento){
    this.dataNascimento = dataNascimento;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

}