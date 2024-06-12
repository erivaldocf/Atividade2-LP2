package model;

import java.io.Serializable;

public class Professor implements Usuario, Serializable {

  String nome;
  String cpf;
  String matricula;
  String dataNascimento;
  String departamento;

  public Professor(String nome, String cpf, String matricula, String dataNascimento, String departamento){
    this.nome = nome;
    this.cpf = cpf;
    this.matricula = matricula;
    this.dataNascimento = dataNascimento;
    this.departamento = departamento;
  }

  public void exibirDetalhes(){
    System.out.println("Nome: " + getNome());
    System.out.println("CPF: " + getCpf());
    System.out.println("Matricula: " + getMatricula());
    System.out.println("Data de Nascimento: " + getDataNascimento());
    System.out.println("Departamento: " + getDepartamento());
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

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento){
    this.departamento = departamento;
  }
}