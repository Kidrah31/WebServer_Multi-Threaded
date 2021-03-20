# About
A multi-threaded Web server which interacts with any standard Web Clients The Web server and Web client communicate using a text-based protocol called HTTP (Hypertext Transfer Protocol).Upon receiving a HTTP request, the server sets up a TCP connection to the requesting client and serves the request in a separate thread using a new port. After sending the response back to the client, it closes the connection.


### Execution Instructions 

Install JDK 1.16
Open Command Prompt
Navigate to the Directory that contains the files
Compile the java files using the following commands on the Command Prompt:
	 javac WebServer.java javac HttpRequestGet.java 
Run the server code in the following format with port number  given as an argument:
	 (e.g. java WebServer 9876).
The server starts waiting for a client.
If port number is not specified as an argument, the server takes the default port 8080.
Open a new tab on any browser window 
Enter URL in the following format
	(e.g.  http://localhost:9876/TestImage.jpg)
The requested file is displayed on the browser and the contents of the file are displayed on the terminal. 

### Screenshots
<img src="https://github.com/Kidrah31/WebServer_Multi-Threaded/blob/main/SS_1.JPG" width=900><br>
<img src="https://github.com/Kidrah31/WebServer_Multi-Threaded/blob/main/SS_1.1.JPG" width=900><br>
<img src="https://github.com/Kidrah31/WebServer_Multi-Threaded/blob/main/SS_1.2.JPG" width=900><br>
<img src="https://github.com/Kidrah31/WebServer_Multi-Threaded/blob/main/SS_2.JPG" width=900><br>
<img src="https://github.com/Kidrah31/WebServer_Multi-Threaded/blob/main/SS_2.1.JPG" width=900><br>

### Resources:

Project1_reference_Java.pdf
Project 1- 4344_5344_Spring_2021_.pdf
JavaNetwork Programming tutorials from Canvas
https://stackoverflow.com/questions/31075893/im-getting-favicon-ico-error
https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
https://www.javatpoint.com/socket-programming
http://net-informations.com/java/net/multithreaded.htm
