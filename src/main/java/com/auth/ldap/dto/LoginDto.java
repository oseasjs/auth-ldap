package com.auth.ldap.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String uid;
    private String password;

}
