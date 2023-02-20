package seat.booking.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void startMessagingClient(String message) {
        try {

            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 5600);

            //
            // Send a message to the client application
            //
            DataOutputStream dos = new DataOutputStream(
                    socket.getOutputStream());

            DataInputStream dis = new DataInputStream(System.in);
            dos.writeUTF(message);
            dos.flush();

            dis.close();
            dos.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}