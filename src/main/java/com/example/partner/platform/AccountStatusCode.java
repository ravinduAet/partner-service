package com.example.partner.platform;

/**
 * AccountStatusCode take care of managing responses which client can easily
 * understand. This act as Status code and Status message if validation failure
 * occoured.
 * 
 * @author ravindu.s
 *
 */
public enum AccountStatusCode {

    INVALID_PARTNER("Partner cannot be null."),

    LEGAL_FIRST_NAME_REQUIRED("Partner's Legal first name required."),

    LEGAL_LAST_NAME_REQUIRED("Partner's Legal last name required."),
    
    PARTNER_ADDRESS_REQUIRED("Partner's address required."),

    PARTNER_CITY_REQUIRED("Partner's city required."),

    PARTNER_STATE_REQUIRED("Partner state required."),
    
    PARTNER_TYPE_REQUIRED("Partner type required."),
    
    INVALID_PARTNER_TYPE("Invalid partner type found.");

    private final String explanation;

    private AccountStatusCode(String reasonPhrase) {
        this.explanation = reasonPhrase;
    }

    public String getDescription() {
        return explanation;
    }

}
