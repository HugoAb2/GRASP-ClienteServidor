package Adm.Conexao;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteConexao {
    protected Socket cliente;

    public ClienteConexao() throws IOException {
        this.cliente = iniciarCliente();
    }

    private Socket iniciarCliente()throws IOException {
        Socket cliente = new Socket(InetAddress.getLocalHost(), 12345);
        System.out.println(cliente);
        System.out.println("Conexão estabelecida");
        return cliente;
    }

    public Socket getCliente() {
        return cliente;
    }
}