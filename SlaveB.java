//Rikki Mann T00521492, Elisheva Goldfarb T00522464, Rebecca Genack T00524626
//ServerSocket to receive jobs from master

import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

//Each slave needs one job list for each thread
//Rikki Mann- I added the slaveB class 11/12
public class SlaveB {
    public static void main(String[] args) throws IOException {

        args = new String[]{"6897"};
        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket slaveBSocket = new ServerSocket(portNumber); // Server Socket: to accept call from master
             Socket masterToB = slaveBSocket.accept();
             PrintWriter out = new PrintWriter(masterToB.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(masterToB.getInputStream()));) {
            System.out.println("Slave B is ready to receive jobs from Master.");
            BooleanWrapper done = new BooleanWrapper(false);
            JobList jobs = new JobList("B");
            Object jobListLock = new Object();
            Thread fromMaster = new SlavesFromMaster(jobs, in, done, jobListLock);
            fromMaster.start();
            while (!done.getBool() || jobs.getJobCount() > 0) {
                if (jobs.getJobCount() > 0) {
                    /* This line doesn't need to be synchronized because the first job
                    isn't affected by adding a job later in the list */
                    String[] job = jobs.getFirstJob();
                    if (job[1].equals("B")) {
                        System.out.println("Sleeping for 2 seconds for type B job.");
                        sleep(2000);
                    } else {
                        System.out.println("Sleeping for 10 seconds for type A job.");
                        sleep(10000);
                    }
                    synchronized (jobListLock) {
                        jobs.removeFirstJob();
                    }
                    System.out.println(job[0] + " is complete in Slave B");
                    out.println(job[0] + " is complete by Slave B");
                } else {
                    // Small delay to prevent busy-waiting when no jobs available
                    sleep(100);
                }
            }
            System.out.println("Slave B: all jobs complete.");
            out.println("Done!");
            fromMaster.join();

        }
        // rikki mann- I added the catch statement 11/12
        catch (IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }
}
