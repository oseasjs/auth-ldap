package com.auth.ldap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LdapUserDto {

    private String uid;
    private String telephoneNumber;
    private String mail;
    private String objectClass;
    private String sn;
    private String cn;

    // All ldap users has the same password = password
    @JsonIgnore
    private String password = "password";

}
