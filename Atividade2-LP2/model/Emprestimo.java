package model;

import java.time.LocalDate;

import java.io.Serializable;

public class Emprestimo implements Serializable {

  Usuario usuario;
  Livro livro;
  LocalDate dataEmprestimo;
  LocalDate dataDev;

  public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDev){
    this.usuario = usuario;
    this.livro = livro;
    this.dataEmprestimo = dataEmprestimo;
    this.dataDev = dataDev;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro){
    this.livro = livro;
  }

  public LocalDate getDataEmprestimo() {
    return dataEmprestimo;
  }

  public void setDataEmprestimo(LocalDate dataEmprestimo){
    this.dataEmprestimo = dataEmprestimo;
  }

  public LocalDate getDataDev() {
    return dataDev;
  }

  public void setDataDev(LocalDate dataDev){
    this.dataDev = dataDev;
  }
  
  public void exibirDetalhes() {
      System.out.println("Usuario: " + usuario.getNome());
      System.out.println("Livro: " + livro.getTitulo());
      System.out.println("Data de Emprestimo: " + getDataEmprestimo());
      System.out.println("Data de devolução: " + getDataDev());
  }
}