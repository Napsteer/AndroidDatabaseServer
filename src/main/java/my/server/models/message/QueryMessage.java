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
public class QueryMessage implements Message {

    private final String query;
    private final String type = Message.TYPE_QUERY;

    public QueryMessage(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String getType() {
        return type;
    }

}
