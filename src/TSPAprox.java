import java.util.Arrays;
import java.util.Random;

public class TSPAprox {
    private int optimalSolution;
    private final int[][] adjMatrix;
    private final Random random;

    public TSPAprox(int[][] adjMatrix) {
        this(adjMatrix, new Random());
    }

    // Seed fixa (para avaliar os resultados)
    public TSPAprox(int[][] adjMatrix, long seed) {
        this(adjMatrix, new Random(seed));
    }

    private TSPAprox(int[][] adjMatrix, Random random) {
        this.adjMatrix = adjMatrix;
        this.random = random;
    }

    public void setOptimalSolution(int optimalSolution) {
        this.optimalSolution = optimalSolution;
    }

    public int getOptimalSolution() {
        return optimalSolution;
    }

    // soma das arestas da rota
    public int custoDoCiclo(int[] rota) {
        int custo = 0;
        for (int i = 0; i < rota.length - 1; i++) {
            custo += adjMatrix[rota[i]][rota[i + 1]];
        }
        return custo + adjMatrix[rota[rota.length - 1]][rota[0]];
    }

    // Monte Carlo
    public int[] monteCarloNearestNeighbor(int runs, int k) {
        int n = adjMatrix.length;
        int[] melhorRota = null;
        int melhorCusto = Integer.MAX_VALUE;

        for (int r = 0; r < runs; r++) {
            int inicio = random.nextInt(n);
            int[] rota = randomizedNearestNeighbor(inicio, k);
            int custo = custoDoCiclo(rota);
            if (custo < melhorCusto) {
                melhorCusto = custo;
                melhorRota = rota;
            }
        }
        return melhorRota;
    }

    // NN ranzomizado
    public int[] randomizedNearestNeighbor(int start, int k) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int[] rota = new int[n];

        int current = start;
        rota[0] = current;
        visited[current] = true;

        for (int step = 1; step < n; step++) {
            int[] candidatos = kNearestUnvisited(current, visited, k);
            int next = candidatos[random.nextInt(candidatos.length)];
            rota[step] = next;
            visited[next] = true;
            current = next;
        }
        return rota;
    }

    // Devolve até k cidades não visitadas
    private int[] kNearestUnvisited(int current, boolean[] visited, int k) {
        int n = adjMatrix.length;
        int[] melhor = new int[k];
        int count = 0;

        for (int j = 0; j < n; j++) {
            if (visited[j]) continue;
            int dist = adjMatrix[current][j];

            if (count == k && dist >= adjMatrix[current][melhor[k - 1]]) continue;

            int pos = (count < k) ? count++ : k - 1;

            while (pos > 0 && adjMatrix[current][melhor[pos - 1]] > dist) {
                melhor[pos] = melhor[pos - 1];
                pos--;
            }
            melhor[pos] = j;
        }
        return Arrays.copyOf(melhor, count);
    }
}