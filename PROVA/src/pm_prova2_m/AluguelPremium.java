package pm_prova2_m;

// Premium: quilometragem ilimitada, possui seguro incluso
public class AluguelPremium extends AluguelVeiculo {

    public AluguelPremium(String nomeCliente, int quantidadeDias) {

        super(nomeCliente, quantidadeDias);
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Premium";
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: ilimitada");
    }

}
