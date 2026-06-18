//Julia Alves Barroso

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
// Tabuleiro com os navios
        char[][] tabuleiroReal = new char[10][10];

        // Tabuleiro do atacante

        char[][] tabuleiroDeAtaques = new char[10][10];

        // Inicia os dois tabuleiros
        Metodos.iniciarTabuleiro(tabuleiroReal);
        Metodos.iniciarTabuleiro(tabuleiroDeAtaques);

        // Criador posiciona os navios
        System.out.println("Posicionamento dos Navios");
        Metodos.posicionarTodosNavios(tabuleiroReal);

        // Mostra o tabuleiro final para o criador
        System.out.println("\nTabuleiro do Criador:");
        Metodos.exibirTabuleiro(tabuleiroReal);

        // Esconde os navios do atacante para o ínicio do ataque
        System.out.println("\nTroque de jogador e pressione ENTER para continuar");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        System.out.println("Ataque");
        Metodos.iniciarAtaques(tabuleiroReal, tabuleiroDeAtaques);

        sc.close();
        }
    }
