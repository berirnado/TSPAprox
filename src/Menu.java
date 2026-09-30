import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    private String[] nomes = {"tsp1_253", "tsp2_1248", "tsp3_1194", "tsp4_7013", "tsp5_27603"};
    private int[] tamanhos = {11, 6, 15, 44, 29};

    public void mostrarOpcoes(){
        System.out.println("=== Problema do Caixeiro Viajante ===");
        System.out.println("Escolha o exemplo que deseja resolver:");
        for(int i = 0; i < nomes.length; i++){
            System.out.println((i + 1) + " - " + nomes[i] + " (" + tamanhos[i] + " cidades)");
        }
        System.out.println("0 - Sair");
    }

    public int escolherExemplo(){
        while(true){
            mostrarOpcoes();
            System.out.print("Opção: ");
            String entrada = scanner.nextLine().trim();

            try {
                int opcao = Integer.parseInt(entrada);
                if(opcao >= 0 && opcao <= nomes.length){
                    return opcao;
                }
            } catch(NumberFormatException e){
            }
            System.out.println("Opção inválida, tente novamente.\n");
        }
    }
}
