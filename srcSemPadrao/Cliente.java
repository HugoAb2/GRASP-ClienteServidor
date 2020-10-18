package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static Socket iniciarCliente()throws IOException {
        Socket cliente = new Socket(InetAddress.getLocalHost(), 12345);
        System.out.println(cliente);
        System.out.println("Conexão estabelecida");
        return cliente;
    }
    private static String enviarMensagem(Socket cliente, Scanner scan) throws IOException {
        // ObjectOutputStream = Classe responsavel por inserir os objetos
        ObjectOutputStream msgClient = new ObjectOutputStream(cliente.getOutputStream());
        msgClient.flush();
        System.out.println("Cliente: ");
        String mensagem = scan.nextLine();
        msgClient.writeObject(new Mensagem(mensagem));
        System.out.println("Mensagem enviada!");
        return mensagem;
    }

    private static void receberMensagem(Socket cliente) throws IOException, ClassNotFoundException {
        // ObjectInputStream = Classe responsavel por ler objeto
        ObjectInputStream servResp = new ObjectInputStream(cliente.getInputStream());
        Mensagem resposta = (Mensagem) servResp.readObject();
        System.out.println("Mensagem recebida!");
        System.out.println("Servidor: " + resposta.getMensagem());
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean loop = true;

        try{
            //Estabelece conexao
            Socket cliente = iniciarCliente();

            while (loop) {
                String mensagem = enviarMensagem(cliente, scan);
                if(mensagem.equals("desconectar")){
                    System.out.println("Desconectado");
                    loop = false;
                    break;
                }
                receberMensagem(cliente);
            }
            cliente.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}