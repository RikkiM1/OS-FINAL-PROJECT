import java.net.*;
import java.io.*;

public class Master {
public static void main(String[] args) throws IOException {

    args = new String[] { "51222" };
    //implement:
    //ThreadfromClient://forEach
    //ThreadToClient://forEach
    if (args.length != 1) {
        System.err.println("Usage: java Master Server 51222");
        System.exit(1);
    }

    int portNumber = Integer.parseInt(args[0]);

    try (ServerSocket masterSocket = new ServerSocket(portNumber);//Server Socket: to accept calls from client
         Socket clientSocketA = masterSocket.accept();//to receive jobs from client
         PrintWriter clientOut =
                 new PrintWriter(clientSocketA.getOutputStream(), true);
         BufferedReader clientIn =
                 new BufferedReader(new InputStreamReader(clientSocketA.getInputStream()));
         Socket slaveA = new Socket("127.0.0.1", 3777);//switch to variables(args)
         PrintWriter out =
                 new PrintWriter(slaveA.getOutputStream(), true);
         BufferedReader in =
                 new BufferedReader(
                         new InputStreamReader(slaveA.getInputStream()));
         //soket for slave b & switch to variables(args)
    ){
        String response;
        String inputLine1;
        while ((inputLine1 = clientIn.readLine()) != null) {
            System.out.println("Received from client: " + inputLine1);
            response = "*** ECHO SERVER MSG *** " + inputLine1;
            System.out.println("Sending back: " + response);
            clientOut.println(response);


        }
    } catch (IOException e) {
        System.out.println(
                "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
        System.out.println(e.getMessage());
    }
}
/**implement: Main will deal with getting more clients ??
 Thread from slave 2 times: (to Client?)
 ThreadfromSlaveA:pass the inA object and outclient object when receive done message
 from slave send to client
 ThreadfromSlaveB: pass the inB object and outclient object when receive done
 message from slave send to client**/
}
