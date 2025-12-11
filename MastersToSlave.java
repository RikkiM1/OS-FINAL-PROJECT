import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
                        slavea.println(line);
                    } else {
                        b.addJob(job);
                        slaveb.println(line);
                    }
                } else {
                    if (bTime <= aTime + 8) {
                        b.addJob(job);
                        slaveb.println(line);
                    } else {
                        a.addJob(job);
                        slavea.println(line);
                    }
                }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
