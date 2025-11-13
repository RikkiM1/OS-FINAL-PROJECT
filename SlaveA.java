import java.net.*;
import java.io.*;

public class SlaveA {
    public static void main(String[] args) throws IOException {

        args = new String[]{"3777"};
        if (args.length != 1) {
            System.err.println("Usage: java Master Server 3777");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket slaveASocket = new ServerSocket(portNumber);//Server Socket: to accept call from master
             Socket masterToA = slaveASocket.accept();
             PrintWriter out =
                     new PrintWriter(masterToA.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(masterToA.getInputStream()));
        )
        {

        }

    }
}