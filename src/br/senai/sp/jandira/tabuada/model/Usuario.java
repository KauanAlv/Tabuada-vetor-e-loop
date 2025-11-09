package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Usuario {

    public int multiplicando;
    public int multiplicadorInicial;
    public int multiplicadorFinal;
    public int tamanho;
    public String[] tabuada;

    public void obterInformacoes(){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Qual o valor do multiplicando? ");
        multiplicando = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador inicial? ");
        multiplicadorInicial = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador final? ");
        multiplicadorFinal = leitor.nextInt();

        calcularInformacoes();
    }

    public void calcularInformacoes(){
        int apoio = 0;

        if (multiplicadorFinal < multiplicadorInicial){
           apoio = multiplicadorFinal;
           multiplicadorFinal = multiplicadorInicial;
           multiplicadorInicial = apoio;
        }

        tamanho = multiplicadorFinal - multiplicadorInicial + 1;
        tabuada = new String[tamanho];

        int i = 0;
        while (i < tamanho){
            int resultado = multiplicando * multiplicadorInicial;
            tabuada[i] = multiplicando + " x " + multiplicadorInicial + " = " + resultado;
            multiplicadorInicial++;
            i++;
        }

        exibirTabuada();
    }

    public void exibirTabuada(){
        System.out.println("");
        System.out.println("");
        System.out.println("Resultado da tabuada: ");

        int i = 0;
        while (i < tamanho){
            System.out.println(tabuada[i]);
            i++; // é o mesmo que i = i + 1, sempre que ela for ela mesma + 1, pode usar o "++"
        }
    }
}