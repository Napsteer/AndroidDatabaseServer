/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AdministratorJa
 */
public class OutputStreamThread extends Observable {

    ObjectOutputStream objectOutputStream;

    public OutputStreamThread(Socket socket) {
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while opening output stream!", ex);
            JOptionPane.showMessageDialog(null, "Wystąpił błąd w trakcie otwierania strumienia wyjßciowego.");
            System.exit(30);
        }
    }

    protected void sendObject(Object output) {
        try {
            objectOutputStream.writeObject(output);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException ex) {
            Logger.getLogger(InputStreamThread.class.getName()).log(Level.SEVERE, "IO error while writing to output stream!", ex);
            JOptionPane.showMessageDialog(null, "Połączenie zerwane. Spróbuj uruchomić aplikację ponownie.");
            System.exit(31);
        }
    }
}
