package com.auth.ldap.controller;

import com.auth.ldap.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    LdapService ldapService;

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(ldapService.findAll());
    }

    @GetMapping("/uid/{uid}")
    public ResponseEntity findByUid(@PathVariable("uid") final String uid) {
        return ResponseEntity.ok(ldapService.findByUid(uid));
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity findByPartialMail(@PathVariable("mail") final String mail) {
        return ResponseEntity.ok(ldapService.findByPartialMail(mail));
    }

    @GetMapping("/group/{group}")
    public ResponseEntity findByGroup(@PathVariable("group") final String group) {
        return ResponseEntity.ok(ldapService.findByGroup(group));
    }

}
