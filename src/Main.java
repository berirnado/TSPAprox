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

            ExecutionTimer timer = new ExecutionTimer();
            timer.start();
            int rota[] = aprox.monteCarloNearestNeighbor(amostras, k);
            long tempo = timer.stop();
            int custo = aprox.custoDoCiclo(rota);

            Resultado resultado = new Resultado(
                fileParser.getNomeInstancia(),
                custo,
                fileParser.getOptimalSolution(),
                tempo,
                false
            );
            
            resultado.mostrarResumo();
            System.out.println("Rota: " + Arrays.toString(rota));
          

            //Exato
            System.out.println("\nExecutando exato (timeout: " + (tempoLimiteMs / 1000) + "s)...");
            ExecutionTimer timerExato = new ExecutionTimer();
            timerExato.start();
            exato.resolver(matrizAdjacencia, tempoLimiteMs);
            long tempoExato = timerExato.stop();

            Resultado resultadoExato = new Resultado(
                fileParser.getNomeInstancia(),
                exato.getMelhorCusto(),
                fileParser.getOptimalSolution(),
                tempoExato,
                exato.estourouTempo()
            );

            resultadoExato.mostrarResumo();

            if (exato.getMelhorRota() != null) {
                System.out.println("Rota: " + Arrays.toString(exato.getMelhorRota()));
            }

             System.out.println("----------------------------------------");
        }
    }
}
