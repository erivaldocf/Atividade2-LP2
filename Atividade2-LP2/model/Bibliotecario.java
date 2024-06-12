package model;

import java.io.Serializable;

public class Bibliotecario implements Usuario, Serializable{

  String nome;
  String cpf;
  String matricula;
  String dataNascimento;
  String login;
  String senha;

  public Bibliotecario(String nome, String cpf, String matricula, String dataNascimento,String login, String senha){
    this.nome = nome;
    this.cpf = cpf;
    this.matricula = matricula;
    this.dataNascimento = dataNascimento;
    this.login = login;
    this.senha = senha;
  }

  public void exibirDetalhes(){
    System.out.println("Nome: " + getNome());
    System.out.println("CPF: " + getCpf());
    System.out.println("Matricula: " + getMatricula());
    System.out.println("Data de Nascimento: " + getDataNascimento());
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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login){
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha){
    this.senha = senha;
  }
}