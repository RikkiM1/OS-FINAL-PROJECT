//Rikki Mann T00521492, Elisheva Goldfarb T00522464, Rebecca Genack T00524626
import java.util.ArrayList;

public class JobList {
    private ArrayList<String[]> jobs;
    private final String idealJobType;

    public JobList(String idealJobType) {
        this.idealJobType = idealJobType;
        this.jobs = new ArrayList<>();
    }

    public void addJob(String[] job) {
        this.jobs.add(job);
    }
    public void addJob(String jobID, String jobType) {
        this.jobs.add(new String[]{jobID, jobType});
    }
    public String[] getFirstJob() {
        return jobs.getFirst();
    }
    public String[] removeFirstJob() {
        return jobs.removeFirst();
    }

    public int getJobCount() {
        return jobs.size();
    }
    public int timeNeeded() {
        int time = 0;
        for(String[] job : this.jobs) {
            if(job[1].equals(this.idealJobType)) {
                time += 2;
            } else {
                time += 10;
            }
        }
        return time;
    }
}
