//ServerSocket to receive jobs from master
import java.net.*;
import java.io.*;

//Rikki Mann- I added the slaveB class 11/12
public class SlaveB {
    public static void main(String[] args) throws IOException {

        args = new String[]{"3897"};
        if (args.length != 1) {
            System.err.println("Usage: java Master Server 3897");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket slaveASocket = new ServerSocket(portNumber);//Server Socket: to accept call from master
             Socket masterToB = slaveBSocket.accept();
             PrintWriter out =
                     new PrintWriter(masterToB.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(masterToB.getInputStream()));
        )
        {

        }

    }
    //rikki mann- I added the catch statement 11/12
    catch (IOException e) {
        System.out.println(
                "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
        System.out.println(e.getMessage());
    }
}
