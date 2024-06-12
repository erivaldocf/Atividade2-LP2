package model;

import java.io.Serializable;

public class Livro implements Serializable{

  String titulo;
  String autor;
  String assunto;
  String dataLancamento;
  int qtdEstoque;
  LivroStatus status;

  public Livro(String titulo, String autor, String assunto, String dataLancamento, int qtdEstoque){
    this.titulo = titulo;
    this.autor = autor;
    this.assunto = assunto;
    this.dataLancamento = dataLancamento;
    this.qtdEstoque = qtdEstoque;
  }

  public void checarDisponibilidade(){

    if (qtdEstoque == 0){
      this.status = LivroStatus.EMPRESTADO;
      System.out.println("Todos os exemplares emprestados!");
    } else if (qtdEstoque > 0){
      this.status = LivroStatus.DISPONIVEL;
    }
  }

  public LivroStatus getStatus(){
    return status;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getAssunto() {
    return assunto;
  }

  public void setAssunto(String assunto) {
    this.assunto = assunto;
  }

  public String getDataLancamento() {
    return dataLancamento;
  }

  public void setDataLancamento(String dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public int getQtdEstoque() {
    return qtdEstoque;
  }

  public void setQtdEstoque(int qtdEstoque){
    this.qtdEstoque = qtdEstoque;
  }

  public void exibirDetalhes() {
      System.out.println("Titulo: " + getTitulo());
      System.out.println("Autor: " + getAutor());
      System.out.println("Assunto: " + getAssunto());
      System.out.println("Ano de Lançamento: " + getDataLancamento());
      System.out.println("Qtd Estoque: " + getQtdEstoque());
  }
}