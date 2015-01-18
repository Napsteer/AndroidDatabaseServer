/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import my.domain.AbstractClientModel;

/**
 * Wrapper class for data sent through socket. Contains message code, list of
 * clients and a map of criteria. The last two can be null in some cases.
 *
 */
public class Message implements Serializable {

    private final MessageCode messageCode;
    private final List<AbstractClientModel> clients;
    private final Map<String, Object> criteria;

    /**
     *
     * @param messageCode Code of the message from enum {@link MessageCode}.
     * @param clients List of clients. Can be null in some scenarios.
     * @param criteria Map of String criterias, used to fetch clients from
     * database.
     * @see MessageCode
     */
    public Message(MessageCode messageCode, List<AbstractClientModel> clients, Map<String, Object> criteria) {
        this.messageCode = messageCode;
        this.clients = clients;
        this.criteria = criteria;
    }

    /**
     * Get message code assigned to this message.
     *
     * @return {@link MessageCode}
     */
    public MessageCode getMessageCode() {
        return messageCode;
    }

    /**
     * Get a list of clients. Can be null in case of some message codes.
     *
     * @return {@link List} of {@link AbstractClientModel} objects.
     * @see MessageCode
     */
    public List<AbstractClientModel> getClients() {
        return clients;
    }

    /**
     * Get a map of criterias for database query. Can be null in case of some
     * message codes.
     *
     * @return {@link Map} of {@link String} keys and any values.
     * @see MessageCode
     */
    public Map<String, Object> getCriteria() {
        return criteria;
    }
}
