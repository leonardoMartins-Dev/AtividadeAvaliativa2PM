package pm_prova2_m;

// Basico: permite ate 100 km por dia, nao possui seguro incluso
public class AluguelBasico extends AluguelVeiculo {

    public static final double LIMITE_KM_POR_DIA = 100;

    public AluguelBasico(String nomeCliente, int quantidadeDias) {

        super(nomeCliente, quantidadeDias);
    }

    // Quantos km o cliente pode rodar no aluguel inteiro
    public double getLimiteKmTotal() {
        return LIMITE_KM_POR_DIA * getQuantidadeDias();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public String getTipo() {
        return "Basico";
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: ate " + String.format("%.0f", LIMITE_KM_POR_DIA) + " km por dia ("
                + String.format("%.0f", getLimiteKmTotal()) + " km no total)");
    }

}
