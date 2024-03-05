import java.net.*;
import java.util.Random;

public class WeatherServer {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);

            System.out.println("Servidor de Previsão do Tempo iniciado...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String weatherForecast = getWeatherForecast();

                byte[] sendData = weatherForecast.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Previsão do tempo enviada para " + clientAddress + ":" + clientPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherForecast() {
        // Simulação simples de previsão do tempo
        String[] forecasts = {"Ensolarado", "Chuvoso", "Nublado", "Parcialmente nublado"};
        Random rand = new Random();
        int index = rand.nextInt(forecasts.length);
        return forecasts[index];
    }
}
