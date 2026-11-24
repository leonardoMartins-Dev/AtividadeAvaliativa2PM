package pm_prova2_m;

// Executivo: quilometragem ilimitada, oferece motorista particular, pode receber avaliacao
public class AluguelExecutivo extends AluguelVeiculo implements Avaliavel {

    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;

    private String nomeMotorista;
    private int nota; // 0 = ainda nao foi avaliado

    public AluguelExecutivo(String nomeCliente, int quantidadeDias, String nomeMotorista) {

        super(nomeCliente, quantidadeDias);
        this.nomeMotorista = nomeMotorista;
        this.nota = 0;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
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

    // O enunciado so cita seguro incluso no Premium
    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public String getTipo() {
        return "Executivo";
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: ilimitada");
        System.out.println("Motorista particular: " + nomeMotorista);
        System.out.println("Nota: " + (nota == 0 ? "Sem avaliacao" : nota));
    }

}
