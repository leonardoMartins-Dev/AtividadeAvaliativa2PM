package pm_prova2_m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Guarda os veiculos e alugueis e implementa as funcionalidades do sistema
public class Locadora {

    private String nome;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<AluguelVeiculo> alugueis;

    public Locadora(String nome) {

        this.nome = nome;
        this.veiculos = new ArrayList<Veiculo>();
        this.alugueis = new ArrayList<AluguelVeiculo>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public List<AluguelVeiculo> getAlugueis() {
        return Collections.unmodifiableList(alugueis);
    }

    // ---------- CADASTRO ----------

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null || buscarVeiculo(veiculo.getPlaca()) != null) {
            return false;
        }
        veiculos.add(veiculo);
        return true;
    }

    public boolean adicionarAluguel(AluguelVeiculo aluguel) {
        if (aluguel == null || alugueis.contains(aluguel)) {
            return false;
        }
        alugueis.add(aluguel);
        return true;
    }

    public Veiculo buscarVeiculo(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    // ---------- 1. ASSOCIAR VEICULOS A UM ALUGUEL ----------

    public boolean associarVeiculo(AluguelVeiculo aluguel, String placa) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (aluguel == null || veiculo == null || !alugueis.contains(aluguel)) {
            return false;
        }
        return aluguel.associarVeiculo(veiculo);
    }

    // ---------- 2. EXIBIR TODOS OS VEICULOS ----------

    public void exibirVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado.");
            return;
        }
        for (Veiculo v : veiculos) {
            v.exibirDetalhes(); // polimorfismo
        }
    }

    // ---------- 3. EXIBIR TODOS OS ALUGUEIS ----------

    public void exibirAlugueis() {
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (AluguelVeiculo a : alugueis) {
            a.exibirDetalhes(); // polimorfismo
        }
    }

    // ---------- 4. EXIBIR APENAS VEICULOS DISPONIVEIS ----------

    public List<Veiculo> getVeiculosDisponiveis() {
        ArrayList<Veiculo> disponiveis = new ArrayList<Veiculo>();
        for (Veiculo v : veiculos) {
            if (v.getStatus() == StatusVeiculo.DISPONIVEL) {
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }

    public void exibirVeiculosDisponiveis() {
        List<Veiculo> disponiveis = getVeiculosDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veiculo disponivel.");
            return;
        }
        for (Veiculo v : disponiveis) {
            v.exibirDetalhes();
        }
    }

    // ---------- 5. AVALIAR OBJETOS AVALIAVEIS ----------

    // Veiculos e alugueis que implementam Avaliavel (Carro, Van e AluguelExecutivo)
    public List<Avaliavel> getAvaliaveis() {
        ArrayList<Avaliavel> avaliaveis = new ArrayList<Avaliavel>();
        for (Veiculo v : veiculos) {
            if (v instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) v);
            }
        }
        for (AluguelVeiculo a : alugueis) {
            if (a instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) a);
            }
        }
        return avaliaveis;
    }

    // ---------- 6. INSPECIONAR VEICULOS INSPECIONAVEIS ----------

    // Retorna quantos veiculos eram inspecionaveis (Moto e Van)
    public int inspecionarVeiculos() {
        int total = 0;
        for (Veiculo v : veiculos) {
            if (v instanceof Inspecionavel) {
                ((Inspecionavel) v).realizarInspecao();
                total++;
            }
        }
        return total;
    }

    // ---------- 7. REMOVER VEICULOS EM MANUTENCAO ----------

    // Retorna quantos veiculos foram removidos
    public int removerVeiculosEmManutencao() {
        int removidos = 0;
        Iterator<Veiculo> it = veiculos.iterator();
        while (it.hasNext()) {
            Veiculo v = it.next();
            if (v.getStatus() == StatusVeiculo.MANUTENCAO) {
                System.out.println("Removido: " + v);
                it.remove();
                removidos++;
            }
        }
        return removidos;
    }

}
