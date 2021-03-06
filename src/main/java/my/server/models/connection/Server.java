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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.server.controllers.MainController;
import my.domain.AbstractClientModel;
import my.util.Message;
import my.util.MessageCode;

/**
 *
 * @author AdministratorJa
 */
public class Server {

    private final int portNumber = 5124;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStreamThread inputStreamThread;
    private OutputStreamThread outputStreamThread;
    private MainController controller;

    public void setController(MainController controller) {
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
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server socket has been set up.");
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
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Client connected.");
    }

    private void openStreams() {
        outputStreamThread = new OutputStreamThread(socket);
        inputStreamThread = new InputStreamThread(socket);
        controller.log("Opened streams.");
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Opened streams.");
    }

    private void clearSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while closing socket.", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie zamykania połączenia.");
            System.exit(12);
        }
        socket = null;
        inputStreamThread = null;
        outputStreamThread = null;
        controller.log("Connection cleared.");
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Connection cleared.");
    }

    public void setUpConnection() {
        if (serverSocket == null) {
            setUpServer();
        }
        waitForClient();
        openStreams();
        controller.log("Initialization complete.");
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Initialization complete.");
        waitForMessage();
    }

    public void waitForMessage() {
        Message message = inputStreamThread.getInput();
        switch (message.getMessageCode()) {
            case ADD_CLIENT:
                if (controller.addClient(message.getClients().get(0))) {
                    outputStreamThread.send(MessageCode.OK, null, null);
                    controller.log("Added client to database.");
                    Logger.getLogger(Server.class.getName()).log(Level.INFO, "Added client to database.");
                } else {
                    outputStreamThread.send(MessageCode.ERROR, null, null);
                }
                break;
            case FIND_CLIENTS:
                Map<String, Object> criteria = message.getCriteria();
                List<AbstractClientModel> clients = controller.findClients(criteria);
                if (clients != null) {
                    if (!clients.isEmpty()) {
                        outputStreamThread.send(MessageCode.OK, clients, null);
                        controller.log("Sent clients list.");
                        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Sent clients list.");
                    } else {
                        outputStreamThread.send(MessageCode.NO_ENTRIES_FOUND, null, null);
                        controller.log("Did not find any clients for criteria.");
                        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Did not find any clients for criteria.");
                    }
                } else {
                    outputStreamThread.send(MessageCode.ERROR, null, null);
                }
                break;
        }
        clearSocket();
        setUpConnection();
    }

}
