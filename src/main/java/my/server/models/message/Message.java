/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.message;

import java.util.List;
import my.server.models.domain.AbstractClientModel;

/**
 * Wrapper class for data sent through socket.
 * 
 */

public class Message {

    private final MessageCode messageCode;
    private final List<AbstractClientModel> clients;

    public Message(MessageCode messageCode, List<AbstractClientModel> clients) {
        this.messageCode = messageCode;
        this.clients = clients;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public List<AbstractClientModel> getClients() {
        return clients;
    }
}
