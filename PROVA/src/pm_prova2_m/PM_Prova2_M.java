package pm_prova2_m;

import java.util.List;
import java.util.Scanner;

public class PM_Prova2_M {

    public static void main(String[] args) {
        Locadora locadora = new Locadora("Locadora PM");
        criarDadosIniciais(locadora);

        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro(sc, "Escolha uma opcao: ");
            switch (opcao) {
                case 1:
                    associarVeiculos(sc, locadora);
                    break;
                case 2:
                    titulo("TODOS OS VEICULOS");
                    locadora.exibirVeiculos();
                    break;
                case 3:
                    titulo("TODOS OS ALUGUEIS");
                    locadora.exibirAlugueis();
                    break;
                case 4:
                    titulo("VEICULOS DISPONIVEIS");
                    locadora.exibirVeiculosDisponiveis();
                    break;
                case 5:
                    avaliar(sc, locadora);
                    break;
                case 6:
                    titulo("INSPECAO");
                    if (locadora.inspecionarVeiculos() == 0) {
                        System.out.println("Nenhum veiculo inspecionavel cadastrado.");
                    }
                    break;
                case 7:
                    titulo("REMOVER VEICULOS EM MANUTENCAO");
                    int removidos = locadora.removerVeiculosEmManutencao();
                    System.out.println(removidos + " veiculo(s) removido(s).");
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
        sc.close();
    }

    // Cria 3 veiculos e 2 alugueis assim que o programa e executado
    public static void criarDadosIniciais(Locadora locadora) {
        locadora.adicionarVeiculo(new Carro("ABC1D23", "Onix", 2022, 150.0, 30000, StatusVeiculo.DISPONIVEL, 4));
        locadora.adicionarVeiculo(new Moto("MOT2E45", "CB 500", 2019, 90.0, 62000, StatusVeiculo.DISPONIVEL, 500));
        locadora.adicionarVeiculo(new Van("VAN3F67", "Sprinter", 2021, 400.0, 80000, StatusVeiculo.DISPONIVEL, 15));

        locadora.adicionarAluguel(new AluguelBasico("Ana Souza", 5));
        locadora.adicionarAluguel(new AluguelExecutivo("Bruno Lima", 3, "Carlos Pereira"));
    }

    private static void exibirMenu() {
        titulo("MENU");
        System.out.println("1 - Associar veiculos a um aluguel");
        System.out.println("2 - Exibir todos os veiculos");
        System.out.println("3 - Exibir todos os alugueis");
        System.out.println("4 - Exibir veiculos disponiveis");
        System.out.println("5 - Avaliar (veiculos e alugueis avaliaveis)");
        System.out.println("6 - Realizar inspecao nos veiculos inspecionaveis");
        System.out.println("7 - Remover veiculos em manutencao");
        System.out.println("0 - Sair");
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("===== " + texto + " =====");
    }

    // ---------- OPCAO 1 ----------

    private static void associarVeiculos(Scanner sc, Locadora locadora) {
        titulo("ASSOCIAR VEICULOS A UM ALUGUEL");
        List<AluguelVeiculo> alugueis = locadora.getAlugueis();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (int i = 0; i < alugueis.size(); i++) {
            System.out.println((i + 1) + " - " + alugueis.get(i));
        }
        int numero = lerInteiro(sc, "Numero do aluguel (0 para voltar): ");
        if (numero == 0) {
            return;
        }
        if (numero < 1 || numero > alugueis.size()) {
            System.out.println("Aluguel inexistente.");
            return;
        }
        AluguelVeiculo aluguel = alugueis.get(numero - 1);

        // Um aluguel pode ter varios veiculos: repete ate o usuario parar
        while (true) {
            List<Veiculo> disponiveis = locadora.getVeiculosDisponiveis();
            if (disponiveis.isEmpty()) {
                System.out.println("Nao ha mais veiculos disponiveis.");
                return;
            }
            System.out.println("Veiculos disponiveis:");
            for (Veiculo v : disponiveis) {
                System.out.println("  " + v);
            }
            String placa = lerTexto(sc, "Placa do veiculo (Enter para terminar): ");
            if (placa.isEmpty()) {
                return;
            }
            Veiculo veiculo = locadora.buscarVeiculo(placa);
            if (veiculo == null) {
                System.out.println("Veiculo nao encontrado.");
            } else if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
                System.out.println("Veiculo nao esta disponivel (" + veiculo.getStatus() + ").");
            } else if (locadora.associarVeiculo(aluguel, placa)) {
                System.out.println("Veiculo " + veiculo.getPlaca() + " associado. Valor total: R$ "
                        + String.format("%.2f", aluguel.getValorTotal()));
            } else {
                System.out.println("Nao foi possivel associar o veiculo.");
            }
        }
    }

    // ---------- OPCAO 5 ----------

    private static void avaliar(Scanner sc, Locadora locadora) {
        titulo("AVALIAR");
        List<Avaliavel> avaliaveis = locadora.getAvaliaveis();
        if (avaliaveis.isEmpty()) {
            System.out.println("Nenhum objeto avaliavel cadastrado.");
            return;
        }
        for (int i = 0; i < avaliaveis.size(); i++) {
            System.out.println((i + 1) + " - " + avaliaveis.get(i));
        }
        int numero = lerInteiro(sc, "O que deseja avaliar (0 para voltar): ");
        if (numero == 0) {
            return;
        }
        if (numero < 1 || numero > avaliaveis.size()) {
            System.out.println("Opcao inexistente.");
            return;
        }
        int nota = lerInteiro(sc, "Nota (1 a 5): ");
        if (nota < 1 || nota > 5) {
            System.out.println("Nota invalida. Use de 1 a 5.");
            return;
        }
        Avaliavel escolhido = avaliaveis.get(numero - 1);
        escolhido.avaliar(nota); // polimorfismo pela interface
        System.out.println("Avaliado com nota " + nota + ": " + escolhido);
    }

    // ---------- LEITURA ----------

    private static String lerTexto(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        if (!sc.hasNextLine()) {
            return "";
        }
        return sc.nextLine().trim();
    }

    // Repete ate o usuario digitar um numero inteiro; sem entrada, devolve 0 (sair)
    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!sc.hasNextLine()) {
                return 0;
            }
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

}
