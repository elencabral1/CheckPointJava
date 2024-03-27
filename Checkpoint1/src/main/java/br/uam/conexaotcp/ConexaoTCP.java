/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.uam.conexaotcp;

/**
 *
 * @author MSWagner
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConexaoTCP {

    public static String receber(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        byte infoBytes[] = new byte[100];
        int bytesLidos = in.read(infoBytes);

        if (bytesLidos > 0) {
            return new String(infoBytes);
        } else {
            return "";
        }
    }

    public static void enviar(Socket socket, String textoRequisicao) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(textoRequisicao.getBytes());
    }
}
