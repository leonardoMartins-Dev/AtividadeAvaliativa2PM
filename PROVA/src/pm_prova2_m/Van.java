package pm_prova2_m;

// Van: possui quantidade maxima de passageiros, pode receber avaliacao, pode ser inspecionada
public class Van extends Veiculo implements Avaliavel, Inspecionavel {

    public static final double LIMITE_KM_INSPECAO = 150000;
    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;

    private int quantidadeMaximaPassageiros;
    private int nota; // 0 = ainda nao foi avaliada

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, StatusVeiculo status, int quantidadeMaximaPassageiros) {

        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadeMaximaPassageiros = quantidadeMaximaPassageiros;
        this.nota = 0;
    }

    public int getQuantidadeMaximaPassageiros() {
        return quantidadeMaximaPassageiros;
    }

    public void setQuantidadeMaximaPassageiros(int quantidadeMaximaPassageiros) {
        this.quantidadeMaximaPassageiros = quantidadeMaximaPassageiros;
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

    // Acima do limite de km a van vai para manutencao; abaixo, fica disponivel
    @Override
    public void realizarInspecao() {
        if (getStatus() == StatusVeiculo.ALUGADO) {
            System.out.println("Van " + getPlaca() + " esta alugada, nao pode ser inspecionada.");
            return;
        }
        if (getQuilometragem() > LIMITE_KM_INSPECAO) {
            setStatus(StatusVeiculo.MANUTENCAO);
        } else {
            setStatus(StatusVeiculo.DISPONIVEL);
        }
        System.out.println("Van " + getPlaca() + " inspecionada: " + getStatus());
    }

    // O enunciado nao define seguro especial: a van sempre tem, por transportar varios passageiros
    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Van ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Ano: " + getAnoFabricacao());
        System.out.println("Diaria: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Quilometragem: " + String.format("%.0f", getQuilometragem()) + " km");
        System.out.println("Status: " + getStatus());
        System.out.println("Maximo de passageiros: " + quantidadeMaximaPassageiros);
        System.out.println("Seguro especial: " + (possuiSeguroEspecial() ? "Sim" : "Nao"));
        System.out.println("Nota: " + (nota == 0 ? "Sem avaliacao" : nota));
    }

    @Override
    public String toString() {
        return "Van " + getModelo() + " (" + getPlaca() + ") - " + getStatus();
    }

}
