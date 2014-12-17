/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.message;

import java.util.ArrayList;
import my.server.models.domain.AbstractClientModel;

/**
 *
 * @author AdministratorJa
 */
public class MessageWrapper {

    public static final String TYPE_SYSTEM = "system";
    public static final String TYPE_DATA = "data";
    private final ArrayList<AbstractClientModel> clientsList;
    private String type;

    public MessageWrapper(ArrayList<AbstractClientModel> clientsList, String type) {
        this.clientsList = clientsList;
        this.type = type;
    }

    public void addClient(ArrayList<AbstractClientModel> clients) {
        for (AbstractClientModel client : clients) {
            clientsList.add(client);
        }
    }
    
    public ArrayList<AbstractClientModel> getClients(){
        return clientsList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
