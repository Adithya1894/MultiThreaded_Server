import java.net.Socket;
import java.io.*;


public class ClientWorker implements Runnable {
    private Socket client;
    public int id;
    private static int logical_clocks[] = {0,0,0,0,0};

    public ClientWorker(Socket client, int id) {
        this.client = client;
        this.id = id;
    }

    @Override
    public void run(){
        int line = 0;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(logical_clocks[id]);
        }

        try {
            while(in.read() != -1){
                try{
    //ANY DATA I GET FROM THIS STREAM IS FROM THIS PARTICULAR CLIENT ONLY!
                    logical_clocks[id] = in.read();
                    //System.out.println("RECEIVED FROM CLIENT "+ id +" " + line);
                }catch (IOException e) {
                    System.exit(-1);
                }
                for(int i =0; i < 5; i++ )
                {
                    System.out.println(logical_clocks[i]);
                }
                System.out.println("\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
