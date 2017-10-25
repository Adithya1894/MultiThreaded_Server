import com.sun.deploy.util.SessionState;

import java.net.Socket;
import java.io.*;


public class ClientWorker implements Runnable {
    private Socket client;
    public int id;
    private static int logical_clocks[] = {0, 0, 0, 0, 0};

    public ClientWorker(Socket client, int id) {
        this.client = client;
        this.id = id;
    }

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
            while (in.read() >= 0) {
                if (in.read() == -1) {
                    System.out.println("end");
                } else {
                    try {
                        //ANY DATA I GET FROM THIS STREAM IS FROM THIS PARTICULAR CLIENT ONLY!
                        logical_clocks[id] = in.read();
                        //System.out.println("RECEIVED FROM CLIENT " + id + " " + logical_clocks[id]);

                    } catch (IOException e) {
                        System.exit(-1);
                    }

                    dout.write(logical_clocks[id]);

                    dout.flush();
                    //System.out.println("FROM CLIENT " + id + ": " + logical_clocks[id]);


                    for (int i = 0; i < 5; i++) {
                         System.out.println(logical_clocks[i]);
                    }
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