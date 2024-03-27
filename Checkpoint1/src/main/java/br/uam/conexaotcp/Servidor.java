package br.uam.conexaotcp;
import java.io.*;
import java.net.*;
import java.util.List;

import javax.persistence.*;

public class Servidor {

    private static final int PORTA = 12345;

    public static void main(String[] args) throws IOException {
        // Criação do EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        
        // Criação do EntityManager
        EntityManager em = emf.createEntityManager();
        
        // Criação do servidor

        ServerSocket serverSocket = null;
        try {
          serverSocket = new ServerSocket(PORTA);
          // ... seu código de servidor aqui
        } catch (IOException e) {
          // Tratar a exceção de I/O
        } finally {
          if (serverSocket != null) {
            try {
              serverSocket.close();
            } catch (IOException e) {
              // Tratar a exceção de fechamento do socket (opcional)
            }
          }
        }

        System.out.println("Servidor iniciado na porta " + PORTA);
        
        while (true) {
            // Aceitação de uma nova conexão
            Socket socket = serverSocket.accept();
            
            // Criação de streams de entrada e saída
            InputStream entrada = socket.getInputStream();
            OutputStream saida = socket.getOutputStream();
            
            // Recepção do ID do produto do cliente
            int idProduto = entrada.read();
            
            // Consulta ao banco de dados para obter o produto
            Query query = em.createQuery("SELECT p FROM Produto p WHERE p.id = :id");
            query.setParameter("id", idProduto);
            List<Produto> produtos = query.getResultList();
            
            // Envio das informações do produto para o cliente
            if (!produtos.isEmpty()) {
                Produto produto = produtos.get(0);
                String dadosProduto = produto.toString();
                saida.write(dadosProduto.getBytes());
                saida.flush();
            } else {
                String mensagem = "Produto não encontrado";
                saida.write(mensagem.getBytes());
                saida.flush();
            }
            
            // Fechamento dos streams e do socket
            entrada.close();
            saida.close();
            socket.close();
        }
    }
}
