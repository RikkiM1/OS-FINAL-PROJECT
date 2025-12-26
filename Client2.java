import java.io.*;
import java.net.*;
//add print statements(can be same as client 1)
public class Client2 {
    public static void main(String[] args) throws IOException {

        args = new String[] {"127.0.0.1", "61222"};//only have this port number here


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
            System.out.println("Client would like to request jobs.");
            out.println("Client would like to request jobs.");//is this going to master? Also isn't the client the one
                // that sends jobs?
            // do we need to put this in a while loop? like while(in.readline!=null)?
                String ClientID = in.readLine();//who is sending in the ID? I switched it from in to stdIn
            // and now no error but not sure if supposed to do that
                Thread toMaster = new ClientsToMaster(out, ClientID, stdIn);
                toMaster.start();
                toMaster.join(); // Wait for thread to finish before try-with-resources closes the streams



            //Client is supposed to inform user that job is complete. Add code that tells client when its done then
            // add print statement: system.out.println("Job is complete.");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
            e.printStackTrace();
        }
    }
}
