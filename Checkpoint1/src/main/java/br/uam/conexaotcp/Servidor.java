package br.uam.conexaotcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Servidor {

    private static final Logger logger = LoggerFactory.getLogger(Servidor.class);
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORTA);
            logger.info("Servidor iniciado na porta {}", PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("Nova conexão recebida de {}", socket.getInetAddress());

                new Thread(new ClientHandler(socket, emf)).start();
            }
        } catch (IOException e) {
            logger.error("Erro ao iniciar servidor", e);
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                    logger.info("Servidor encerrado");
                } catch (IOException e) {
                    logger.error("Erro ao fechar servidor", e);
                }
            }
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final EntityManagerFactory emf;

        public ClientHandler(Socket socket, EntityManagerFactory emf) {
            this.socket = socket;
            this.emf = emf;
        }

        @Override
        public void run() {
            try (InputStream entrada = socket.getInputStream();
                 OutputStream saida = socket.getOutputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entrada))) {

                EntityManager em = emf.createEntityManager();

                String idProdutoStr = bufferedReader.readLine();
                int idProduto = Integer.parseInt(idProdutoStr.trim()); 
                logger.debug("ID do produto recebido: {}", idProduto);

                Query query = em.createQuery("SELECT p FROM Produto p WHERE p.id = :id");
                query.setParameter("id", idProduto);
                List<Produto> produtos = query.getResultList();

                logger.debug("Número de produtos encontrados: {}", produtos.size());

                if (!produtos.isEmpty()) {
                    Produto produto = produtos.get(0);
                    String dadosProduto = produto.toString();
                    logger.debug("Produto encontrado: {}", dadosProduto);
                    ConexaoTCP.enviar(socket, dadosProduto);
                } else {
                    String mensagem = "Produto não encontrado";
                    ConexaoTCP.enviar(socket, mensagem);
                }

                em.close();
            } catch (IOException e) {
                logger.error("Erro ao processar conexão", e);
            }
        }
    }
}

