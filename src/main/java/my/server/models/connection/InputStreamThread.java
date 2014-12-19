/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.server.models.message.Message;

/**
 *
 * @author AdministratorJa
 */
public class InputStreamThread extends Observable implements Runnable {

    ObjectInputStream objectInputStream;
    private Object input;
    private boolean active;

    public Message getInput() {
        return (Message) input;
    }

    public void shutdown() {
        this.active = false;
    }

    protected InputStreamThread(Socket socket) {
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while opening input stream!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie otwierania strumienia wejściowego.");
            System.exit(20);
        }
        active = true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                input = objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while reading from input stream!", ex);
                JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
                System.exit(21);
            }
            setChanged();
            notifyObservers();
            if (!active) {
                return;
            }
        }
    }

}
