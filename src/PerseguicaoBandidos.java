import java.io.*;
import java.util.*;

public class PerseguicaoBandidos {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("casoI2000.txt"));
        int linhas = scanner.nextInt();
        int colunas = scanner.nextInt();
        scanner.nextLine();
        long startTime = System.currentTimeMillis();
        char[][] mapa = new char[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            String linha = scanner.nextLine();
            for (int j = 0; j < linha.length(); j++) {
                mapa[i][j] = linha.charAt(j);
            }

        }
        System.out.println("Quantia recuperada pela polícia: " + perseguirBandidos(mapa, linhas, colunas));
        long stopTime = System.currentTimeMillis();
        System.out.println("Tempo de execução: "+(stopTime - startTime)+"ms");
    }

    private static int perseguirBandidos(char[][] mapa, int linhas, int colunas) {
        int dinheiro = 0;
        int direcao = 0; // 0: direita, 1: baixo, 2: esquerda, 3: cima
        int i = 0, j = 0;
        boolean fim=true;
        char atual = mapa[i][j];
        char pas;
        String cache="";

        while (mapa[i][j] != '-') {
            j++;
            if (j >= colunas) { i++; j = 0; }
        }

        outerloop:
        for (j = 0; j < colunas; j++) {
            for (i = 0; i < linhas; i++) {
                if (mapa[i][j] == '-') {
                    atual = mapa[i][j];

                    break outerloop;
                }
            }
        }

        while (fim) {
            pas = atual;
            atual = mapa[i][j];
            if (i < 0 || i > linhas || j < 0 || j > colunas) {
                break;
            }
            if(atual=='0' || atual=='1' || atual=='2' || atual=='3' || atual=='4' || atual=='5' || atual=='6' || atual=='7' || atual=='8' || atual=='9'){
                cache = cache + atual;
            }
            else {
                if(pas=='0' || pas=='1' || pas=='2' || pas=='3' || pas=='4' || pas=='5' || pas=='6' || pas=='7' || pas=='8' || pas=='9') {
                    dinheiro = dinheiro + Integer.parseInt(cache);
                    cache = "";
                }
                switch (atual) {
                    case '-':
                        break;
                    case '\\':
                        direcao = switch (direcao) {
                            case 0 -> 1;
                            case 1 -> 0;
                            case 2 -> 3;
                            case 3 -> 2;
                            default -> direcao;
                        };
                        break;
                    case '/':
                        direcao = switch (direcao) {
                            case 0 -> 3;
                            case 1 -> 2;
                            case 2 -> 1;
                            case 3 -> 0;
                            default -> direcao;
                        };
                        break;
                    case '#':
                        fim = false;
                        break;
                }
            }

            switch (direcao) {
                case 0:
                    j++;
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    j--;
                    break;
                case 3:
                    i--;
                    break;
            }
        }
        return dinheiro;
    }

}







