/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models;

import javax.persistence.Entity;

/**
 *
 * @author AdministratorJa
 */
@Entity
public class BusinessClientModel extends AbstractClientModel {

    private String companyName;

    public BusinessClientModel() {
    }

    public BusinessClientModel(String firstName, String lastName, String companyName) {
        super(firstName, lastName);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
