/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.message;

/**
 * Enum containing message codes.
 * Contains both server and client side codes.
 */

public enum MessageCode {

    /**
     * Task was completed successfully.
     */
    OK, 

    /**
     * Return code used when asking for client data.
     * No entries for given criteria were found.
     */
    NO_ENTRIES_FOUND, 

    /**
     * Return code used when adding user to database.
     * Indicates that client data was not saved.
     */
    ERROR,

    /**
     * Task code used to find clients by given criteria.
     */
    FIND_CLIENTS, 

    /**
     * Task code used to add client data to database.
     */
    ADD_CLIENT; // Client side
}
