package com.example.partner.domain.partner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.partner.domain.audit.Auditable;

@Entity
@Table(name = "partner")
@EntityListeners(AuditingEntityListener.class)
public class Partner extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String legalFirstName;

    private String legalLastName;

    private String preferedName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    public Partner() {
    }

    public Partner(Partner partner) {
        super();
        this.id = partner.id;
        this.legalFirstName = partner.legalFirstName;
        this.legalLastName = partner.legalLastName;
        this.preferedName = partner.preferedName;
        this.address = partner.address;
        this.partnerType = partner.partnerType;

        // auditable
        this.createdBy = partner.getCreatedBy();
        this.createdDate = partner.getCreatedDate();
        this.lastModifiedBy = partner.getLastModifiedBy();
        this.lastModifiedDate = partner.getLastModifiedDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegalFirstName() {
        return legalFirstName;
    }

    public void setLegalFirstName(String legalFirstName) {
        this.legalFirstName = legalFirstName;
    }

    public String getLegalLastName() {
        return legalLastName;
    }

    public void setLegalLastName(String legalLastName) {
        this.legalLastName = legalLastName;
    }

    public String getPreferedName() {
        return preferedName;
    }

    public void setPreferedName(String preferedName) {
        this.preferedName = preferedName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }

}
