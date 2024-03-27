package br.uam.conexaotcp;
import java.io.*;
import java.net.*;
import java.util.List;

import javax.persistence.*;

public class Servidor {

    private static final int PORTA = 12345;

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");

        EntityManager em = emf.createEntityManager();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado na porta " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();

                InputStream entrada = socket.getInputStream();
                OutputStream saida = socket.getOutputStream();

                int idProduto = entrada.read();

                Query query = em.createQuery("SELECT p FROM Produto p WHERE p.id = :id");
                query.setParameter("id", idProduto);
                List<Produto> produtos = query.getResultList();

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

                entrada.close();
                saida.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
