import java.util.Scanner;

public class Metodos {

        static Scanner sc = new Scanner(System.in);

        // Inicia o tabuleiro
        public static void iniciarTabuleiro(char[][] tabuleiro) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tabuleiro[i][j] = '~';
                }
            }
        }

        // Exibe o tabuleiro
        public static void exibirTabuleiro(char[][] tabuleiro) {

            System.out.print("  ");

            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }

            System.out.println();

            for (int i = 0; i < 10; i++) {

                System.out.print(i + " ");

                for (int j = 0; j < 10; j++) {
                    System.out.print(tabuleiro[i][j] + " ");
                }

                System.out.println();
            }
        }

        // Posiciona um navio
        public static boolean posicionarNavio(char[][] tabuleiro,
                                              int linha,
                                              int coluna,
                                              int tamanho,
                                              int aLinha,
                                              int aColuna) {

            for (int i = 0; i < tamanho; i++) {

                int nLinha = linha + (i * aLinha);
                int nColuna = coluna + (i * aColuna);

                if (nLinha < 0 || nLinha >= 10 ||
                        nColuna < 0 || nColuna >= 10) {

                    return false;
                }

                if (tabuleiro[nLinha][nColuna] == 'N') {
                    return false;
                }
            }

            for (int i = 0; i < tamanho; i++) {

                int nLinha = linha + (i * aLinha);
                int nColuna = coluna + (i * aColuna);

                tabuleiro[nLinha][nColuna] = 'N';
            }

            return true;
        }

        // Posiciona um navio escolhido pelo jogador
        public static void posicionarNavioJogador(char[][] tabuleiro, String nome, int tamanho) {

            boolean colocado = false;

            while (!colocado) {

                System.out.println("\nPosicionando " + nome);

                System.out.print("Escolha a primeira linha: ");
                int linha = sc.nextInt();

                System.out.print("Escolha a primeira coluna: ");
                int coluna = sc.nextInt();

                System.out.println("Direções:");
                System.out.println("1 - Horizontal");
                System.out.println("2 - Vertical");
                System.out.println("3 - Diagonal");

                int opcao = sc.nextInt();

                int dl = 0;
                int dc = 0;

                switch (opcao) {
                    case 1:
                        dl = 0;
                        dc = 1;
                        break; //Colunas

                    case 2:
                        dl = 1;
                        dc = 0;
                        break; //Linhas

                    case 3:
                        dl = 1;
                        dc = 1;
                        break; //Diagonal

                    default:
                        System.out.println("Direção inválida!");
                        continue;
                }

                colocado = posicionarNavio(
                        tabuleiro,
                        linha,
                        coluna,
                        tamanho,
                        dl,
                        dc
                );

                if (!colocado) {
                    System.out.println("Posição inválida!");
                }

                exibirTabuleiro(tabuleiro);
            }
        }

        // Posiciona todos os navios
        public static void posicionarTodosNavios(char[][] tabuleiro) {

            posicionarNavioJogador(tabuleiro, "Porta-Aviões", 4);

            posicionarNavioJogador(tabuleiro, "Fragata", 3);

            for (int i = 1; i <= 3; i++) {
                posicionarNavioJogador(tabuleiro,
                        "Submarino " + i,
                        2);
            }

            for (int i = 1; i <= 3; i++) {
                posicionarNavioJogador(tabuleiro,
                        "Bote " + i,
                        1);
            }
        }

        // Realiza um ataque
        public static boolean realizarAtaque(char[][] tabuleiroReal,
                                             char[][] tabuleiroAtaques,
                                             int linha,
                                             int coluna) {

            if (linha < 0 || linha >= 10 ||
                    coluna < 0 || coluna >= 10) {

                System.out.println("Coordenada inválida!");
                return false;
            }

            if (tabuleiroAtaques[linha][coluna] == 'X' ||
                    tabuleiroAtaques[linha][coluna] == 'O') {

                System.out.println("Você já atacou esta posição");
                return false;
            }

            if (tabuleiroReal[linha][coluna] == 'N') {

                tabuleiroReal[linha][coluna] = 'X';
                tabuleiroAtaques[linha][coluna] = 'X';

                System.out.println("Acertou");
                return true;

            } else {

                tabuleiroAtaques[linha][coluna] = 'O';

                System.out.println("ERROU!");
                return false;
            }
        }

        // Rodada de ataque
        public static void realizarRodadaAtaque(char[][] tabuleiroReal,
                                                char[][] tabuleiroAtaques) {

            exibirTabuleiro(tabuleiroAtaques);

            System.out.print("Linha: ");
            int linha = sc.nextInt();

            System.out.print("Coluna: ");
            int coluna = sc.nextInt();

            realizarAtaque(
                    tabuleiroReal,
                    tabuleiroAtaques,
                    linha,
                    coluna
            );
        }

        // Verificando se ainda existem navios
        public static boolean verificarFimDeJogo(char[][] tabuleiro) {

            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < 10; j++) {

                    if (tabuleiro[i][j] == 'N') {
                        return false;
                    }

                }
            }

            return true;
        }

        // Controla a fase de ataques
        public static void iniciarAtaques(char[][] tabuleiroReal,
                                          char[][] tabuleiroDeAtaques) {

            int tentativas = 0;
            int limite = 30;

            while (!verificarFimDeJogo(tabuleiroReal)
                    && tentativas < limite) {

                System.out.println("\nTentativa "
                        + (tentativas + 1)
                        + " de "
                        + limite);

                realizarRodadaAtaque(
                        tabuleiroReal,
                        tabuleiroDeAtaques
                );

                tentativas++;
            }

            if (verificarFimDeJogo(tabuleiroReal)) {
                System.out.println("\nVitória do Atacante");
            } else {
                System.out.println("\nVitória do Criador");
            }
        }

    }
