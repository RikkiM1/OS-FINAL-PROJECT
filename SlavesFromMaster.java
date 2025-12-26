import java.io.BufferedReader;
import java.io.IOException;

public class SlavesFromMaster extends Thread {

    private JobList jobs;
    private BufferedReader br;
    private BooleanWrapper done;

    public SlavesFromMaster(JobList jobs, BufferedReader br,  BooleanWrapper done) {
        this.jobs = jobs;
        this.br = br;
        this.done = done;
    }

    @Override
    public void run() {
        System.out.println("SlavesFromMaster thread started.");
        String[] job;
        while(!done.getBool()) {
            try {
                String line = br.readLine();
                if(line.equals("Done")) {
                    done.setBool(true);
                } else {
                    job = line.split(",");
                    jobs.addJob(job);
                    System.out.println("Job added to slave: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
