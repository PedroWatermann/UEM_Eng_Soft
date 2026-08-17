package Aula_26_05_18;

public class Main {
    static void main() {
        //1)
        System.out.println("1) Qual a diferença entre um vetor e uma variável simples? Quando é vantajoso usar vetores?");
        System.out.println("Um vetor armazena uma lista de valores, enquanto uma variável armazena apenas um valor. É vantajoso utilizar vetores quando é necessário armazenar uma lista de valores.\n\n");

        //2)
        System.out.println("2) Dado o vetor int[] v = {10, 20, 30, 40, 50}, responda:");
        System.out.println("\ta) Qual é o valor de v[2]?");
        System.out.println("\t\t30");
        System.out.println("\tb) Qual é o valor de v.length?");
        System.out.println("\t\t5");
        System.out.println("\tc) O que acontece ao acessar v[5]? Qual exceção é lançada?");
        System.out.println("\t\tSerá lançada uma exceção de ArrayIndexOutOfBoundException, onde indica que foi tentado acessar uma posição fora dos limites do array.\n\n");

        //3)
        System.out.println("3) Qual a diferença entre o for tradicional e o for-each ao percorrer um vetor? Quando usar cada um? Qual deles permite modificar os elementos do vetor?");
        System.out.println("O for-each acessa o valor do elemento e não permite sua edição, enquanto o for iterativo nos fornece o index para acessar uma posição específica no vetor. É interessante utilizar o for-each para a impressão de valores e o for iterativo para o resto.\n\n");

        //4)
        System.out.println("4) Explique como funciona a declaração de uma matriz em Java. Qual a diferença entre c.length e c[0].length?");
        System.out.println("Uma matriz é, na verdade, uma lista de listas, sendo possível estender para mais dimensões, ou seja, listas de listas com listas de listas, etc. O primeiro é a quantidade de linhas na matriz e o segundo é a quantidade de colunas na linha 0 da matriz.\n\n");

        //5
        System.out.println("5) O que acontece quando criamos um vetor de objetos com new Aluno[10]? Os objetos Aluno já estão criados? Qual é o valor de cada posição antes de instanciar os objetos?");
        System.out.println("Criamos uma lista de 10 posições, onde cada posição possui um objeto Aluno com valor null, antes das instâncias dos objetos.\n\n");

        //6
        System.out.println("6) Para que a multiplicação de duas matrizes A e B seja possível, qual condição deve ser satisfeita em relação às dimensões das matrizes? Qual será a dimensão da matriz resultante?");
        System.out.println("Para ocorrer a multiplicação das duas matrizes, o número de linhas da matriz A deve ser igual ao número de colunas da matriz B. A matriz resultante terá o tamanho da quantidade de colunas.\n\n");
    }
}
