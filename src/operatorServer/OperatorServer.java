package operatorServer;  
  
import java.io.File;  
  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.Properties;  
  
import operator.Operator;  
import operator.OperatorFactory;  
import base.operator.OperatorType;  
  
/** 
 * Servidor de operações 
 * 
 */  
public class OperatorServer implements Runnable {  
  
    private ServerSocket serverSocket = null;  
    private Socket       socket       = null;  
    private int          serverPort   = 0;  
    private OperatorType operatorType = null;  
    private Operator     operator     = null;  
    private boolean      canRun       = true;  
  
    public OperatorServer(OperatorType operatorType) throws IOException,ClassNotFoundException, InstantiationException,  
            IllegalAccessException {  
        this.operatorType = operatorType;  
        this.operator = OperatorFactory.getOperator(operatorType);  
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
                OperatorRunner runner = new OperatorRunner(socket, operator);  
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
        file         = new File(propFileName);  
        reader       = new FileReader(file);  
        properties   = new Properties();  
        properties.load(reader);  
  
        this.serverPort = Integer.parseInt(properties.getProperty(this.operatorType + ".port"));  
    }  
}  
