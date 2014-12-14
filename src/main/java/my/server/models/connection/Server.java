/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorJa
 */
public class Server implements Observer {

    // TODO dodac potwierdzenie poszczegolnych krokow otwierania polaczenia
    int portNumber = 5124;
    ServerSocket serverSocket;
    Socket socket;
    InputStreamThread inputStreamThread;
    OutputStreamThread outputStreamThread;

    private void setUpServer() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while trying to set up server socket!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie tworzenia servera. Sprawdź czy server nie jest już uruchomiony.");
            System.exit(10);
        }
    }

    private void waitForClient() {
        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "IO error while accepting client!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie łączenia z klientem.");
            System.exit(11);
        }
    }

    private void openStreams() {
        outputStreamThread = new OutputStreamThread(socket);
        inputStreamThread = new InputStreamThread(socket);
        inputStreamThread.addObserver(this);
        new Thread(inputStreamThread).start();
    }

    private void clearSocket() {
        socket = null;
        inputStreamThread.setActive(false);
        inputStreamThread = null;
        outputStreamThread = null;
        System.out.println("Socket and streams cleared!");
    }

    public void setUpConnection() {
        setUpServer();
        waitForClient();
        openStreams();
    }

    @Override
    public void update(Observable observable, Object object) {

    }
}
