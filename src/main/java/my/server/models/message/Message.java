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
public interface Message {

    public static final String TYPE_SYSTEM = "system";
    public static final String TYPE_DATA = "data";
    public static final String TYPE_QUERY = "query";
    public static final String SYSTEM_CLOSE_SOCKET = "close";

    public String getType();
}
