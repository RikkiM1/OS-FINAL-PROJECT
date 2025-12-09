import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MasterFromSlave {
    PrintWriter client1;
    PrintWriter client2;
    BufferedReader in;
    JobList jobList;

    public MasterFromSlave(PrintWriter client1, PrintWriter client2, BufferedReader in, JobList jobList) {
        this.client1 = client1;
        this.client2 = client2;
        this.in = in;
        this.jobList = jobList;

        try {
            boolean done = false;
            String line = in.readLine();
            while (!done) {
                if (line.equals("Done!")) {
                    done = true;
                } else {

                    if (line.matches(".* is complete")) {
                        String[] temp = line.split(" ");
                        if (jobList.getFirstJob()[0].equals(temp[1])) {
                            jobList.removeFirstJob();
                        }
                        System.out.println("Slave reported that job " + line);
                        switch(line.charAt(0)) {
                            case 'A':
                                    client1.println(line);
                            break;
                            case 'B':
                                    client2.println(line);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
