import java.io.*;
import java.net.*;

public class Server
{
    public static void main(String[] args) throws Exception
    {
        Server SER = new Server();
        SER.run();
    }

    public void run() throws Exception
    {
        ServerSocket srvSk = new ServerSocket(1028);
        try
        {
            while(true)
            {
                Socket SK = srvSk.accept();
                PrintStream PS = new PrintStream(SK.getOutputStream());

                InputStreamReader IR = new InputStreamReader(SK.getInputStream());
                BufferedReader BR = new BufferedReader(IR);

                String message = BR.readLine();

                if(message.equals("connected"))
                    PS.println("server: got connection from client " + SK.getLocalAddress().getHostAddress());
                else
                    PS.println("other message received");
            }
        }
        finally
        {
            srvSk.close();
        }
    }
}
