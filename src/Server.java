/**
 * author: Adithya Morampudi
 */

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Server Class which takes care of the requests from multiple clients and Synchronizes
 * the logical clock values using the ClientWorker class
 */
public class Server extends Thread{
    private static int port = 8423;
    private static int clock = 0;
    private static Socket socket_obj;
    private static ServerSocket ss_obj;
    int [] clock_values = {0,0,0,0,0};
    static int size = 3;

    public static int getPort() {
        return port;
    }

    public static int getClock() {
        return clock;
    }

    public static Socket getSocket_obj() {
        return socket_obj;
    }

    public static ServerSocket getSs_obj() {
        return ss_obj;
    }

    public int[] getClock_values() {
        return clock_values;
    }

    public static int getSize() {
        return size;
    }

    public static void setPort(int port) {
        Server.port = port;
    }

    public static void setClock(int clock) {
        Server.clock = clock;
    }

    public static void setSocket_obj(Socket socket_obj) {
        Server.socket_obj = socket_obj;
    }

    public static void setSs_obj(ServerSocket ss_obj) {
        Server.ss_obj = ss_obj;
    }

    public void setClock_values(int[] clock_values) {
        this.clock_values = clock_values;
    }

    public static void setSize(int size) {
        Server.size = size;
    }

    public int  berkely_algoorithm(int[] a){
        int sum = 0;
        for(int i = 0; i < 5; i++)
        {
            sum = sum + a[i];

        }
        int avg =  sum/3;
        return avg;
        //send();

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

