package server;

/**
 * Created by Oleg on 14.04.2017.
 * oleg.kenunen@gmail.com
 */

import md5.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting server");
        BufferedReader in = null;
        PrintWriter out = null;

        ServerSocket servers = null;
        Socket fromclient = null;

        // create server socket
        try {
            servers = new ServerSocket(24361);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 24361");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient = servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        in = new BufferedReader(new
                InputStreamReader(fromclient.getInputStream()));
        out = new PrintWriter(fromclient.getOutputStream(), true);
        String input;
        //String output;
        int nextStringInput;

        System.out.println("Wait for messages");
        while ((input = in.readLine()) != null) {
            if (input.equalsIgnoreCase("stop")) break;

            try {


                //out.println("receeved: " + input);
                out.println("server MD5: " + Hash.md5(input));
            } catch (Exception e) {
                out.println("incorrect input");
            }
        }


        out.println("Finish");


        out.close();
        in.close();
        fromclient.close();
        servers.close();
    }
}
