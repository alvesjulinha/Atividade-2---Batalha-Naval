//Julia Alves Barroso

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Tabuleiro com os navios
        char[][] tabuleiroReal = new char[10][10];

        // Tabuleiro do atacante

        char[][] tabuleiroDeAtaques = new char[10][10];

        // Inicializa os dois tabuleiros com água
        Metodos.iniciarTabuleiro(tabuleiroReal);
        Metodos.iniciarTabuleiro(tabuleiroDeAtaques);

        // Criador posiciona os navios
        System.out.println("Posicionamento dos Navios");
        Metodos.posicionarTodosNavios(tabuleiroReal);

        // Mostra o tabuleiro final para o criador
        System.out.println("\nTabuleiro do Criador:");
        Metodos.exibirTabuleiro(tabuleiroReal);

        // Esconde o tabuleiro
        System.out.println("\nTroque de jogador e pressione ENTER para continuar");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        // Inicia a fase de ataques
        System.out.println("Ataque");
        Metodos.iniciarAtaques(tabuleiroReal, tabuleiroDeAtaques);

        sc.close();
        }
    }
