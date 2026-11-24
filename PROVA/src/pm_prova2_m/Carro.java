package pm_prova2_m;

// Carro: possui quantidade de portas, pode receber avaliacao, nao precisa de inspecao
public class Carro extends Veiculo implements Avaliavel {

    public static final double DIARIA_SEGURO_ESPECIAL = 300.0;
    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;

    private int quantidadePortas;
    private int nota; // 0 = ainda nao foi avaliado

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, StatusVeiculo status, int quantidadePortas) {

        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadePortas = quantidadePortas;
        this.nota = 0;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public void avaliar(int nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            System.out.println("Nota invalida: " + nota + ". Use de " + NOTA_MINIMA + " a " + NOTA_MAXIMA + ".");
            return;
        }
        this.nota = nota;
    }

    // O enunciado nao define seguro especial: carros com diaria alta (carros de luxo) tem
    @Override
    public boolean possuiSeguroEspecial() {
        return getValorDiaria() >= DIARIA_SEGURO_ESPECIAL;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Carro ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Ano: " + getAnoFabricacao());
        System.out.println("Diaria: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Quilometragem: " + String.format("%.0f", getQuilometragem()) + " km");
        System.out.println("Status: " + getStatus());
        System.out.println("Portas: " + quantidadePortas);
        System.out.println("Seguro especial: " + (possuiSeguroEspecial() ? "Sim" : "Nao"));
        System.out.println("Nota: " + (nota == 0 ? "Sem avaliacao" : nota));
    }

    @Override
    public String toString() {
        return "Carro " + getModelo() + " (" + getPlaca() + ") - " + getStatus();
    }

}
