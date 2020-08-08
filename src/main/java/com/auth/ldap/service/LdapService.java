package com.auth.ldap.service;

import com.auth.ldap.dto.GroupAttributeMapper;
import com.auth.ldap.dto.LdapUserDto;
import com.auth.ldap.dto.LoginDto;
import com.auth.ldap.dto.UserAttributeMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.auth.ldap.enums.LdapEnum.*;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class LdapService {

    @Autowired
    LdapTemplate ldapTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public List<LdapUserDto> findByGroup(final String group) {

        final LdapQuery query = query()
                .where(OU.getValue())
                .like("*" + group + "*");

        List<List<String>> uidList = ldapTemplate.search(query, new GroupAttributeMapper());
        List<LdapUserDto> userlist = new ArrayList<>();

        uidList.forEach(list -> {
            list.forEach(uid -> {
                userlist.add(findByUid(uid));
            });
        });

        return userlist;

    }

    public LdapUserDto findByUid(final String uid) {

        final LdapQuery query = query()
                .where(UID.getValue())
                .is(uid);

        List<Map> list = ldapTemplate.search(query, new UserAttributeMapper());
        return objectMapper.convertValue(list.get(0), LdapUserDto.class);

    }

    public List<LdapUserDto> findByPartialMail(final String mail) {

        final LdapQuery query = query()
                .where(MAIL.getValue())
                .like("*" + mail + "*");

        List list = ldapTemplate.search(query, new UserAttributeMapper());
        return objectMapper.convertValue(list, new TypeReference<List<LdapUserDto>>() {});

    }

    public List<LdapUserDto> findAll() {

        final LdapQuery query = query()
                .countLimit(10)
                .timeLimit(5000)
                .searchScope(SearchScope.SUBTREE)
                .where(OBJECT_CLASS.getValue())
                .isPresent();

        List list = ldapTemplate.search(query, new UserAttributeMapper());
        return objectMapper.convertValue(list, new TypeReference<List<LdapUserDto>>() {});

    }

    public Object login(LoginDto user) {

        final LdapQuery query = query()
                .where(UID.getValue())
                .is(user.getUid());
        ldapTemplate.authenticate(query, user.getPassword());
        return findByUid(user.getUid());

    }

}