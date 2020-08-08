package com.auth.ldap.dto;

import com.auth.ldap.enums.LdapEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import java.util.ArrayList;
import java.util.List;

public class GroupAttributeMapper implements AttributesMapper {

    public List<String> mapFromAttributes(Attributes attrs) {

        try {

            List<String> uidList = new ArrayList<>();

            NamingEnumeration<?> keys = attrs.getAll();

            while (keys.hasMore()) {

                BasicAttribute basic = (BasicAttribute) keys.next();

                NamingEnumeration<?> keyAttrs = basic.getAll();

                while (keyAttrs.hasMore()) {

                    String key = (String) keyAttrs.next();

                    if (key.contains(LdapEnum.UID.getValue())) {

                        String uid = key
                                .substring(0, key.indexOf(","))
                                .replaceFirst(LdapEnum.UID.getValue().concat("="), StringUtils.EMPTY);

                        uidList.add(uid);

                    }
                }

            }

            return uidList;

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}