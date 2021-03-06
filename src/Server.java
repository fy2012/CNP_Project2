import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Server
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket srvSk = new ServerSocket(1025);
        try
        {
            while(true)
            {
                new trd(srvSk.accept()).start();
            }
        }
        finally
        {
            srvSk.close();
        }
    }

    private static class trd extends Thread
    {
        private Socket SK;

        public trd(Socket SK)
        {
            this.SK = SK;
        }

        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(SK.getInputStream()));
                PrintWriter out = new PrintWriter(SK.getOutputStream(), true);
                ArrayList<String> list=new ArrayList<String>();
                String IPAddress = SK.getLocalAddress().getHostAddress();
                out.println("server: got connection from client " + IPAddress);
                out.println("Server is ready...");

                while(true)
                {
                    String input = in.readLine();
                    System.out.println(IPAddress + " sends " + input);
                    if (input.equals("UPPERCASE") || input.equals("LOWERCASE") || input.equals("REVERSE"))
                    {
                        String function = input;
                        out.println("s: 200 OK");
                        input = in.readLine();
                        System.out.println(IPAddress + " sends " + input);
                        while(!input.equals("."))
                        {
                            if(function.equals("UPPERCASE"))
                                list.add(input.toUpperCase());
                            if(function.equals("LOWERCASE"))
                                list.add(input.toLowerCase());
                            if(function.equals("REVERSE"))
                                list.add(new StringBuilder(input).reverse().toString());
                            input = in.readLine();
                            System.out.println(IPAddress + " sends " + input);
                        }
                        Iterator itr = list.iterator();
                        while(itr.hasNext())
                        {
                            out.println("s: " + itr.next());
                        }
                        list.clear();
                    }

                    else if (input.equals("EXIT"))
                    {
                        out.println("200 OK");
                        SK.close();
                    }
                    else
                    {
                        out.println("400: Not a valid command!");
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("");
            }
        }
    }
}