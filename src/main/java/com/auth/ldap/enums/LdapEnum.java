package com.auth.ldap.enums;

import lombok.Getter;

public enum LdapEnum {

    UID("uid"),
    MAIL("mail"),
    OU("ou"),
    DC("dc"),
    OBJECT_CLASS("objectClass");

    @Getter
    private String value;

    LdapEnum(String value) {
        this.value = value;
    }

}
