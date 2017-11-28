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
        ServerSocket SRVSOCK = new ServerSocket(1027);
        Socket SOCK = SRVSOCK.accept();
        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String MESSAGE = BR.readLine();
        System.out.println(MESSAGE);

        if (MESSAGE !=null)
        {
            PrintStream PS = new PrintStream(SOCK.getOutputStream());
            PS.println("Message recieved");
        }
    }
}
