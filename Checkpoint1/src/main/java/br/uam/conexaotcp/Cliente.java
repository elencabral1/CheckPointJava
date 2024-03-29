package br.uam.conexaotcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORTA);
             OutputStream saida = socket.getOutputStream();
             InputStream entrada = socket.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(entrada);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            System.out.println("Digite o ID do produto: ");
            String idProdutoStr = new BufferedReader(new InputStreamReader(System.in)).readLine();
            saida.write((idProdutoStr + "\n").getBytes());
            saida.flush();

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
