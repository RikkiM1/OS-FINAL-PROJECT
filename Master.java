
import java.util.*;
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
         Socket clientSocketA = masterSocket.accept();//to send jobs to salve A
         PrintWriter out1 =
                 new PrintWriter(clientSocketA.getOutputStream(), true);
         BufferedReader in1 =
                 new BufferedReader(new InputStreamReader(clientSocketA.getInputStream()));
         Socket clientSocketB = masterSocket.accept();//to jobs calls to slave B
         PrintWriter out2 =
                 new PrintWriter(clientSocketB.getOutputStream(), true);
         BufferedReader in2 =
                 new BufferedReader(new InputStreamReader(clientSocketB.getInputStream()));) {
        String inputLine1, inputLine2;
        String response;
        while ((inputLine1 = in1.readLine()) != null) {
            System.out.println("Received from client: " + inputLine1);
            response = "*** ECHO SERVER MSG *** " + inputLine1;
            System.out.println("Sending back: " + response);
            out1.println(response);

            inputLine2 = in2.readLine();
            System.out.println("Received from client: " + inputLine2);
            response = "*** ECHO SERVER MSG *** " + inputLine2;
            System.out.println("Sending back: " + response);
            out2.println(response);
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
}