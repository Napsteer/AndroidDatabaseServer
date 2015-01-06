/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.server.controllers.MainController;
import my.server.models.domain.AbstractClientModel;
import my.server.models.message.DataMessage;
import my.server.models.message.Message;
import my.server.models.message.QueryMessage;
import my.server.models.message.SystemMessage;

/**
 *
 * @author AdministratorJa
 */
public class Server implements Observer {

    // TODO dodac loggowanie poszczegolnych krokow otwierania polaczenia
    // TODO dodac sendMessage
    private final int portNumber = 5124;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStreamThread inputStreamThread;
    private OutputStreamThread outputStreamThread;
    private MainController controller;

    public void setController(MainController controller){
        this.controller = controller;
    }
    
    private void setUpServer() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while trying to set up server socket!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie tworzenia servera. Sprawdź czy server nie jest już uruchomiony.");
            System.exit(10);
        }
        controller.log("Server socket has been set up.");
    }

    private void waitForClient() {
        try {
            controller.log("Waiting for client.");
            socket = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while accepting client!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie łączenia z klientem.");
            System.exit(11);
        }
        controller.log("Client connected.");
    }

    private void openStreams() {
        outputStreamThread = new OutputStreamThread(socket);
        inputStreamThread = new InputStreamThread(socket);
        inputStreamThread.addObserver(this);
        new Thread(inputStreamThread).start();
        controller.log("Opened streams.");
    }

    private void clearSocket() {
        socket = null;
        inputStreamThread.shutdown();
        inputStreamThread = null;
        outputStreamThread = null;
        controller.log("Connection cleared.");
    }

    public void setUpConnection() {
        setUpServer();
        waitForClient();
        openStreams();
        controller.log("Initialization complete.");
    }

    @Override
    public void update(Observable observable, Object object) {
        String messageType = inputStreamThread.getInput().getType();
        if (messageType.equals(Message.TYPE_DATA)) {
            DataMessage message = (DataMessage) inputStreamThread.getInput();
            controller.addClients(message.getClients());
        }
        if (messageType.equals(Message.TYPE_SYSTEM)) {
            SystemMessage message = (SystemMessage) inputStreamThread.getInput();
            switch (((SystemMessage) message).getMessage()) {
                case DataMessage.SYSTEM_CLOSE_SOCKET:
                    clearSocket();
                    controller.log("Client disconnected.");
                    break;
                default:
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while accepting client!");
                    JOptionPane.showMessageDialog(null, "Otrzymano wiadomość o nieznanym typie.");
                    System.exit(12);
            }
        }
        if (messageType.equals(Message.TYPE_QUERY)) {
            QueryMessage message = (QueryMessage) inputStreamThread.getInput();
            controller.executeQuery(message.getQuery());
        }
    }

    public void send(List<AbstractClientModel> clients) {
        outputStreamThread.send(clients);
    }
}
