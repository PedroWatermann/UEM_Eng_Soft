package Exercicio2;

public class Principal {
    public static void main(String[] args) {
        Carro carro1 = new Carro("ABC-1234", "Polo", 2014, 39870, 4, "Flex");
        Carro carro2 = new Carro("ABD-1235", "Gol", 2024, 45690, 4, "Elétrico");
        Moto moto = new Moto("ABC-123", "CG", 2010, 14320, 150);
        Caminhao caminhao = new Caminhao("ABC-123", "1113", 1983, 80724, 3, 20.5);

        System.out.println("====================================================================================================");
        System.out.print("- Dados:");
        System.out.printf(
                "%n\t- Carro 1: %n\t\t%s %n\t- Carro 2: %n\t\t%s %n\t- Moto: %n\t\t%s %n\t- Caminhão: %n\t\t%s",
                carro1.exibirDados(),
                carro2.exibirDados(),
                moto.exibirDados(),
                caminhao.exibirDados()
        );

        System.out.printf("%n====================================================================================================%n");
        System.out.print("- Financiamento:");
        System.out.printf(
                "%n\t- Carro 1: %n\t\t- Parcelas: 48 %n\t\t- Taxa: 1,2%% a.m. %n\t\t- Valor da parcela: R$%.2f %n\t\t- Custo total: R$%.2f",
                carro1.calcularParcela(48, 1.2),
                carro1.calcularCustoTotal(48, 1.2)
        );
        System.out.printf(
                "%n\t- Carro 2: %n\t\t- Parcelas: 48 %n\t\t- Taxa: 1,2%% a.m. %n\t\t- Valor da parcela: %.2f %n\t\t- Custo total: %.2f",
                carro2.calcularParcela(48, 1.2),
                carro2.calcularCustoTotal(48, 1.2)
        );
        System.out.printf(
                "%n\t- Moto: %n\t\t- Parcelas: 48 %n\t\t- Taxa: 1,2%% a.m. %n\t\t- Valor da parcela: %.2f %n\t\t- Custo total: %.2f",
                moto.calcularParcela(48, 1.2),
                moto.calcularCustoTotal(48, 1.2)
        );

        System.out.printf("%n====================================================================================================%n");
        System.out.print("- Validação de suporte a cargas:");
        System.out.printf(
                "%n\t- Teste 1: %n\t\t- Carga: 10t %n\t\t- Suporta: %s %n\t- Teste 2: %n\t\t- Carga: 30t %n\t\t- Suporta: %s",
                caminhao.suportaCarga(10) ? "Sim" : "Não",
                caminhao.suportaCarga(30) ? "Sim" : "Não"
        );

        System.out.printf("%n====================================================================================================%n");
        System.out.print("Comparação IPVA:");
        System.out.printf(
                "%n\t- Carro 1: %n\t\t- Tipo de combustível: %s %n\t\t- Valor IPVA: %.2f %n\t- Carro 2: %n\t\t- Tipo de combustível: %s %n\t\t- Valor IPVA: %.2f",
                carro1.getTipoCombustivel(),
                carro1.calcularIPVA(),
                carro2.getTipoCombustivel(),
                carro2.calcularIPVA()
        );

        System.out.printf("%n====================================================================================================%n");
    }
}
