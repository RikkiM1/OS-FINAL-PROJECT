import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class JobInfoFromClient extends Thread {
    private PrintWriter out;
    private String clientID;
    private BufferedReader kybd;

    public JobInfoFromClient(PrintWriter out, String clientID, BufferedReader kybd) {
        this.out = out;
        this.clientID = clientID;
        this.kybd = kybd;
    }

    @Override
    public void run() {
        try {
            System.out.println("How many jobs? \nenter 0 to end");
            int jobs = Integer.parseInt(kybd.readLine());
            for (int i = 0; i < jobs; i++) {
                System.out.println("Choose: 'A' or 'B' - ");
                String jobType = kybd.readLine();
                jobType = jobType.toUpperCase();
                String jobID = clientID + i;
                out.println(jobID + "," + jobType);
            }
            System.out.println("finished entering job info");
            out.println("Done");//this tells JobsToSlave it's done sending
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
