import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ClientsToMaster extends Thread {
    private PrintWriter out;
    private String clientID;
    Scanner kybd = new Scanner(System.in);

    public ClientsToMaster(PrintWriter out, String clientID) {
        this.out = out;
        this.clientID = clientID;
    }

    @Override
    public void run() {
        // Random rand = new Random();
        //int jobs = rand.nextInt(100) + 1;

        System.out.println("How many jobs? \nenter 0 to end");
        int jobs = kybd.nextInt();
        kybd.nextLine();
        for (int i = 0; i < jobs; i++) {
            System.out.println("Choose: 'A' or 'B' - ");
            String jobType = kybd.nextLine();
            jobType = jobType.toUpperCase();
            String jobID = clientID + i;
            out.println(jobID + "," + jobType);
        }
        out.println("Done");
    }
}
