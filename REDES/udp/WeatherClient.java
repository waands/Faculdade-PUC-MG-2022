import java.net.*;

public class WeatherClient {
    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            byte[] sendData = "Qual é a previsão do tempo?".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String weatherForecast = new String(receivePacket.getData()).trim();
            System.out.println("Previsão do tempo: " + weatherForecast);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
