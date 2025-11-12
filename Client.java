
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {

        args = new String[] {"127.0.0.1", "51222"};
        Random rand = new Random();
        if (args.length != 2) {
            System.err.println(
                    "Usage: java Client local host 51222");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            out.println("request to master");//fill it in

            if (rand.nextInt(2)<1) {//choosing jobs at random to send to master
            //fill in code
            }
            //implement:
            // ThreadfromMaster: gets message when job is complete; and out outs it
            //ThreadtoMaster: ?? main

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}