package Aula_26_05_25;

public class Main {
    static void main() {
        System.out.println("1) O que é uma exceção em Java? Por que é importante tratar exceções em um programa?");
        System.out.println("\tÉ um evento, normalmente associado a um erro, que interrompe o fluxo de execução do programa. É importante realizar o tratamento, pois dependendo de qual for, é possível lidar com ele e continuar o fluxo do programa normalmente.\n");

        System.out.println("2) Qual a diferença entre um erro (Error) e uma exceção (Exception) em Java? Dê um exemplo de cada.");
        System.out.println("\tUm erro é um algo grave e irrecuperável da JVM, como o StackOverflow. Já a exceção é algo que o programa(dor) pode prever e tratar, como ArithmeticException\n");

        System.out.println("3) Explique a diferença entre exceções verificadas (checked) e não verificadas (unchecked). Quando o compilador obriga o tratamento? Dê exemplos de cada tipo.");
        System.out.println("\tPara as exceções verificadas o compilador obriga o tratamento, como IOException. Para as verificadas, o tratamento é facultativo, como ArithmeticException\n");

        System.out.println("4) Explique o papel de cada bloco na estrutura try-catch-finally: \n" +
                "\ta) O que acontece se nenhuma exceção ocorrer dentro do try?\n" +
                    "\t\tO bloco catch não será executado, apenas o bloco finally, se houver\n" +
                "\tb) O que acontece se uma exceção ocorrer e houver um catch compatível?\n" +
                    "\t\tO fluxo será direcionado para o bloco catch específico e será executado seu conteúdo. Posteriormente, se houver, também será executado o bloco finally\n" +
                "\tc) O que acontece se uma exceção ocorrer e nenhum catch for compatível? O bloco finally executa?\n" +
                    "\t\tSe nenhum catch for compatível, o bloco finally irá executar e a exceção será propagada para as classes superiores\n");

        System.out.println("5) Para que serve o bloco finally? Dê um exemplo prático de quando o usar. É possível ter um try-finally sem catch?");
        System.out.println("O bloco finally sempre será executado, independentemente do fluxo anterior. É muito utilizado quando é se trabalha com arquivos ou acesso a banco de dados. É totalmente possível ter um bloco try-catch sem um finally, já que ele é opcional");
    }
}
