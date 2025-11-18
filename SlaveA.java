import java.net.*;
import java.io.*;

import static java.lang.Thread.sleep;

//Each slave needs one jobList for each thread
public class SlaveA {
    public static void main(String[] args) throws IOException {

        args = new String[]{"3777"};

        int portNumber = Integer.parseInt(args[0]);

        try (ServerSocket slaveASocket = new ServerSocket(portNumber);//Server Socket: to accept call from master
             Socket masterToA = slaveASocket.accept();
             PrintWriter out =
                     new PrintWriter(masterToA.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(masterToA.getInputStream()));
        ) {
            BooleanWrapper done  = new BooleanWrapper(false);
            JobList jobs = new JobList("A");
            Thread fromMaster = new SlavesFromMaster(jobs,in,done);
            fromMaster.start();
            while (!done.getBool() || jobs.getJobCount() > 0) {
                if(jobs.getJobCount() > 0) {
                    String[] job = jobs.getFirstJob();
                    if(job[1].equals("A")) {
                        sleep(2000);
                    } else {
                        sleep(10000);
                    }
                    out.println(job[0] + " is complete");
                }
            }
        }

        //rikki mann- I added the catch statement 11/12
        catch (IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }
}