import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MasterFromSlave extends Thread{
    ArrayList<PrintWriter> clients = new ArrayList<PrintWriter>();
    BufferedReader in;
    JobList jobList;

    public MasterFromSlave(BufferedReader in, JobList jobList) {
        this.in = in;
        this.jobList = jobList;
    }

    public void addClient(PrintWriter client) {
        clients.add(client);
    }

    public void run() {

        try {
            boolean done = false;
            String line = in.readLine();
            while (!done) {
                if (line.equals("Done!")) {
                    System.out.println("Slave reported that all it's jobs are done.");
                    done = true;
                } else {
                    if (line.matches(".* is complete .*")) {
                        String[] temp = line.split(" ");
                        if (jobList.getFirstJob()[0].equals(temp[1])) {
                            jobList.removeFirstJob();
                        }
                        System.out.println("Slave reported that job " + line);
                        clients.get(line.charAt(0) - 'A').println(line);
                    }
                    line = in.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

