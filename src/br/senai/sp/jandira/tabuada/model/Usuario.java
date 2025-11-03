package br.senai.sp.jandira.tabuada.model;

import java.util.Scanner;

public class Usuario {

    public int multiplicando;
    public int multiplicadorInicial;
    public int multiplicadorFinal;
    public double[] tabuada;

    public void obterInformacoes(){
        Scanner leitor = new Scanner(System.in);
        System.out.print("Qual o valor do multiplicando? ");
        multiplicando = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador inicial? ");
        multiplicadorInicial = leitor.nextInt();

        System.out.print("Qual o valor do multiplicador final? ");
        multiplicadorFinal = leitor.nextInt();

    }

}
