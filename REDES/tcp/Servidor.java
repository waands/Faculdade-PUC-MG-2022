import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PORTA = 6789;

    public static void main(String[] args) {
        try {
            ServerSocket servidorSocket = new ServerSocket(PORTA);
            System.out.println("Servidor TCP iniciado na porta " + PORTA);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket);

                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
                out.println("Bem-vindo ao servidor TCP!");

                clienteSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
        }
    }
}
