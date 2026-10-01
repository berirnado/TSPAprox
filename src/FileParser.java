import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    private File file;
    private int[][] adjMatrix;
    private int optimalSolution;
    private String nomeInstancia;

    public void generateAdjMatrix(){
        String[] linhaArray;
        List<int[]> linhas = new ArrayList<>();

        try (Scanner scanner = new Scanner(this.file)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                linhaArray = linha.split("\\s+");

                int[] valores = new int[linhaArray.length];

                //adiciona cada linha da matriz de adjacência
                for(int i = 0; i < linhaArray.length; i++){
                    // adicionar cada número à matriz de adjacência
                    valores[i] = Integer.parseInt(linhaArray[i]);
                }
                linhas.add(valores);
            }
        } catch(FileNotFoundException e){
            System.out.println("Ocorreu um erro ao abrir o arquivo.");
            e.printStackTrace();
            return;
        }

        //transforma linhas parseadas em matriz de adjacencia
        this.adjMatrix = linhas.toArray(new int[0][]);
    }

    public void loadExample(int exemplo){
        switch(exemplo){
            case 1:
                this.file = new File("src/examples/tsp1_253.txt");
                this.optimalSolution = 253;
                this.nomeInstancia = "tsp1_253";
                break;
            case 2:
                this.file = new File("src/examples/tsp2_1248.txt");
                this.optimalSolution = 1248;
                this.nomeInstancia = "tsp2_1248";
                break;
            case 3:
                this.file = new File("src/examples/tsp3_1194.txt");
                this.optimalSolution = 1194;
                this.nomeInstancia = "tsp3_1194";
                break;
            case 4:
                this.file = new File("src/examples/tsp4_7013.txt");
                this.optimalSolution = 7013;
                this.nomeInstancia = "tsp4_7013";
                break;
            case 5:
                this.file = new File("src/examples/tsp5_27603.txt");
                this.optimalSolution = 27603;
                this.nomeInstancia = "tsp5_27603";
                break;
            default:
                System.out.println("Escolha um arquivo válido.");
        }
        this.generateAdjMatrix();
    }

    public int[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    public int getOptimalSolution(){
        return this.optimalSolution;
    }

    public String getNomeInstancia() {
        return this.nomeInstancia;
    }

}
