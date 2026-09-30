import java.util.Arrays;

public class TSPExato {
    private int[][] adj;
    private int numCidades;

    private int[] caminho;
    private boolean[] visitado;

    private int melhorCusto;
    private int[] melhorRota;

    private long maxTempoMs;
    private long tInicio;
    private long chamadas;
    private boolean estourouTempo;

    public void resolver(int[][] adj, long maxTempoMs){
        this.adj = adj;
        this.numCidades = adj.length;
        this.maxTempoMs = maxTempoMs;

        this.caminho = new int[numCidades];
        this.visitado = new boolean[numCidades];
        this.melhorCusto = Integer.MAX_VALUE;
        this.melhorRota = null;
        this.chamadas = 0;
        this.estourouTempo = false;

        caminho[0] = 0;
        visitado[0] = true;

        this.tInicio = System.currentTimeMillis();
        visitar(1, 0);
    }

    private void visitar(int posicao, int custoAtual){
        // olha o relógio só de vez em quando pra não deixar o algoritmo lento
        chamadas++;
        if(chamadas % 100000 == 0 && System.currentTimeMillis() - tInicio > maxTempoMs){
            estourouTempo = true;
        }
        if(estourouTempo){
            return;
        }

        // poda: essa rota parcial já é pior que a melhor que temos
        if(custoAtual >= melhorCusto){
            return;
        }

        if(posicao == numCidades){
            int custoTotal = custoAtual + adj[caminho[numCidades - 1]][0];
            if(custoTotal < melhorCusto){
                melhorCusto = custoTotal;
                melhorRota = Arrays.copyOf(caminho, numCidades);
            }
            return;
        }

        int ultima = caminho[posicao - 1];
        for(int cidade = 1; cidade < numCidades; cidade++){
            if(!visitado[cidade]){
                visitado[cidade] = true;
                caminho[posicao] = cidade;

                visitar(posicao + 1, custoAtual + adj[ultima][cidade]);

                visitado[cidade] = false;
            }
        }
    }

    public int getMelhorCusto(){
        return this.melhorCusto;
    }

    public int[] getMelhorRota(){
        return this.melhorRota;
    }

    public boolean estourouTempo(){
        return this.estourouTempo;
    }
}
