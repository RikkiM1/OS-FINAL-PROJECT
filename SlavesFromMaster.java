import java.io.BufferedReader;
import java.io.IOException;

public class SlavesFromMaster extends Thread {

    private JobList jobs;
    private BufferedReader br;
    private BooleanWrapper done;
    private Object jobListLock;

    public SlavesFromMaster(JobList jobs, BufferedReader br,  BooleanWrapper done,  Object jobListLock) {
        this.jobs = jobs;
        this.br = br;
        this.done = done;
        this.jobListLock = jobListLock;
    }

    @Override
    public void run() {
        System.out.println("SlavesFromMaster thread started.");
        String[] job;
        while(!done.getBool()) {
            try {
                String line = br.readLine();
                if(line.equals("Done!")) {
                    done.setBool(true);
                    System.out.println("All jobs recieved");
                } else {
                    job = line.split(",");
                    synchronized (jobListLock) {
                        jobs.addJob(job);
                    }
                    System.out.println("Job received from master. Job added to slave: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
