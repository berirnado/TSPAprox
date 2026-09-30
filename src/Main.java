import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        FileParser fileParser = new FileParser();
        TSPExato exato = new TSPExato();

        long tempoLimiteMs = 60000;

        while (true) {
            int opcaoMenu = menu.escolherExemplo();
            
            if (opcaoMenu == 0) {
                System.out.println("Saindo...");
                break;
            }

            fileParser.loadExample(opcaoMenu);
            int[][] matrizAdjacencia = fileParser.getAdjMatrix();

            System.out.println("\nExecutando exato (timeout: " + (tempoLimiteMs / 1000) + "s)...");
            exato.resolver(matrizAdjacencia, tempoLimiteMs);

            if (exato.estourouTempo()) {
                System.out.println("Timeout! O tempo limite foi atingido.");
                if (exato.getMelhorRota() != null) {
                    System.out.println("Melhor custo parcial encontrado: " + exato.getMelhorCusto());
                }
            } else {
                System.out.println("Custo ótimo encontrado: " + exato.getMelhorCusto());
                System.out.println("Rota: " + Arrays.toString(exato.getMelhorRota()));
            }

            System.out.println("Custo ótimo esperado: " + fileParser.getOptimalSolution());
            System.out.println("----------------------------------------");
        }
    }
}