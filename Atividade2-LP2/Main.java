import utils.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

     // Adicionando Bibliotecario
    BibliotecaFunctions.adicionarBibliotecario("Leandro", "000.000.000-10", "202410", "06/10/1994", "login123", "senha123");

    /*

    //Adicionando Estudantes
    BibliotecaFunctions.adicionarEstudante("João", "000.000.000-01", "202401", "01/01/2001", "BTI");

    BibliotecaFunctions.adicionarEstudante("Maria", "000.000.000-02", "202402", "02/02/2002", "Engenharia");

    BibliotecaFunctions.adicionarEstudante("Antonio", "000.000.000-03", "202403", "01/01/2003", "Matemática");

    //Adicionando Professores
   
    BibliotecaFunctions.adicionarProfessor("Ricardo", "000.000.000-05", "202405", "03/01/1995", "IMD");

    BibliotecaFunctions.adicionarProfessor("Charles", "000.000.000-06", "202406", "01/03/1978", "IMD");

    BibliotecaFunctions.adicionarProfessor("Felipe", "000.000.000-07", "202407", "02/07/1997", "IMD");
    
     
    //Adicionando Livros
    BibliotecaFunctions.adicionarLivro("livro1", "autor1", "assunto 1", "2020", 3);
    
    BibliotecaFunctions.adicionarLivro("livro2", "autor2", "assunto2", "2020", 2);

    BibliotecaFunctions.adicionarLivro("livro3", "autor3", "assunto3", "2020", 5);

    */

    int opc;
    Scanner input;

    input = new Scanner(System.in);

    System.out.println("\n---------------\n");
    System.out.println("        MENU");
    System.out.println("Escolha um Menu: ");
    System.out.println("1- Estudante");
    System.out.println("2- Professor");
    System.out.println("3- Bibliotecario");

    opc = input.nextInt();

    if (opc == 1){
      // Menu do estudante
      System.out.println("Menu do estudante");
    }

    if (opc == 2) {
      // Menu do professor
      System.out.println("Menu do professor");
    }

    if (opc == 3) {
      BibliotecaFunctions.menuBibliotecario();
    }
    input.close();
  }
}