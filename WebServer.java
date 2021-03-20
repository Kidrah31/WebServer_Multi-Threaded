/* Name: Hardik Shukla
UTA ID: 1001664934*/

/*
Resources:
Project1_reference_Java.pdf
Project 1- 4344_5344_Spring_2021_.pdf
JavaNetwork Programming tutorials from Canvas
https://stackoverflow.com/questions/31075893/im-getting-favicon-ico-error
https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
https://www.javatpoint.com/socket-programming
http://net-informations.com/java/net/multithreaded.htm
 */

import java.io.* ;
import java.net.* ;
import java.util.* ;



public final class WebServer 
{
    
    public static void main(String[] args) throws IOException
    {
        int ServerPort;
        
        try
        {   
            
		    // Fetching the command line argument
		    
            if(args.length>0)
		    {
		        ServerPort = Integer.parseInt(args[0]);
		    }
		    else
		    {
                ServerPort=8080;
		    }
            ServerSocket serverSocket = new ServerSocket(ServerPort);
            
            while(true)
            {
                System.out.println("Awaiting TCP Client Request: ");
                Socket socket =serverSocket.accept();
                System.out.println("Client Connected");
                // Construct an object to process the HTTP request message.
                HttpRequest request = new HttpRequest(socket);
                // Create a new thread to process the request.
                Thread thread = new Thread(request);
                // Start the thread.
                thread.start();
                
                
            }

        }
        catch(Exception e)
        {
            System.out.println("Server Exception"+e.getMessage());

        }
        
    }
    
}

