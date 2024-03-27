package br.uam.conexaotcp;
import java.io.*;
import java.net.*;

public class Cliente {

    private static final int PORTA = 1521;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", PORTA);

        InputStream entrada = socket.getInputStream();
        OutputStream saida = socket.getOutputStream();

        System.out.println("Digite o ID do produto: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int idProduto = Integer.parseInt(reader.readLine());

        saida.write(String.valueOf(idProduto).getBytes());
        saida.flush();

        byte[] bytes = new byte[1024];
        int bytesLidos = entrada.read(bytes);
        String dadosProduto = new String(bytes, 0, bytesLidos);

        System.out.println("Informações do produto: ");
        System.out.println(dadosProduto);

        entrada.close();
        saida.close();
        socket.close();
    }
}
