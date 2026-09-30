import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

void main() {
    FileParser fileParser = new FileParser();

    fileParser.loadExample(4);
    int adjMatrix[][] = fileParser.getAdjMatrix();

    // Monte Carlo
    Aprox aprox = new Aprox(adjMatrix, 42);
    int runs = 1000;
    int k = 3;

    int[] rota = aprox.monteCarloNearestNeighbor(runs, k);

    System.out.println("Rota: " + Arrays.toString(rota));
    System.out.println("Custo: " + aprox.custoDoCiclo(rota));
}
