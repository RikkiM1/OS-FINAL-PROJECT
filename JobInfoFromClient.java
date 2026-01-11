import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class JobInfoFromClient extends Thread {
    private PrintWriter out;
    private String clientID;
    private BufferedReader kybd;
    private int numJobs;

    public JobInfoFromClient(PrintWriter out, String clientID, BufferedReader kybd, int numJobs) {
        this.out = out;
        this.clientID = clientID;
        this.kybd = kybd;
        this.numJobs = numJobs;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numJobs; i++) {//for each job- ask which type it is
                System.out.println("Choose: 'A' or 'B' - ");
                char jobType = kybd.readLine().toUpperCase().charAt(0);
                String jobID = clientID + i;
                out.println(jobID + "," + jobType);
                System.out.println("Sent job " + jobID + " to master.");
            }

            out.println("Done");//this tells JobsToSlave it's done sending
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
