package com.example.partner.domain.partner;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.partner.domain.audit.Auditable;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String address1;

    private String address2;

    private String city;

    private String state;

    public Address() {
    }

    public Address(Address address) {
        this.id = address.id;
        this.address1 = address.address1;
        this.address2 = address.address2;
        this.city = address.city;
        this.state = address.state;

        // auditable
        this.createdBy = address.getCreatedBy();
        this.createdDate = address.getCreatedDate();
        this.lastModifiedBy = address.getLastModifiedBy();
        this.lastModifiedDate = address.getLastModifiedDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
