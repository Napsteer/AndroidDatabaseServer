/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.util.Message;

/**
 *
 * @author AdministratorJa
 */
public class InputStreamThread {

    ObjectInputStream objectInputStream;

    protected InputStreamThread(Socket socket) {
        try {
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while opening input stream!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie otwierania strumienia wejściowego.");
            System.exit(20);
        }
    }

    protected Message getInput() {
        Message input = null;
        try {
            input = (Message) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while reading from input stream!", ex);
            JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
            System.exit(21);
        }
        return input;
    }

}
