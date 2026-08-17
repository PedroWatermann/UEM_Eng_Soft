import java.util.Scanner;

public class Main {
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    private static final double DOLAR = 5.24;

    public static void main(String[] args) {
        double real;

        Scanner input = new Scanner(System.in);

        System.out.println(GREEN + "Digite o valor em R$: ");
        real = input.nextDouble();

        double resultado = real / DOLAR;

        System.out.printf("%sA conversão resultou em US$%.2f", YELLOW, resultado);
    }
}
