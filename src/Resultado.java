public class Resultado {

  private String instancia;
  private int custoEncontrado;
  private int custoOtimo;
  private long tempo;
  private boolean timeout;

public Resultado( String instancia, int custoEncontrado, int custoOtimo, long tempo, boolean timeout ) {
  
    this.instancia = instancia;
    this.custoEncontrado = custoEncontrado;
    this.custoOtimo = custoOtimo;
    this.tempo = tempo;
    this.timeout = timeout;
}

public double calcularGap () {
  return ((double) (custoEncontrado - custoOtimo) / custoOtimo)*100;
}


public void mostrarResumo() {
  System.out.println("\n== Resultado ==");
  System.out.println("Instância: " + instancia);
  System.out.println("Custo encontrado: " + custoEncontrado);
  System.out.println("Custo ótimo: " + custoOtimo);
  System.out.printf("Gap: %.2f%%%n" , calcularGap());
   System.out.println("Tempo: " + tempo + " ns");

   if(timeout) {
      System.out.println("Status: Tempo limite atingido");
   }else {
      System.out.println("Status: Concluído");
   }
   

}

}