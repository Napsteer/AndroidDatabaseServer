/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.util;

/**
 * Enum containing message codes. Contains both server and client side codes.
 * Member of {@link Message} wrapper class.
 *
 * @see Message
 */
public enum MessageCode {

    /**
     * Task was completed successfully. The criteria map of {@link Message}
     * wrapper will be null.
     *
     * @see Message
     */
    OK,
    /**
     * Return code indicating that there were no entries found for given
     * criteria. The clients list and criteria map of {@link Message} wrapper
     * will be null.
     *
     * @see Message
     */
    NO_ENTRIES_FOUND,
    /**
     * There was an error and request was not fulfilled. The clients list and
     * criteria map of {@link Message} wrapper will be null.
     *
     * @see Message
     */
    ERROR,
    /**
     * Task code used to find clients by given criteria. The clients list of
     * {@link Message} wrapper will be null.
     *
     * @see Message
     */
    FIND_CLIENTS,
    /**
     * Task code used to add client data to database. The criteria map of
     * {@link Message} wrapper will be null.
     *
     * @see Message
     */
    ADD_CLIENT;
}
