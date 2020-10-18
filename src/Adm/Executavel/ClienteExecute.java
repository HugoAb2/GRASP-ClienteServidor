package Adm.Executavel;

import Adm.Conexao.ClienteConexao;
import Adm.Mensageiro.MensageiroCliente;


import java.io.IOException;
import java.net.Socket;

public class ClienteExecute {

    public static Socket estabelecerConexao()throws IOException{
        ClienteConexao conectClient = new ClienteConexao();
        return conectClient.getCliente();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket cliente = estabelecerConexao();
        boolean loop = true;

        while (loop) {
            MensageiroCliente mensageiroCliente = new MensageiroCliente(cliente);
            mensageiroCliente.getMensagemCliente();
            mensageiroCliente.getMensagemServidor();
            if(mensageiroCliente.getMensagemCliente().equals("desconectar")){
                System.out.println("Conexão Encerrada");
                cliente.close();
                loop = false;
            }
        }
    }
}
