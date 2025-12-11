
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {

        args = new String[] {"127.0.0.1", "61222"};

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
            out.println("Client would like to request jobs.");//is this going to master?
            String ClientID = in.readLine();//who is sending in the ID?
            Thread toMaster = new ClientsToMaster(out, ClientID);
            toMaster.start();

            //Client is supposed to inform user that job is complete. Add code that tells client when its done then
            // add print statement: system.out.println("Job is complete.");


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