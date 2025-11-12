import java.util.ArrayList;

public class JobList {
    private ArrayList<String[]> jobs;
    private final String idealJobType;

    public JobList(String idealJobType) {
        this.idealJobType = idealJobType;
    }

    public void addJob(String[] job) {
        this.jobs.add(job);
    }
    public void addJob(String jobID, String jobType) {
        this.jobs.add(new String[]{jobID, jobType});
    }
    public String[] getFirstJob() {
        return this.jobs.getFirst();
    }
    public String[] removeFirstJob() {
        return this.jobs.removeFirst();
    }

    public int getJobCount() {
        return this.jobs.size();
    }
    public int timeNeeded() {
        int time = 0;
        for(String[] job : this.jobs) {
            if(job[1].equals(this.idealJobType)) {
                time += 3;
            } else {
                time += 7;
            }
        }
        return time;
    }
}
