package Mensageiro;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public abstract class Mensageiro implements InterfaceMensageiro {
    Scanner scan = new Scanner(System.in);
    protected Socket cliente;

    public Mensageiro(){}

    public Mensageiro(Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public abstract String enviarMensagem() throws IOException;

    @Override
    public abstract String receberMensagem(Socket socket) throws IOException, ClassNotFoundException;
}
