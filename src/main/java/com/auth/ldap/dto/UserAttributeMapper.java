package com.auth.ldap.dto;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import java.util.HashMap;
import java.util.Map;

public class UserAttributeMapper implements AttributesMapper {

    public Map<String, Object> mapFromAttributes(Attributes attrs) {

        try {

            Map<String, Object> map = new HashMap<>();

            NamingEnumeration<String> keys = attrs.getIDs();

            do {
                String key = keys.next();
                map.put(key, attrs.get(key).get());
            }
            while (keys.hasMoreElements());

            return map;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}