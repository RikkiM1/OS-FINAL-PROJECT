import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class ClientsToMaster extends Thread {
    private PrintWriter out;
    private String clientID;
    private BufferedReader kybd;

    public ClientsToMaster(PrintWriter out, String clientID, BufferedReader kybd) {
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
            out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
