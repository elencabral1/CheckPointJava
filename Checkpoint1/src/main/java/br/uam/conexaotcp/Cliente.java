package br.uam.conexaotcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    private static final int PORTA = 12346;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORTA);
             InputStream entrada = socket.getInputStream();
             OutputStream saida = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             InputStreamReader inputStreamReader = new InputStreamReader(entrada);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            System.out.println("Digite o ID do produto: ");
            int idProduto = Integer.parseInt(reader.readLine());

            saida.write(String.valueOf(idProduto).getBytes());
            saida.flush();

            String dadosProduto = bufferedReader.readLine();

            System.out.println("Informações do produto: ");
            System.out.println(dadosProduto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
