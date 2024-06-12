package DAO;
import java.util.ArrayList;
import model.*;

public class BibliotecaDAO {

  public ArrayList<Livro> livros;
  public ArrayList<Usuario> usuarios;
  public ArrayList<Emprestimo> emprestimos;

  private static BibliotecaDAO banco;

  private BibliotecaDAO() {
    livros = new ArrayList<Livro>();
    usuarios = new ArrayList<Usuario>();
    emprestimos = new ArrayList<Emprestimo>();
  }

  public static BibliotecaDAO getInstance(){
    if (banco == null){
      banco = new BibliotecaDAO();
    }
    return banco;
  }

  public ArrayList<Livro> getLivros() {
    return livros;
  }

  public void setLivros(ArrayList<Livro> livros){
    this.livros = livros;
  }

  public ArrayList<Usuario> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(ArrayList<Usuario> usuarios){
    this.usuarios = usuarios;
  }

  public ArrayList<Emprestimo> getEmprestimos() {
    return emprestimos;
  }

  public void setEmprestimos(ArrayList<Emprestimo> emprestimos){
    this.emprestimos = emprestimos;
  }
}