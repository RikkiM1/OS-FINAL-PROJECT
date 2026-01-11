import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JobsToSlave extends Thread {
    BufferedReader in;
    JobList a;
    JobList b;
    PrintWriter slavea;
    PrintWriter slaveb;
    Object lockA;
    Object lockB;

    public JobsToSlave(BufferedReader in, JobList a, Object lockA, JobList b, Object lockB, PrintWriter slavea, PrintWriter slaveb) {
        this.in = in;
        this.a = a;
        this.b = b;
        this.slavea = slavea;
        this.slaveb = slaveb;
        this.lockA = lockA;
        this.lockB = lockB;

    }

    public void run() {
        System.out.println("JobsToSlave thread started.");
        try {
             in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean done = false;
        while (!done) {
            try {
                String line = in.readLine();
                if (line.equals("Done")) {
                    done = true;
                } else {
                String[] job = line.split(",");
                int aTime = a.timeNeeded();
                int bTime = b.timeNeeded();
                if (job[1].equals("A")) {//load-balancing logic:
                    if (aTime <= bTime + 8) {
                        synchronized (lockA) {
                            a.addJob(job);
                        }
                        System.out.println("Master received a job from client and assigned it to Slave A.");
                        slavea.println(line);
                    } else {
                        synchronized (lockB) {
                            b.addJob(job);
                        }
                        System.out.println("Master received a job from client and assigned it to Slave B.");
                        slaveb.println(line);
                    }
                } else {//this sends jobs to non-optimal slave if that makes the most sense
                    if (bTime <= aTime + 8) {
                        synchronized (lockB) {
                            b.addJob(job);
                        }
                        System.out.println("Slave B received job from master");
                        slaveb.println(line);
                    } else {
                        synchronized (lockA) {
                            a.addJob(job);
                        }
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
