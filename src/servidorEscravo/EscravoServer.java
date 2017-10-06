package servidorEscravo;  
  
import java.io.File;  
  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.Properties;

import base.servidor.TipoServidor;
import servidor.Server;
import servidor.ServerFactory;  
  
/** 
 * Servidor Escravo
 * 
 */  
public class EscravoServer implements Runnable {  
  
    private ServerSocket serverSocket = null;  
    private Socket socket = null;  
    private int serverPort = 0;  
    private TipoServidor operatorType = null;  
    private Server operator = null;  
    private boolean canRun = true;  
  
    public EscravoServer(TipoServidor operatorType) throws IOException,ClassNotFoundException, InstantiationException,  
            IllegalAccessException {  
        this.operatorType = operatorType;  
        this.operator = ServerFactory.getOperator(operatorType);  
        loadProperties();  
        serverSocket = new ServerSocket(this.serverPort);  
    }  
  
    public void run() {  
        System.out.println("Servidor " + operator.getOperatorName()  
                + " no ar");  
  
        while (!Thread.interrupted() && canRun) {  
            socket = null;  
  
            try {  
                System.out.println("Aguardando conexao...");  
                socket = this.serverSocket.accept();  
  
                System.out.println("Cliente conectado: "  
                        + socket.getRemoteSocketAddress());  
                EscravoRunner runner = new EscravoRunner(socket, operator);  
                Thread thread = new Thread(runner);  
                thread.start();  
            } catch (Exception e) {  
                e.printStackTrace();  
                canRun = false;  
            }  
        }  
  
    }  
  
    /** 
     * Carrega as configurações do arquivo de propriedades 
     * 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    private void loadProperties() throws FileNotFoundException, IOException {  
        String propFileName = null;  
        File file = null;  
        FileReader reader = null;  
        Properties properties = null;  
  
        propFileName = "XMLOperatorServer.properties";  
        file = new File(propFileName);  
        reader = new FileReader(file);  
        properties = new Properties();  
        properties.load(reader);  
  
        this.serverPort = Integer.parseInt(properties.getProperty(this.operatorType + ".port"));  
    }  
}  
