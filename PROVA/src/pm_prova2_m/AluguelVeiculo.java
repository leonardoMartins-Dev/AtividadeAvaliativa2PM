package pm_prova2_m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Base dos tipos de aluguel: guarda os varios veiculos de um aluguel
// sem precisar alterar a classe Aluguel, que veio pronta
public abstract class AluguelVeiculo extends Aluguel {

    private ArrayList<Veiculo> veiculos;

    public AluguelVeiculo(String nomeCliente, int quantidadeDias) {

        super(nomeCliente, quantidadeDias, 0, StatusAluguel.ATIVO);
        this.veiculos = new ArrayList<Veiculo>();
    }

    // Somente leitura: para incluir um veiculo, use associarVeiculo
    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    // Associa um veiculo disponivel ao aluguel; o veiculo passa a ALUGADO
    public boolean associarVeiculo(Veiculo veiculo) {
        if (veiculo == null || getStatus() != StatusAluguel.ATIVO) {
            return false;
        }
        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL || veiculos.contains(veiculo)) {
            return false;
        }
        veiculos.add(veiculo);
        veiculo.setStatus(StatusVeiculo.ALUGADO);
        setValorTotal(calcularValorTotal());
        return true;
    }

    // Soma das diarias de todos os veiculos vezes a quantidade de dias
    public double calcularValorTotal() {
        double somaDiarias = 0;
        for (Veiculo v : veiculos) {
            somaDiarias += v.getValorDiaria();
        }
        return somaDiarias * getQuantidadeDias();
    }

    public abstract String getTipo();

    // Dados comuns a todos os tipos; cada subclasse completa no seu exibirDetalhes
    protected void exibirDadosComuns() {
        System.out.println("--- Aluguel " + getTipo() + " ---");
        System.out.println("Cliente: " + getNomeCliente());
        System.out.println("Dias: " + getQuantidadeDias());
        System.out.println("Status: " + getStatus());
        System.out.println("Seguro incluso: " + (possuiSeguroIncluso() ? "Sim" : "Nao"));
        System.out.println("Valor total: R$ " + String.format("%.2f", getValorTotal()));
        if (veiculos.isEmpty()) {
            System.out.println("Veiculos: nenhum");
        } else {
            System.out.println("Veiculos:");
            for (Veiculo v : veiculos) {
                System.out.println("  " + v);
            }
        }
    }

    @Override
    public String toString() {
        return "Aluguel " + getTipo() + " - cliente " + getNomeCliente() + " - " + veiculos.size() + " veiculo(s) - " + getStatus();
    }

}
