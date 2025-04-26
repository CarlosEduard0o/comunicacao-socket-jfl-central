package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JFLCentralListener {
    private ServerSocket serverSocket;

    public JFLCentralListener(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Aguardando conexão na porta " + port + "...");
    }

    public void listen() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Conexão recebida da central: " + socket.getInetAddress().getHostAddress());

                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    System.out.print("Dados recebidos: ");
                    for (int i = 0; i < bytesRead; i++) {
                        System.out.printf("%02X ", buffer[i]);
                    }
                    System.out.println();
                }

                socket.close();
                System.out.println("Conexão encerrada.");
            } catch (IOException e) {
                System.out.println("Erro na conexão: " + e.getMessage());
            }
        }
    }
}