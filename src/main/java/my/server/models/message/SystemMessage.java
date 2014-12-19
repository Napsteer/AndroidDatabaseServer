/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.message;

/**
 *
 * @author AdministratorJa
 */
public class SystemMessage implements Message {

    private final String message;
    private final String type = Message.TYPE_SYSTEM;

    public SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getType() {
        return type;
    }

}
