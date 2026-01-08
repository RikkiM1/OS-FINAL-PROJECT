
import java.io.*;
import java.net.*;

public class Client1 {
    public static void main(String[] args) throws IOException {

        args = new String[] {"127.0.0.1", "61222"};

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
            System.out.println("Client1 would like to request jobs.");
            out.println("Client1 would like to request jobs.");
                String ClientID = in.readLine();
                Thread toMaster = new JobInfoFromClient(out, ClientID, stdIn);
                toMaster.start();
                boolean jobsDone = false;
                while (!jobsDone) {
                    String line = in.readLine();
                    System.out.println(line);//for debugging. this is getting null
                    if (line.equals("Done")) jobsDone = true;
                    else System.out.println(line);
                }
                toMaster.join(); // Wait for thread to finish before try-with-resources closes the streams

            //Client1 is supposed to inform user that job is complete. Add code that tells client when its done then
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