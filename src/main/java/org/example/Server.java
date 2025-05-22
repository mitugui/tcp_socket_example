package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Listening on port 8000");
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
                        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
                ) {
                    String clientIp = clientSocket.getInetAddress().toString();
                    Integer clientPort = clientSocket.getPort();

                    System.out.printf("CLIENT [%s:%d] connected!\n", clientIp, clientPort);

                    while (true) {

                        String clientMessage = dataIn.readUTF();
                        System.out.println(clientMessage);

                        String serverMessage = "Received!";
                        dataOut.writeUTF(serverMessage);
                    }
                } catch (EOFException e) {
                    System.out.println("Cliente disconected!");
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e);
        }
    }
}
