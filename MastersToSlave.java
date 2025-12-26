import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MastersToSlave extends Thread {
    BufferedReader in;
    JobList a;
    JobList b;
    PrintWriter slavea;
    PrintWriter slaveb;

    public MastersToSlave ( BufferedReader in, JobList a, JobList b, PrintWriter slavea, PrintWriter slaveb) {
        this.in = in;
        this.a = a;
        this.b = b;
        this.slavea = slavea;
        this.slaveb = slaveb;
    }

    public void run() {
        System.out.println("MastersToSlave thread started.");
        try {
             in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean done = false;
        while (!done) {
            try {
                String line = in.readLine();
                if (line.equals("Done!")) {
                    done = true;
                } else {
                String[] job = line.split(",");
                int aTime = a.timeNeeded();
                int bTime = b.timeNeeded();
                if (job[1].equals("A")) {
                    if (aTime <= bTime + 8) {
                        a.addJob(job);
                        System.out.println("Slave A received job from master");
                        slavea.println(line);
                    } else {
                        b.addJob(job);
                        System.out.println("Slave B received job from master");
                        slaveb.println(line);
                    }
                } else {//this sends jobs to non-optimal slave if that makes the most sense
                    if (bTime <= aTime + 8) {
                        b.addJob(job);
                        System.out.println("Slave B received job from master");
                        slaveb.println(line);
                    } else {
                        a.addJob(job);
                        System.out.println("Slave A received job from master");
                        slavea.println(line);
                    }
                }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
    }
}
