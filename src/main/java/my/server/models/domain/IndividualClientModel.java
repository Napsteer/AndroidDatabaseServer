/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.server.models.domain;

import javax.persistence.Entity;

/**
 *
 * @author AdministratorJa
 */
@Entity
public class IndividualClientModel extends AbstractClientModel {

    private int age;

    public IndividualClientModel() {
    }

    public IndividualClientModel(String firstName, String lastName, int age) {
        super(firstName, lastName);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
