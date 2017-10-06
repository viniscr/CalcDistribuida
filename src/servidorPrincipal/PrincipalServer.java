package servidorPrincipal;  
  
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;  
  
public class PrincipalServer implements Runnable {  
  
   private ServerSocket serverSocket = null;  
   private boolean      canRun       = true;  
     
     
   public PrincipalServer(int serverPort) throws IOException {  
      this.serverSocket = new ServerSocket(serverPort);  
   }  
     
     
   public void run() {  
      System.out.println("Servidor principal no ar..");  
      while (!Thread.interrupted() && canRun) {  
         Socket socket = null;  
         try {  
            System.out.println("Aguardando conexao...");   
            socket = serverSocket.accept();  
              
            System.out.println("Cliente conectado: " + socket.getRemoteSocketAddress());  
            Runnable runner = new PrincipalRunner(socket);  
            Thread thread = new Thread(runner);  
            thread.start();  
           
         } catch (Exception e) {  
            e.printStackTrace();  
            canRun = false;  
         }  
      }  
   }  
}