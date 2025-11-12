public class ArrayListA {
    String jobID;
    String jobType;
    String[] jobs;
    //add third field client name?
    //make this a shared object

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public void setJobs(String jobID,  String jobType) {
        this.jobs = new String[]{jobID, jobType};//is this the right way to do it?
    }

    public String getJobID() {
        return jobID;
    }

    public String getJobType() {
        return jobType;
    }
    public String[] getJobs() {
        return jobs;
    }



}