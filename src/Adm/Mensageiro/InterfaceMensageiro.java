package Adm.Mensageiro;

import java.io.IOException;
import java.net.Socket;

public interface InterfaceMensageiro {

     String enviarMensagem()throws IOException;
     String receberMensagem(Socket socket)throws IOException, ClassNotFoundException;

}
