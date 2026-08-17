package Exercicios;

import java.util.Scanner;

public class Exercicios {
    static Scanner input = new Scanner(System.in);
    public void Inicio() {
        Ex01();
        Ex02();
        Ex03();
        Ex04();
        Ex05();
        Ex06();
        Ex07();
        Ex08();
        Ex09();
        Ex10();
        Ex11();
        Ex12();
        Ex13();
        Ex14();
        Ex15();
    }

    // 1. Faça um programa que leia a idade de uma pessoa e exiba se ela é maior ou menor de idade.
    private void Ex01() {
        System.out.print("Digite sua idade: ");
        int idade = input.nextInt();

        System.out.println(idade <= 18 ? "Você é menor de idade!" : "Você é maior de idade!");
    }

    // 2. Leia dois números e imprima o maior deles.
    private void Ex02() {
        System.out.print("Digite um número: ");
        int numero1 = input.nextInt();

        System.out.print("Digite outro número: ");
        int numero2 = input.nextInt();

        System.out.println("O número " + (numero1 > numero2 ? numero1 : numero2) + " é maior!");
    }

    // 3. Verifique se um número é par ou ímpar.
    private void Ex03() {
        System.out.print("Digite um número: ");
        int num = input.nextInt();

        System.out.printf("O número %d é " + (num % 2 == 0 ? "par." : "ímpar."), num);
    }

    // 4. Leia três números e determine qual é o maior e qual é o menor.
    private void Ex04() {
        System.out.print("Digite um número: ");
        int numero1 = input.nextInt();

        System.out.print("Digite outro número: ");
        int numero2 = input.nextInt();

        System.out.print("Digite mais um número: ");
        int numero3 = input.nextInt();

        int maior = numero1 > numero2 ? numero1 : numero2;
        int menor = numero3 < numero3 ? numero3 : numero2;

        System.out.printf("O número %d é o maior e o número %d é o menor.", maior, menor);
    }

    // 5. Classifique a idade de uma pessoa em: criança (0-12), adolescente (13-17), adulto (18-59) ou idoso (60+).
    private void Ex05() {
        System.out.print("Digite sua idade: ");
        int idade = input.nextInt();

        if (idade <= 12) {
            System.out.println("Você é uma criança.");
        } else if (idade <= 17) {
            System.out.println("Você é um adolescente.");
        } else if (idade <= 59) {
            System.out.println("Você é um adulto.");
        } else {
            System.out.println("Você é um idoso.");
        }
    }

    // 6. Calcule o preço final de um produto com desconto progressivo: • 10% se valor > R$ 100 • 20% se valor > R$ 200 • 30% se valor > R$ 500
    private void Ex06() {
        System.out.print("Digite o valor do produto: ");
        double valor = input.nextDouble();
        double desconto = 0;
        if (valor > 100 && valor <= 200) {
            System.out.println("R$" + (valor - (valor * 0.1)));
        } else if (valor <= 500) {
            System.out.println("R$" + (valor - (valor * 0.2)));
        } else if (valor > 500) {
            System.out.println("R$" + (valor - (valor * 0.3)));
        } else {
            System.out.println("R$" + valor);
        }
    }

    // 7. Leia um número de 1 a 12 e imprima o mês correspondente usando switch.
    private void Ex07() {
        System.out.print("Digite o número do mês: ");
        int mes = input.nextInt();
        String msg;

        switch (mes) {
            case 1:
                msg = "Janeiro";
                break;
            case 2:
                msg = "Fevereiro";
                break;
            case 3:
                msg = "Março";
                break;
            case 4:
                msg = "Abril";
                break;
            case 5:
                msg = "Maio";
                break;
            case 6:
                msg = "Junho";
                break;
            case 7:
                msg = "Julho";
                break;
            case 8:
                msg = "Agosto";
                break;
            case 9:
                msg = "Setembro";
                break;
            case 10:
                msg = "Outubro";
                break;
            case 11:
                msg = "Novembro";
                break;
            case 12:
                msg = "Dezembro";
                break;
            default:
                msg = "Esse mês não existe.";
        }

        System.out.println(msg);
    }

    // 8. Faça uma calculadora simples que receba dois números e um operador (+, -, *, /) e realize a operação usando switch.
    private void Ex08() {
        System.out.print("Digite o primeiro número: ");
        int num1 = input.nextInt();
        System.out.print("Digite o segundo número: ");
        int num2 = input.nextInt();
        System.out.print("Digite a operação: ");
        String operador = input.nextLine();

        switch (operador) {
            case "+":
                System.out.println("Resultado: " + (num1 + num2));
                break;
            case "-":
                System.out.println("Resultado: " + (num1 - num2));
                break;
            case  "*":
                System.out.println("Resultado: " + (num1 * num2));
                break;
            case  "/":
                System.out.println("Resultado: " + (num1 / num2));
                break;
            default:
                System.out.println("Essa operação não existe.");
        }
    }

    // 9. Leia três lados de um triângulo e determine se é equilátero, isósceles ou escaleno. Verifique se os valores formam um triângulo válido.
    private void Ex09() {
        System.out.print("Digite o valor do primeiro lado: ");
        int a = input.nextInt();
        System.out.print("Digite o valor do segundo lado: ");
        int b = input.nextInt();
        System.out.print("Digite o valor do terceiro lado: ");
        int c = input.nextInt();

        String msg = a + b > c && a + c > b && b + c > a ? "É um triângulo válido" : "Não é um triângulo válido.";
        System.out.println(msg);
    }

    // 10. Imprima todos os números pares de 1 a 100.
    private void Ex10() {
        for (int i  = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.print(i + ", ");
            }
        }
    }

    // 11. Imprima a tabuada de um número escolhido pelo usuário (de 1 a 10).
    private void Ex11() {
        System.out.print("Digite um número: ");
        int num = input.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d\n", num, i, num * i);
        }
    }

    // 12. Calcule o fatorial de um número usando for.
    private void Ex12() {
        System.out.print("Digite um número: ");
        int num = input.nextInt();
        int res = 1;

        for (int i = num; i > 1; i--)
            res *= i;

        System.out.println(res);
    }

    // 13. Leia números inteiros até que o usuário digite 0. Ao final, mostre quantos números foram digitados e a soma deles.
    private void Ex13() {
        int num = 0;
        int count = 0;
        int sum = 0;
        do {
            System.out.print("Digite um número: ");
            num = input.nextInt();
            count++;
            sum += num;
        } while (num != 0);

        System.out.printf("Números digitados: %d \nSoma total: %d\n", count, sum);
    }

    // 14. Calcule a média de N números digitados pelo usuário (pergunte quantos números serão digitados).
    private void Ex14() {
        System.out.println("Quantos números serão digitados?");
        int qtd = input.nextInt();
        int sum = 0;

        for (int i = 0; i < qtd; i++) {
            System.out.printf("Digite o %dº número: ", i + 1);
            sum += input.nextInt();
        }

        System.out.println("A média é: " + (sum / qtd));
    }

    // 15. Solicite ao usuário um número entre 1 e 10 com validação usando do-while.
    private void Ex15() {
        boolean valido = true;
        do {
            System.out.print("Digite o número: ");
            int num = input.nextInt();
            if (num > 10 || num < 1) {
                System.out.println("Número digitado inválido! Saindo...");
                valido = false;
            }
        } while (valido);
    }

    // 16. Faça um programa que leia um número N e imprima todos os números primos de 1 até N.
    private void Ex16() {
        System.out.print("Digite um número: ");
        int num = input.nextInt();
        for (int i = 0; i <= num; i++) {

            System.out.print(i + ", ");
        }
    }

// 17. Imprima o seguinte padrão usando for aninhado: • Primeira linha: 1 asterisco • Segunda linha: 2 asteriscos • E assim por diante até 5 asteriscos
}