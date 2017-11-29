import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client
{
    private BufferedReader in;
    private PrintWriter out;

    public static void main(String[] args) throws Exception
    {
        Client CLT = new Client();
        CLT.connect();
    }

    public void connect() throws Exception
    {
        Socket SK = new Socket("localhost",1025);
        in = new BufferedReader(new InputStreamReader(SK.getInputStream()));
        out = new PrintWriter(SK.getOutputStream(), true);
        System.out.println(in.readLine());

        Scanner kb = new Scanner(System.in);
        System.out.print("c: ");
        String input = kb.nextLine();
        int count;

        while(!input.equals("EXIT"))
        {
            out.println(input);
            System.out.println(in.readLine());
            count = 0;
            while(!input.equals("."))
            {
                count++;
                System.out.print("c: ");
                input = kb.nextLine();
                out.println(input);
            }

            for(int i = 0; i < count-1; i++)
            {
                System.out.println(in.readLine());
            }

            System.out.print("c: ");
            input = kb.nextLine();
        }
    }
}
