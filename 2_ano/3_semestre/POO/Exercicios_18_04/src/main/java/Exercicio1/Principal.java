package Exercicio1;

public class Principal {
    public static void main(String[] args) {
        Gerente gerente = new Gerente("José da Silva", "123.456.789-00", 3650.23, "Faturamento", 300.64);
        Desenvolvedor dev = new Desenvolvedor("Antônio de Oliveira", "321.654.987-00", 2348.67, "Java", 30);
        Estagiario estagiario = new Estagiario("Diego dos Santos", "213.546.879-00", 992.45, "UEM", "Lucas");

        double salarioBrutoGerente = gerente.calcularSalarioFinal();
        double salarioBrutoDev = dev.calcularSalarioFinal();
        double salarioLiquidoGerente = salarioBrutoGerente - gerente.calcularImpostoRenda();
        double salarioLiquidoDev = salarioBrutoDev - dev.calcularImpostoRenda();

        System.out.println("====================================================================================================");
        System.out.print("- Contracheques:");
        System.out.printf(
                "%n\t- Gerente: %n\t\t%s %n\t- Desenvolvedor: %n\t\t%s %n\t- Estagiário: %n\t\t%s",
                gerente.gerarContraCheque(),
                dev.gerarContraCheque(),
                estagiario.gerarContraCheque()
        );

        System.out.printf("%n====================================================================================================%n");
        System.out.print("- IR e faixa de tributação:");
        System.out.printf(
                "%n\t- Gerente: %s %n\t- Desenvolvedor: %s",
                gerente.getFaixaIR(),
                dev.getFaixaIR()
        );

        System.out.printf("%n====================================================================================================%n");
        System.out.print("- Diferença salarial:");
        System.out.printf(
                "%n\t- Gerente: %n\t\t- Bruto: R$%.2f %n\t\t- Líquido: R$%.2f%n\t\t- Diferença: R$%.2f %n\t- Desenvolvedor: %n\t\t- Bruto: R$%.2f %n\t\t- Líquido: R$%.2f%n\t\t- Diferença: R$%.2f",
                salarioBrutoGerente,
                salarioLiquidoGerente,
                salarioBrutoGerente - (salarioBrutoGerente - gerente.calcularImpostoRenda()),
                salarioBrutoDev,
                salarioLiquidoDev,
                salarioBrutoDev - (salarioBrutoDev - dev.calcularImpostoRenda())
        );

        System.out.printf("%n====================================================================================================%n");
    }
}
