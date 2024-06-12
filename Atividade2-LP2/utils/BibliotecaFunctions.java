package utils;

import DAO.BibliotecaDAO;
import model.*;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.*;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class BibliotecaFunctions {

  static Scanner input = new Scanner(System.in);
  static int opc;
  
  static String nome, cpf, dataNascimento, matricula, curso,departamento, titulo,autor,assunto,dataLancamento;
  static int qtdEstoque, dia, mes, ano;
  static LocalDate dataEmprestimo, dataDev;
  static Usuario usuario;
  static Livro livro;

  private static BibliotecaDAO banco = BibliotecaDAO.getInstance();

  public static void adicionarLivro(String titulo, String autor, String assunto, String dataLancamento, int qtdEstoque){

    Livro rt = new Livro(titulo, autor, assunto, dataLancamento, qtdEstoque);
    banco.getLivros().add(rt);
  }

  public static void adicionarEstudante(String nome, String cpf, String matricula, String dataNascimento,String curso){

    Estudante est = new Estudante(nome, cpf, matricula, dataNascimento, curso);
    banco.getUsuarios().add(est);
  }

  public static void adicionarProfessor(String nome, String cpf, String matricula, String dataNascimento, String departamento){
    
    Professor prof = new Professor(nome, cpf, matricula, dataNascimento, departamento);
    banco.getUsuarios().add(prof);
  }

  public static void adicionarBibliotecario(String nome, String cpf, String matricula, String dataNascimento,String login, String senha){
    Bibliotecario bib = new Bibliotecario(nome, cpf, matricula, dataNascimento, login, senha);
    banco.getUsuarios().add(bib);
  }

  public static void adicionarEmprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDev){
    Emprestimo emp = new Emprestimo(usuario, livro, dataEmprestimo, dataDev);
    banco.getEmprestimos().add(emp);
    
  }

  
  public static void removerLivro(String titulo){

    Livro li = buscarPorTitulo(titulo);

    if (li == null) {
      System.out.println("Livro não localizado!");
      return;
    } else {
      banco.getLivros().remove(li);
    }
  }

  public static void removerEstudante(String cpf){

    Usuario u = buscarPorCpf(cpf);

    if (u == null){
      System.out.println("Usuario não localizado!");
      return;
    } else {
      banco.getUsuarios().remove(u);
    }
  }

  private static Livro buscarPorTitulo(String titulo) {

      for (Livro li : banco.getLivros()) {
          if (li instanceof Livro) {
              if (((Livro) li).getTitulo().equals(titulo)) {
                  return li;
              }
          }
      }
      return null;
  }

  private static Usuario buscarPorCpf(String cpf){

    for (Usuario u : banco.getUsuarios()) {
      if (u instanceof Estudante) {
        if (((Estudante) u).getCpf().equals(cpf)) {
          return u;
        }
      }

      if (u instanceof Professor) {
        if (((Professor) u).getCpf().equals(cpf)) {
          return u;
        }
      }

      if (u instanceof Bibliotecario) {
        if (((Bibliotecario) u).getCpf().equals(cpf)) {
          return u;
        }
      }
    }

    return null;
  }

  private static Emprestimo buscarEmprestimo(String cpf, String titulo){
    for (Emprestimo e : banco.getEmprestimos()) {
      if (e.getUsuario().getCpf().equals(cpf) && e.getLivro().getTitulo().equals(titulo)) {
        return e;
      }
    }

    return null;
  }

  public static void exibirLivros(){

    for (Livro livro : banco.getLivros()){
      System.out.println("\n#--------------------------#\n");
      livro.exibirDetalhes();
    }
  }

  public static void exibirUsuarios(){

    for (Usuario u : banco.getUsuarios()){
      System.out.println("\n########################################");
      if (u instanceof Bibliotecario){
        System.out.println("Bibliotecario:");
      }
      if (u instanceof Estudante){
        System.out.println("Estudante:");
      }
      if (u instanceof Professor) {
        System.out.println("Professor:");
      }
      u.exibirDetalhes();
    }
  }

  public static void exibirEmprestimos(String cpf){

    Usuario u = buscarPorCpf(cpf);
    int aux = 0;

    for (Emprestimo emp : banco.getEmprestimos()){
      if (emp.getUsuario().getCpf().equals(cpf)){
        aux++;
        System.out.println("\n#--------------------------#\n");
        emp.exibirDetalhes();
      }
    }
    if (aux == 0){
      System.out.println("O usuario não possui nenhum emprestimos ativo!");
    }
  }

  public static void devolverLivro(String nome, String titulo){

    Emprestimo emp = buscarEmprestimo(nome, titulo);

    if (emp == null){
      System.out.println("Emprestimo não localizado!");
      return;
    } else {
      banco.getEmprestimos().remove(emp);
    }
  }

  public static int calcularQtdEmprestimos(String cpf){

    Usuario u = buscarPorCpf(cpf);
    int qtd = 0;
    if (u == null){
      System.out.println("Usuario não encontrado!");
    } else {
      for (Emprestimo e : banco.getEmprestimos()){
        if (e.getUsuario().getCpf().equals(cpf)){
          qtd++;
        }
      }
    }
    return qtd;
  }

  public static int validarEmprestimo(String cpf){

    Usuario u = buscarPorCpf(cpf);
    int qtdEmprestimos = calcularQtdEmprestimos(cpf);
    int verificador = 0;

    if (u == null){
      System.out.println("Usuario não encontrado!");
    } else if (u instanceof Estudante && qtdEmprestimos > 3){
    	verificador = 1;
    } else if (u instanceof Professor && qtdEmprestimos > 5){
    	verificador = 1;
    } else if (u instanceof Bibliotecario && qtdEmprestimos > 5){
    	verificador = 1;
    }
    
    return verificador;
    
  }

  public static void salvarUsuarios(){

    try{
      FileOutputStream arquivoSaida = new FileOutputStream("usuarios.bin");
      ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
      saida.writeObject(banco.getUsuarios());
      saida.close();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
  }

  public static void salvarLivros(){

    try{
      FileOutputStream arquivoSaida = new FileOutputStream("livros.bin");
      ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
      saida.writeObject(banco.getLivros());
      saida.close();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
  }

  public static void salvarEmprestimos(){

    try{
      FileOutputStream arquivoSaida = new FileOutputStream("emprestimos.bin");
      ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
      saida.writeObject(banco.getEmprestimos());
      saida.close();
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
  }

  public static void lerUsuarios() {

    try{
      FileInputStream arquivoEntrada = new FileInputStream("usuarios.bin");
      ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada);
      banco.setUsuarios((ArrayList<Usuario>) entrada.readObject());
      entrada.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void lerLivros() {

    try{
      FileInputStream arquivoEntrada = new FileInputStream("livros.bin");
      ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada);
      banco.setLivros((ArrayList<Livro>)entrada.readObject());
      entrada.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void lerEmprestimos() {

    try{
      FileInputStream arquivoEntrada = new FileInputStream("emprestimos.bin");
      ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada);
      banco.setEmprestimos((ArrayList<Emprestimo>) entrada.readObject());
      entrada.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }



  public static void menuBibliotecario(){

    //Apenas o bibliotecário poderá inserir, emprestar, devolver e excluir livros (utilize uma autenticação inicial para confirmar o perfil de bibliotecário).

    lerUsuarios();
    lerLivros();
    lerEmprestimos();

    do {

      System.out.println("\n------------\n");
      System.out.println("       MENU");
      System.out.println("Escolha uma opção");
      System.out.println("1- Adicionar Estudante");
      System.out.println("2- Adicionar Professor");
      System.out.println("3- Inserir livro");
      System.out.println("4- Emprestar livro");
      System.out.println("5- Devolver livro");
      System.out.println("6- Excluir livro");
      System.out.println("7- Exibir Emprestimos ativos");
      System.out.println("8- Mostrar livros");
      System.out.println("9- Exibir Usuarios cadastrados");
      System.out.println("0- Sair");

      opc = input.nextInt();
      input.nextLine();

      switch(opc) {

        case 1:
          System.out.println("Nome: ");
          nome = input.nextLine();
          System.out.println("CPF: ");
          cpf = input.nextLine();
          System.out.println("Matricula: ");
          matricula = input.nextLine();
          System.out.println("Data de Nascimento: ");
          dataNascimento = input.nextLine();
          System.out.println("Curso: ");
          curso = input.nextLine();
          adicionarEstudante(nome, cpf, matricula,dataNascimento,curso);
          break;
        case 2:
          System.out.println("Nome: ");
          nome = input.nextLine();
          System.out.println("CPF: ");
          cpf = input.nextLine();
          System.out.println("Matricula: ");
          matricula = input.nextLine();
          System.out.println("Data de Nascimento: ");
          dataNascimento = input.nextLine();
          System.out.println("Departamento: ");
          departamento = input.nextLine();
          adicionarProfessor(nome, cpf, matricula,dataNascimento,departamento);
          break;
        case 3:
          System.out.println("Insira o titulo do livro: ");
          titulo = input.nextLine();
          System.out.println("Insira o autor do livro: ");
          autor = input.nextLine();
          System.out.println("Insira o assunto do livro: ");
          assunto = input.nextLine();
          System.out.println("Insira a data de lançamento: ");
          dataLancamento = input.nextLine();
          System.out.println("Insira a quantidade de livros: ");
          qtdEstoque = input.nextInt();
          adicionarLivro(titulo, autor, assunto, dataLancamento, qtdEstoque);
          break;
        case 4:
          System.out.println("Insira o CPF do usuario do emprestimo");
          cpf = input.nextLine();
          usuario = buscarPorCpf(cpf);
          if (usuario == null){
            System.out.println("Usuario não localizado!");
            break;
          } else {
            if (validarEmprestimo(cpf) == 0){
              System.out.println("Insira o titulo do livro: ");
                titulo = input.nextLine();
                livro = buscarPorTitulo(titulo);
                if (livro == null){
                  System.out.println("Livro não localizado!");
                  break;
                } else {
                  livro.checarDisponibilidade();
                  if (livro.getStatus() == LivroStatus.EMPRESTADO){
                    System.out.println("Livro emprestado!");
                    break;
                  } else {
                    int aux = livro.getQtdEstoque();
                    System.out.println("Insira o dia emprestimo: ");
                    dia = input.nextInt();
                    System.out.println("Insira o mes emprestimo: ");
                    mes = input.nextInt();
                    System.out.println("Insira o ano emprestimo: ");
                    ano = input.nextInt();
                    dataEmprestimo = LocalDate.of(ano,mes,dia);

                    System.out.println("Insira o dia devolução: ");
                    dia = input.nextInt();
                    System.out.println("Insira o mes devolução: ");
                    mes = input.nextInt();
                    System.out.println("Insira o ano devolução: ");
                    ano = input.nextInt();
                    dataDev = LocalDate.of(ano,mes,dia);

                    Period periodo = Period.between(dataEmprestimo, dataDev);

                    if (periodo.getDays() > 30 || periodo.getMonths() >= 1){
                      System.out.println("\nEmprestimo não permitido!");
                      break;
                    } else {
                      adicionarEmprestimo(usuario, livro, dataEmprestimo, dataDev);
                      aux -= 1;
                      livro.setQtdEstoque(aux);
                    }
                  }
                }
              } else {
                System.out.println("Limite de emprestimos excedido!");
              }
            }
          break;
        case 5:
          System.out.println("CPF do usuário do emprestimo: ");
          cpf = input.nextLine();
          usuario = buscarPorCpf(cpf);
          if (usuario == null){
            System.out.println("Usuario não localizado!");
            break;
          } else {
            System.out.println("Titulo do livro: ");
            titulo = input.nextLine();
            livro = buscarPorTitulo(titulo);
            if (livro == null){
              System.out.println("Livro não localizado!");
              break;
            } else {
              devolverLivro(nome, titulo);
            }
          }
          break;
        case 6:
          System.out.println("Insira o titulo do livro: ");
          titulo = input.nextLine();
          removerLivro(titulo);
          break;
        case 7:
          System.out.println("Digite o CPF do usuario que deseja ver os emprestimos ativos: ");
          cpf = input.nextLine();
          usuario = buscarPorCpf(cpf);
          if (usuario == null){
            System.out.println("Usuario não localizado!");
            break;
          } else {
            exibirEmprestimos(cpf);
          }
          break;
        case 8:
          exibirLivros();
          break;
        case 9:
          exibirUsuarios();
          break;
        case 0:
          salvarUsuarios();
          salvarLivros();
          salvarEmprestimos();
          break;
        default:
          System.out.println("Opção inválida!");
      }
      
    } while (opc != 0);
    

    
  }
  
}