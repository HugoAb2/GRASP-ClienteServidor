package Mensageiro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MensageiroCliente extends Mensageiro {

    protected String mensagemCliente;
    protected String mensagemServidor;

    public MensageiroCliente(Socket cliente) throws IOException, ClassNotFoundException {
        super(cliente);
        this.mensagemCliente = enviarMensagem(cliente, scan);
        this.mensagemServidor = receberMensagem(cliente);
    }

    @Override
    public String enviarMensagem() throws IOException {
        return null;
    }


    public String enviarMensagem(Socket cliente, Scanner scan) throws IOException {
        ObjectOutputStream msgClient = new ObjectOutputStream(cliente.getOutputStream());
        msgClient.flush();
        System.out.println("Cliente: ");
        String mensagem = scan.nextLine();
        msgClient.writeObject(new Mensagem(mensagem));
        System.out.println("Mensagem enviada!");
        return mensagem;
    }

    public String receberMensagem(Socket cliente) throws IOException, ClassNotFoundException {
        // ObjectInputStream = Classe responsavel por ler objeto
        ObjectInputStream servResp = new ObjectInputStream(cliente.getInputStream());
        Mensagem resposta = (Mensagem) servResp.readObject();
        System.out.println("Servidor: " + resposta.getMensagem());
        return resposta.getMensagem();
    }

    public String getMensagemCliente() {
        return mensagemCliente;
    }

    public String getMensagemServidor() {
        return mensagemServidor;
    }
}
