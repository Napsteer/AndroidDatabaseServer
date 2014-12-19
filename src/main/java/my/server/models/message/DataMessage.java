/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.message;

import java.util.List;
import my.server.models.domain.AbstractClientModel;

/**
 *
 * @author AdministratorJa
 */
public class DataMessage implements Message {

    private final List<AbstractClientModel> clientsList;
    private final String type = Message.TYPE_DATA;

    public DataMessage(List<AbstractClientModel> clientsList) {
        this.clientsList = clientsList;
    }

    public List<AbstractClientModel> getClients() {
        return clientsList;
    }

    @Override
    public String getType() {
        return type;
    }

}
