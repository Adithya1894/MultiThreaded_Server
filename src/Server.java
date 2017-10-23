import java.net.*;
import java.io.*;
import java.util.*;


public class Server extends Thread{
    private static int port = 8423;
    private static int clock = 0;
    private static Socket socket_obj;
    private static ServerSocket ss_obj;
    int [] clock_values = {0,0,0,0,0};



    public void receive(){

    }

    public void send(){



    }



    public void berkely_algoorithm(){
        send();

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
          System.out.println("Server is on: "+port);

          while(true)
          {
              ClientWorker cw_obj;
              try{
                  cw_obj = new ClientWorker(ss_obj.accept(), client);
                  //client++;
                  /**
                   * adding the new clients into the array list
                   */
                  arr_List.add(cw_obj);
                  Thread thread_obj = new Thread(cw_obj);
                  thread_obj.start();
                  System.out.println(arr_List);
              }
              catch(Exception e)
              {
                  e.printStackTrace();
              }

              /*socket_obj = ss_obj.accept();

              Server t_obj = new Server();
              t_obj.start();
              System.out.println("client connected id:" +t_obj.getState());*/
              client++;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      finally {
          try{
              socket_obj.close();
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
      }


  }










}
