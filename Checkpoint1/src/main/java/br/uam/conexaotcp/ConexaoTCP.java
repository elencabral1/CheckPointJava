package br.uam.conexaotcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConexaoTCP {

    public static String receber(Socket socket) throws IOException {
        try (InputStream in = socket.getInputStream()) {
            byte[] buffer = new byte[1024]; // Aumentando o tamanho do buffer para evitar truncamento de dados
            int bytesRead = in.read(buffer);
            if (bytesRead > 0) {
                return new String(buffer, 0, bytesRead); // Lendo apenas os bytes lidos
            } else {
                return ""; // Retornando uma string vazia se nenhum byte for lido
            }
        }
    }

    public static void enviar(Socket socket, String textoRequisicao) throws IOException {
        try (OutputStream out = socket.getOutputStream()) {
            out.write(textoRequisicao.getBytes());
            out.flush();
        }
    }
}
