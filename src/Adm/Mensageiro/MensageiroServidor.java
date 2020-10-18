package Adm.Mensageiro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MensageiroServidor extends Mensageiro {

    protected String mensagemCliente;
    protected String mensagemServidor;

    public MensageiroServidor(Socket cliente) throws IOException, ClassNotFoundException {
        super(cliente);
        this.mensagemCliente = receberMensagem(cliente);
        this.cliente = cliente;
        this.mensagemServidor = enviarMensagem(cliente);
    }

    @Override
    public String enviarMensagem() throws IOException {
        return null;
    }

    public String enviarMensagem(Socket cliente) throws IOException{
        ObjectOutputStream msgServ = new ObjectOutputStream(cliente.getOutputStream());
        msgServ.flush();
        String respostaServidor = "Mensagem Recebida!";
        msgServ.writeObject(new Mensagem (respostaServidor));
        System.out.println("Resposta Enviada");
        return respostaServidor;
    }

    public String receberMensagem(Socket cliente) throws IOException, ClassNotFoundException {
        ObjectInputStream msgClient = new ObjectInputStream(cliente.getInputStream());
        Mensagem mensagem = (Mensagem) msgClient.readObject();
        String resposta = mensagem.getMensagem();
        System.out.println("Mensagem Recebida!\n" + "Cliente: " + resposta);
        return resposta;
    }

    public String getMensagemCliente() {
        return mensagemCliente;
    }

    public String getMensagemServidor() {
        return mensagemServidor;
    }

}
