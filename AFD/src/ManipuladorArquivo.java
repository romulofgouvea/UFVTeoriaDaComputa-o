
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Romulo
 */
public class ManipuladorArquivo {

    public final static String diretorio = "src\\arquivos\\";

    public static void checarDiretorio() {
//        System.out.print("Diretório \"" + diretorio + "\" ");
        File dir = new File(diretorio);
        if (!dir.exists()) {
            try {
                dir.mkdir();
                System.out.println("criado!");
            } catch (Exception e) {//Caso dê erro o programa será finalizado com esse trecho
                System.err.print("ERRO! Diretório não acessível\nPrograma impossibilitado de criar novo diretório\n. O programa será finalizado.");
                System.exit(0);
            }
        } else {
//            System.out.print("encontrado.\n");
        }
    }

    public static ArrayList<String> leitorAutomato(String nome) {
        FileReader arquivo = null;
        try {
            arquivo = new FileReader(diretorio + nome + ".txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        ArrayList<String> linhaArquivo = new ArrayList<String>();
        Scanner scanner = null;

        checarDiretorio();
        try {
            BufferedReader buffRead = new BufferedReader(arquivo);
            String linha = "";
            String s = "";
            int j = 0;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    String[] a = linha.split("#");
//                    System.out.println("a[0]: " + a[0]);

                    linhaArquivo.add(a[0]);
                } else {
                    break;
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return linhaArquivo;
    }

    public static ArrayList<String> leitorEntrada(String nome) {

        ArrayList<String> linhaArquivo = new ArrayList<String>();
        FileReader arquivo = null;
        try {
            arquivo = new FileReader(diretorio + nome + ".txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        checarDiretorio();
        try {
            BufferedReader buffRead = new BufferedReader(arquivo);
            String linha = "";
            int j = 0;
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    linhaArquivo.add(linha);
                } else {
                    break;
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return linhaArquivo;
    }

    public static void escreSaida(String nome, ArrayList<String> saida) {

        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter(diretorio + "saida" + nome + ".txt");
            checarDiretorio();
            PrintWriter regF = new PrintWriter(arquivo);
            ArrayList<String> dados = new ArrayList<String>();
            dados = saida;
            for (String x : dados) {
                regF.println(x);
            }

            regF.close();
        } catch (IOException | NullPointerException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
