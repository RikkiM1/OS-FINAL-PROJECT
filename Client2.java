import java.io.*;
import java.net.*;
//add print statements(can be same as client 1)
public class Client2 {
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
            System.out.println("Client2 would like to request jobs.");
            out.println("Client2 would like to request jobs.");
                            String ClientID = in.readLine();
                System.out.println("How many jobs? \nenter 0 to end");
                int jobs = Integer.parseInt(stdIn.readLine());
                Thread toMaster = new JobInfoFromClient(out, ClientID, stdIn, jobs);
                toMaster.start();
                for(int i = 0; i < jobs; i++) {
                    String line = in.readLine();
                    System.out.println(line);
                }
                toMaster.join(); // Wait for thread to finish before try-with-resources closes the streams
                System.out.println("All jobs are complete.");


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
