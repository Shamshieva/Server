import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Burulai Urbaeva
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static EchoServer bindToPort(int port){
        return new EchoServer(port);
    }

    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            try (var clientSocket = serverSocket.accept()){
                handle(clientSocket);
            }
        } catch (IOException e){
            System.out.printf("Вероятнее всего порт %s занят.%n", port);
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) throws IOException{
        var input = socket.getInputStream();
        var isr = new InputStreamReader(input, StandardCharsets.UTF_8);
        var scanner = new Scanner(isr);
            Map<String, String> map = new HashMap<>();
            try (scanner){
            var message = "";
            while (!"bye".equalsIgnoreCase(message)){
                message = scanner.nextLine().strip() + ' ';
                String mes = message.substring(0, message.indexOf(' '));
                map.put("date", MethodQuery.date().toString());
                map.put("time", MethodQuery.time().toString());
                map.put("reverse", MethodQuery.reverse(message));
                map.put("upper", MethodQuery.upper(message));

                if (map.get(mes) != null){
                    System.out.printf("Got: %s%n",
                            map.get(mes));
                }
                else System.out.printf("%s---Got: %s%n", socket.getInetAddress(), message);
            }
            System.out.printf("Bye bye!%n");
        } catch (NoSuchElementException e){
            System.out.printf("Client dropped the connection!%n");
        }
    }
}
