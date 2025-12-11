import java.net.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;

public class Master {
public static void main(String[] args) throws IOException {

    args = new String[] { "61222" };
    //implement:
    //ThreadfromClient://forEach
    //ThreadToClient://forEach

    int portNumber = Integer.parseInt(args[0]);


    try (ServerSocket masterSocket = new ServerSocket(portNumber);//Server Socket: to accept calls from client
         Socket slaveAsocket = new Socket("127.0.0.1", 6777);//switch to variables(args)
         PrintWriter slaveAOut =
                 new PrintWriter(slaveAsocket.getOutputStream(), true);
         BufferedReader slaveAIn =
                 new BufferedReader(
                         new InputStreamReader(slaveAsocket.getInputStream()));
         //socket for slave b & switch to variables(args)
         Socket slaveBsocket = new Socket("127.0.0.1", 6897);//switch to variables(args)
         PrintWriter slaveBOut =
                 new PrintWriter(slaveBsocket.getOutputStream(), true);
         BufferedReader slaveBIn =
                 new BufferedReader(
                         new InputStreamReader(slaveBsocket.getInputStream()));

    ){
        Thread[] clientSlaveConnections  = new Thread[2];

        //- Master needs two Joblist objects for each slave:
        JobList jobsSlaveA = new JobList("A");
        JobList jobsSlaveB = new JobList("B");
        //add jobs to joblist? is it only in mastertoslave?

        //this loop gets jobs from the client and then sends them to the slaves
        for (int i = 0; i < 2; i++) {
            Socket clientSocket = masterSocket.accept();//to receive jobs from client
            PrintWriter clientOut =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader clientIn =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientSlaveConnections[i] = new MastersToSlave(clientIn, jobsSlaveA, jobsSlaveB, slaveAOut, slaveBOut);
        }

        slaveAOut.println("Done!");
        slaveBOut.println("Done!");
//
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
