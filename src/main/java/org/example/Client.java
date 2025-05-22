package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket serverSocket = new Socket();
                Scanner scanner = new Scanner(System.in)
        ) {
            serverSocket.connect(new InetSocketAddress("127.0.0.1", 8000), 1000);
            System.out.println("Connection established.");

            try (
                    DataInputStream dataIn = new DataInputStream(serverSocket.getInputStream());
                    DataOutputStream dataOut = new DataOutputStream(serverSocket.getOutputStream())
            ) {
                while (true) {
                    System.out.println("Type a message: ");
                    String message = scanner.nextLine();
                    dataOut.writeUTF(message);

                    System.out.println(dataIn.readUTF());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
