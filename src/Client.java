import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        Scanner kb = new Scanner(System.in);
        //System.out.print(">");
        // String command = kb.next();
        Client CLT = new Client();
        CLT.run();
    }

    public void run() throws Exception
    {
        Socket sk = new Socket("localhost",1028);

        PrintStream PS = new PrintStream(sk.getOutputStream());
        String command = "connect";
        PS.println(command);

        InputStreamReader IR = new InputStreamReader(sk.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String MESSAGE = BR.readLine();
        System.out.println(MESSAGE);
    }
}
