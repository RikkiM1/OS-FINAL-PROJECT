import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ClientsToMaster extends Thread {
    private PrintWriter out;
    private String clientID;
    private int jobs;
    Scanner kybd = new Scanner(System.in);

    public ClientsToMaster(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        // Random rand = new Random();
        //int jobs = rand.nextInt(100) + 1;

        System.out.println("How many jobs? \nenter 0 to end");
        jobs = kybd.nextLine();
        kybd.nextLine();
        for (int i = 0; i < jobs; i++) {
            //String jobType = rand.nextInt(2) <1 ? "A":"B" ;
            //are we supposed to ask them to pick A or B?
            System.out.println("chose: 'A' or 'B'");
            String jobType = kybd.nextLine();
            jobType = jobType.toUpperCase();
            String jobID = clientID + i;
            out.println(jobID + "," + jobType);
        }
        out.println("Done");
    }
}
