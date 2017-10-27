/**
 * Author: Adithya Morampudi
 */



import com.sun.deploy.util.SessionState;

import java.net.Socket;
import java.io.*;

/**
 * ClientWorker class which implements the Runnable class to use the functionality of threads in the server
 * This class is used to get the Multithreading functionality in the server
 */
public class ClientWorker implements Runnable {
    private Socket client;
    public int id;
    private static int logical_clocks[] = {0, 0, 0, 0, 0};

    public ClientWorker(Socket client, int id) {
        this.client = client;
        this.id = id;
    }

    /**
     * Run method makes the Threads start processing
     */
    @Override
    public void run() {
        int line = 0;
        BufferedReader in = null;
        DataOutputStream dout = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            dout = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(logical_clocks[id]);
        }

        try {
            while (in.read()!=-1) {
                if (in.read() == -1) {
                    System.out.println("end");
                } else {
                    try {
                        //ANY DATA I GET FROM THIS STREAM IS FROM THIS PARTICULAR CLIENT ONLY!
                        if(in.read()!=-1)
                            logical_clocks[id] = in.read();
                        else{
                            logical_clocks[id] = logical_clocks[id];
                        }
                        //System.out.println("RECEIVED FROM CLIENT " + id + " " + logical_clocks[id]);

                    } catch (IOException e) {
                        System.exit(-1);
                    }
                    Server server_obj = new Server();
                    int avg = server_obj.berkely_algoorithm(logical_clocks);
                    int off[] = new int[5];
                    /*for(int i = 0 ; i < 5; i++)
                    {
                        off[i] = avg;
                        //System.out.println(off[i]);
                    }*/
                    int offset[] = new int[5];
                    offset[id] = avg-logical_clocks[id];
                    for(int i =0; i < 5; i++)
                    {
                        System.out.println(offset[i]+"\t\t"+ avg +"\t\t"+logical_clocks[i]);
                    }
                    //System.out.println("\n\n");

                    String s = Integer.toString(offset[id]) + "\n";
                    dout.writeBytes(s);

                    dout.flush();
                    //System.out.println("FROM CLIENT " + id + ": " + logical_clocks[id]);


                    //for (int i = 0; i < 5; i++) {
                        //System.out.println(logical_clocks[i]);
                    //}
                    System.out.println("\n\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                client.close();
                //in.close();;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    }
}