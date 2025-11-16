import java.net.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;

public class Master {
public static void main(String[] args) throws IOException {

    args = new String[] { "51222" };
    //implement:
    //ThreadfromClient://forEach
    //ThreadToClient://forEach

    int portNumber = Integer.parseInt(args[0]);


    try (ServerSocket masterSocket = new ServerSocket(portNumber);//Server Socket: to accept calls from client
         Socket clientSocketA = masterSocket.accept();//to receive jobs from client
         PrintWriter clientOut =
                 new PrintWriter(clientSocketA.getOutputStream(), true);
         BufferedReader clientIn =
                 new BufferedReader(new InputStreamReader(clientSocketA.getInputStream()));
         Socket slaveAsocket = new Socket("127.0.0.1", 3777);//switch to variables(args)
         PrintWriter slaveAOut =
                 new PrintWriter(slaveAsocket.getOutputStream(), true);
         BufferedReader slaveAIn =
                 new BufferedReader(
                         new InputStreamReader(slaveAsocket.getInputStream()));
         //socket for slave b & switch to variables(args)
         Socket slaveBsocket = new Socket("127.0.0.1", 3897);//switch to variables(args)
         PrintWriter slaveBOut =
                 new PrintWriter(slaveBsocket.getOutputStream(), true);
         BufferedReader slaveBIn =
                 new BufferedReader(
                         new InputStreamReader(slaveBsocket.getInputStream()));

    ){

        String response;
        String inputLine1;
//        while ((inputLine1 = clientIn.readLine()) != null) {
//            System.out.println("Received from client: " + inputLine1);
//            response = "*** ECHO SERVER MSG *** " + inputLine1;
//            System.out.println("Sending back: " + response);
//            clientOut.println(response);

        //- Master needs two Joblist objects for each slave:
        JobList jobs1SlaveA = new JobList();
        JobList jobs2SlaveA = new JobList();
        JobList jobs1SlaveB = new JobList();
        JobList jobs2SlaveB = new JobList();
        //add params when we get to that part of coding
//        }
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
