import java.io.PrintWriter;
import java.util.Random;


public class ClientsToMaster extends Thread {
    private PrintWriter out;
    private String clientID;

    public ClientsToMaster(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int jobs = rand.nextInt(100) + 1;
        for (int i = 0; i < jobs; i++) {
            String jobType = rand.nextInt(2) <1 ? "A":"B" ;
            String jobID = clientID + i;
            out.println(jobID + "," + jobType);
        }
    }
}
