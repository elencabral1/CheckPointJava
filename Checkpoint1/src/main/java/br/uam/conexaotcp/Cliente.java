package br.uam.conexaotcp;
import java.io.*;
import java.net.*;

public class Cliente {

    private static final int PORTA = 1521;

    public static void main(String[] args) throws IOException {
        // Conexão com o servidor
        Socket socket = new Socket("localhost", PORTA);
        
        // Criação de streams de entrada e saída
        InputStream entrada = socket.getInputStream();
        OutputStream saida = socket.getOutputStream();
        
        // Leitura do ID do produto do usuário
        System.out.println("Digite o ID do produto: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int idProduto = Integer.parseInt(reader.readLine());
        
        // Envio do ID do produto para o servidor
        saida.write(idProduto);
        saida.flush();
        
        // Recepção das informações do produto do servidor
        byte[] bytes = new byte[1024];
        int bytesLidos = entrada.read(bytes);
        String dadosProduto = new String(bytes, 0, bytesLidos);
        
        // Exibição das informações do produto na tela
        System.out.println("Informações do produto: ");
        System.out.println(dadosProduto);
        
        // Fechamento dos streams e do socket
        entrada.close();
        saida.close();
        socket.close();
    }
}
