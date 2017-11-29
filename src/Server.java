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
                out.println("server: got connection from client " + SK.getLocalAddress().getHostAddress());

                while(true)
                {
                    String input = in.readLine();
                    if (input.equals("UPPERCASE"))
                    {
                        out.println("s: 200 OK");
                        input = in.readLine();
                        while(!input.equals("."))
                        {
                            list.add(input.toUpperCase());
                            input = in.readLine();
                        }
                        Iterator itr=list.iterator();
                        while(itr.hasNext())
                        {
                            out.println("s: " + itr.next());
                        }
                    }

                    if (input.equals("LOWERCASE"))
                    {
                        out.println("200 OK");
                    }

                    if (input.equals("REVERSE"))
                    {
                        out.println("200 OK");
                    }

                    if (input.equals("EXIT"))
                    {
                        out.println("200 OK");
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("");
            }
        }

    }

    /*
    public void run() throws Exception
    {

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
    }*/
}
