package Adm.Executavel;

import Adm.Conexao.ServidorConexao;
import Adm.Mensageiro.MensageiroServidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExecute {
    public static void main(String[] args) {

        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = ServidorConexao.servidor();
            Socket cliente = ServidorConexao.receberCliente(servidor);
            boolean loop = true;

            while (loop) {
                MensageiroServidor mensageiroServidor = new MensageiroServidor(cliente);
                mensageiroServidor.getMensagemCliente();
                mensageiroServidor.getMensagemServidor();
                if (mensageiroServidor.getMensagemServidor().equals("desconectar")){
                    servidor.close();
                    loop = false;
                }
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
