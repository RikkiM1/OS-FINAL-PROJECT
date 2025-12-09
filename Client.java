
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {

        args = new String[] {"127.0.0.1", "51222"};
        Random rand = new Random();

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        int ctr =0;//Gets incremented when you pass it ToMaster thread in client.
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
            out.println("Client would like to request jobs.");
            String ClientID = in.readLine();
            Thread toMaster = new ClientsToMaster(out, ClientID);
            toMaster.start();


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