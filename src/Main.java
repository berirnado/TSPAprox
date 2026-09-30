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

            //Monte Carlo
            System.out.println("\nExecutando o aproximativo...");
            TSPAprox aprox = new TSPAprox(matrizAdjacencia, 42); //seed fixa pra verificar resutlados
            int amostras = 1000;
            int k = 3;

            int rota[] = aprox.monteCarloNearestNeighbor(amostras, k);

            System.out.println("Solução Aproximativa:");
            System.out.println("Rota: " + Arrays.toString(rota));
            System.out.println("Custo: " + aprox.custoDoCiclo(rota));
            System.out.println("Custo ótimo esperado: " + fileParser.getOptimalSolution());

            //Exato
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
