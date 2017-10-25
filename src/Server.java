import java.net.*;
import java.io.*;
import java.util.*;


public class Server extends Thread{
    private static int port = 8423;
    private static int clock = 0;
    private static Socket socket_obj;
    private static ServerSocket ss_obj;
    int [] clock_values = {0,0,0,0,0};
    static int size = 2;



    public void receive(){

    }

    public void send(){



    }



    public int  berkely_algoorithm(int[] a){
        int sum = 0;
        for(int i = 0; i < 5; i++)
        {
            sum = sum + a[i];

        }
        int avg =  sum/5;
        return avg;
        //send();

    }

    public void encrypt()
    {

    }

    public void decrypt()
    {

    }

    public static void main(String args[])
    {

        ArrayList<ClientWorker> arr_List = new ArrayList<ClientWorker>();
        int client = 1;
        try {
            ss_obj = new ServerSocket(port);
            System.out.println("Server is on: " + port);


            while (arr_List.size() < size) {

                //try{
                // cw_obj = null;
                //Thread thread_obj = new Thread(cw_obj);

                ClientWorker cw_obj = new ClientWorker(ss_obj.accept(), client);
                //ClientWorker  cw1_obj = new ClientWorker(ss_obj.accept(), client+1);
                //ClientWorker  cw_obj = new ClientWorker(ss_obj.accept(), client);
                client++;
                Thread thread_obj = new Thread(cw_obj);
                //Thread thread_obj1= new Thread(cw1_obj);
                /**
                 * adding the new clients into the array list
                 */
                arr_List.add(cw_obj);
                //arr_List.add(cw1_obj);


                System.out.println(arr_List);
                //while(arr_List.size() < size )
                //{

                thread_obj.start();
                //thread_obj1.start();

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

              /*socket_obj = ss_obj.accept();
              Server t_obj = new Server();
              t_obj.start();
              System.out.println("client connected id:" +t_obj.getState());*/
        //client++;
        //}

    }



}

