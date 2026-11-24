package pm_prova2_m;

// Moto: possui cilindrada, nao recebe avaliacao, pode ser inspecionada
public class Moto extends Veiculo implements Inspecionavel {

    public static final int CILINDRADA_SEGURO_ESPECIAL = 600;
    public static final double LIMITE_KM_INSPECAO = 50000;

    private int cilindrada;

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, StatusVeiculo status, int cilindrada) {

        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    // Acima do limite de km a moto vai para manutencao; abaixo, fica disponivel
    @Override
    public void realizarInspecao() {
        if (getStatus() == StatusVeiculo.ALUGADO) {
            System.out.println("Moto " + getPlaca() + " esta alugada, nao pode ser inspecionada.");
            return;
        }
        if (getQuilometragem() > LIMITE_KM_INSPECAO) {
            setStatus(StatusVeiculo.MANUTENCAO);
        } else {
            setStatus(StatusVeiculo.DISPONIVEL);
        }
        System.out.println("Moto " + getPlaca() + " inspecionada: " + getStatus());
    }

    // O enunciado nao define seguro especial: motos de alta cilindrada tem
    @Override
    public boolean possuiSeguroEspecial() {
        return cilindrada >= CILINDRADA_SEGURO_ESPECIAL;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Moto ---");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Ano: " + getAnoFabricacao());
        System.out.println("Diaria: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Quilometragem: " + String.format("%.0f", getQuilometragem()) + " km");
        System.out.println("Status: " + getStatus());
        System.out.println("Cilindrada: " + cilindrada + " cc");
        System.out.println("Seguro especial: " + (possuiSeguroEspecial() ? "Sim" : "Nao"));
    }

    @Override
    public String toString() {
        return "Moto " + getModelo() + " (" + getPlaca() + ") - " + getStatus();
    }

}
