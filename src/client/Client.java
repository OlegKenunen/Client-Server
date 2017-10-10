package client;

/**
 * Created by Oleg on 14.04.2017.
 * oleg.kenunen@gmail.com
 */

import md5.Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Client side");

        Socket fromserver = null;

        System.out.println("Connecting to server...");

        fromserver = new Socket("localhost", 24361);

        BufferedReader in = new
                BufferedReader(new
                InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new
                PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new
                BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;

        while ((fuser = inu.readLine()) != null) {
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);//from server
            System.out.println("local MD5: " + Hash.md5(fuser));
            if (fuser.equalsIgnoreCase("close")) break;
            if (fuser.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();
    }
}