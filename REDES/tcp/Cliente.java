import java.io.*;
import java.net.*;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 6789;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORTA);
            System.out.println("Conectado ao servidor " + HOST + ":" + PORTA);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensagem = in.readLine();
            System.out.println("Mensagem do servidor: " + mensagem);

            socket.close();
        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }
}
