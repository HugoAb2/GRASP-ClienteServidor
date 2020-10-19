package Conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorConexao {

    public static ServerSocket servidor()throws IOException {
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println(servidor);
        return servidor;
    }

    public static Socket receberCliente(ServerSocket servidor)throws IOException{
        Socket cliente = servidor.accept(); // aguardar conexao
        System.out.println("Conexão estabelecida!\n" + cliente.getInetAddress().getHostAddress());
        return cliente;
    }
}