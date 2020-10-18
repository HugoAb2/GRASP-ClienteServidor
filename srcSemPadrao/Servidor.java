package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    private static ServerSocket servidor()throws IOException {
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println(servidor);
        return servidor;
    }

    private static Socket receberCliente(ServerSocket servidor)throws IOException{
        Socket cliente = servidor.accept(); // aguardar conexao
        System.out.println("Conexão estabelecida!\n" + cliente.getInetAddress().getHostAddress());
        return cliente;
    }

    private static String receberMensagem(Socket cliente) throws IOException, ClassNotFoundException {
        // Recebe Mensagem
        ObjectInputStream msgClient = new ObjectInputStream(cliente.getInputStream());
        Mensagem mensagem = (Mensagem) msgClient.readObject();
        String resposta = mensagem.getMensagem();
        System.out.println("Mensagem Recebida!");
        System.out.println("Cliente: " + resposta);
        return resposta;
    }

    private static void enviarMensagem(Socket cliente, Scanner scan) throws IOException{
        ObjectOutputStream msgServ = new ObjectOutputStream(cliente.getOutputStream());
        msgServ.flush();
        System.out.println("Servidor: ");
        msgServ.writeObject(new Mensagem (scan.nextLine()));
        System.out.println("Mensagem enviada!");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = servidor();
            Socket cliente = receberCliente(servidor);

            while (loop) {
                String mensagem = receberMensagem(cliente);
                if(mensagem.equals("desconectar")) {
                    System.out.println("Desconectado");
                    loop = false;
                    break;
                }
                enviarMensagem(cliente, scan);
            }
            servidor.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}