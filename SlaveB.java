//ServerSocket to receive jobs from master

import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

//Each slave needs one job list for each thread
//Rikki Mann- I added the slaveB class 11/12
public class SlaveB {
    public static void main(String[] args) throws IOException {

        args = new String[]{"3897"};
        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket slaveBSocket = new ServerSocket(portNumber);//Server Socket: to accept call from master
             Socket masterToB = slaveBSocket.accept();
             PrintWriter out =
                     new PrintWriter(masterToB.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(masterToB.getInputStream()));
        ) {
            BooleanWrapper done = new BooleanWrapper(false);
            JobList jobs = new JobList("B");

            Thread fromMaster = new SlavesFromMaster(jobs, in, done);
            fromMaster.start();
            System.out.println("Slave A received job from master");
            while (!done.getBool() || jobs.getJobCount() > 0) {
                if (jobs.getJobCount() > 0) {
                    String[] job = jobs.getFirstJob();
                    if (job[1].equals("B")) {
                        sleep(2000);
                    } else {
                        sleep(10000);
                    }
                    System.out.println(job[0] + " is complete in Slave B");
                    out.println(job[0] + " is complete");
                }
            }
        }
        //rikki mann- I added the catch statement 11/12
        catch (IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }
}
