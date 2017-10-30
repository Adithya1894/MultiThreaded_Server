/**
 * Author: Adithya Morampudi
 */



//import com.sun.deploy.util.SessionState;

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
     * This Method receives the clock value from the decrypt method
     *
     */
    public void receive(){



        decrypt();

    }

    /**
     * This Method is used to pass the clock value to the encrypt method
     * Encrypt() Method is used to Encrypt() the clock value and send it to Different clients.
     */
    public void send(){

        encrypt();

    }

    /**
     * encrypt() Method encrypts the Clock value using Affine Chiper.
     * The encryption Technique uses the encryption technique as Y = (aX + b)Mod 26; Y is the encrypted message
     * where 'X' is the value to be encrypted.
     * I am using the values a and b as 3 and 7.
     * on the receiving side the Client decrypts() the message by taking the inverse of the encrypted message
     */
    public void encrypt()
    {


    }

    /**
     * This method receives the encrypted message from the client and takes the inverse of the encrypted message
     * Passes the value back to the receive method
     */
    public void decrypt()
    {


    }






    /**
     * Run method makes the Threads start processing
     */
    @Override
    public void run() {
        int line = 0;
        BufferedReader in = null;
        DataOutputStream dout = null;
        String str;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            dout = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(logical_clocks[id]);
        }

        try {
            while (in.readLine()!=null) {
               /* if (in.read() == -1) {
                    System.out.println("end");
                }*/ //else {
                try {
                    //ANY DATA I GET FROM THIS STREAM IS FROM THIS PARTICULAR CLIENT ONLY!
                    //if(in.read()!=-1)
                    str = in.readLine();

                    int encrypted_data = Integer.parseInt(str);

                    logical_clocks[id] = encrypted_data/3;

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
                int dec_data = offset[id] * 3;
                String s = Integer.toString(dec_data) + "\n";
                dout.writeBytes(s);

                dout.flush();
                //System.out.println("FROM CLIENT " + id + ": " + logical_clocks[id]);


                //for (int i = 0; i < 5; i++) {
                //System.out.println(logical_clocks[i]);
                //}
                System.out.println("\n\n");
                //  }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                //in.close();;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}