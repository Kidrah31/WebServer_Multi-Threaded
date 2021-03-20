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
import java.time.*;
import java.lang.*;

final class HttpRequest implements Runnable
{   long start,end;
    final static String CRLF = "\r\n";
    Socket socket;
    // Constructor
    public HttpRequest(Socket socket) throws Exception 
    {
        start = System.currentTimeMillis();
        this.socket = socket;
    }
    // Implement the run() method of the Runnable interface.
    public void run()
    {
        try 
        {
            processRequest();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    private void processRequest() throws Exception
    {
        
	    InputStream is = socket.getInputStream();	
        // outputstream initialization
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        //InputStream filters.
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        // Reading the HTTP Request from the client
        String requestLine = br.readLine();
        // Display the request line.
        System.out.println("Request: ");
        System.out.println(requestLine);
        System.out.println("-----------------------------------------");
        // Get and display the header lines.
        String headerLine = br.readLine();
        while (headerLine!=null && headerLine.length() != 0) 
        {
            
            System.out.println(headerLine);
            headerLine = br.readLine();
        }

        // Extract the filename from the request line.
        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken();  // skip over the method, which should be "GET"
        String fileName = tokens.nextToken();
        // Prepend a "." so that file request is within the current directory.
        fileName = "." + fileName;

        // Open the requested file.
        FileInputStream fis = null;
        boolean fileExists = true;
        try 
        {
            fis = new FileInputStream(fileName);
        } 
        catch (FileNotFoundException e) 
        {
            fileExists = false;
        }
        System.out.println("-----------------------------------------");

        end = System.currentTimeMillis();
        double Time = (end -start);
        


        // Construct the response message.
        String statusLine = null;
        String contentTypeLine = null;
        String entityBody = null;
        if (fileExists) 
        {
            statusLine = "HTTP/1.1 200 OK ";;
            contentTypeLine = "Content-type: "
                            +contentType( fileName ) + CRLF;
            System.out.println("HTTP/1.1 200 OK ");
            System.out.println("-----------------------------------------");
        } 
        else 
        {
            statusLine = "HTTP/1.1 404 Not Found";
            contentTypeLine = "File doesn't exists";
            entityBody = "<HTML>" + 
                    "<HEAD><TITLE>Not Found</TITLE></HEAD>" +
                    "<BODY>Not Found</BODY></HTML>";
            System.out.println("HTTP/1.1 404 Not Found");
            System.out.println("-----------------------------------------");
        }
        // Send the status line.
        os.writeBytes(statusLine);
        // Send the content type line.
        os.writeBytes(contentTypeLine);
        // Send a blank line to indicate the end of the header lines.
        os.writeBytes(CRLF);


        // Send the entity body.
        if (fileExists) 
        {
            sendBytes(fis, os);
            fis.close();
        } 
        else 
        {
            os.writeBytes(entityBody);
        }
        //Displaying RTT Time
        System.out.println("RTT: "+(Time/1000)+ " Seconds");
        // Close streams and socket.
        os.close();
        br.close();
        socket.close();
    
    }
    private void sendBytes(FileInputStream fis, DataOutputStream os)throws Exception 
    {
        // Construct a 1K buffer to hold bytes on their way to the socket.
        byte[] buffer = new byte[1024];
        int bytes = 0;
        // Copy requested file into the socket's output stream.
        while((bytes = fis.read(buffer)) != -1 ) 
        {
            os.write(buffer, 0, bytes);
        }

    }
    private String contentType(String fileName) 
    {
        if(fileName.endsWith(".htm") || fileName.endsWith(".html")) 
        {
            return "text/html";
        }
        if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) 
        {
            return "image/jpeg";
        }
        if(fileName.endsWith(".gif") || fileName.endsWith(".gif")) 
        {
        return "image/gif";
        }
        return "application/octet-stream";
    
    }

}

