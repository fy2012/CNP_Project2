import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        Client CLT = new Client();
        CLT.run();
    }

    public void run() throws Exception
    {
        Socket SOCK = new Socket("localhost",1027);
        PrintStream PS = new PrintStream(SOCK.getOutputStream());
        PS.println("Hello to server from client");

        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String MESSAGE = BR.readLine();
        System.out.println(MESSAGE);
    }
}
