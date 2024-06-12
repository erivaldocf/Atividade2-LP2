package model;

public interface Usuario{

  public void exibirDetalhes();

  String getNome();
  void setNome(String nome);

  String getCpf();
  void setCpf(String cpf);

  String getMatricula();
  void setMatricula(String matricula);

  String getDataNascimento();
  void setDataNascimento(String dataNascimento);
  
}