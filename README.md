# AUTH-LDAP #

Project to list, find and login on public ldap server: forumsys

For more information: https://www.forumsys.com/tutorials/integration-how-to/ldap/online-ldap-test-server/


### Dependencies ###

* Maven
* Java 11
* Spring Boot
* Spring Data LDAP
* Lombok


### Credentials 

The admin credentials is defined on application.yml: <br/>

| Property | Value |
| ----------- | ----------- |
| urls | ldap://ldap.forumsys.com:389 |
| base | dc=example,dc=com |
| username | cn=read-only-admin,dc=example,dc=com |
| password | password | 

* All users have the same password = `password`


## CURLs example

* Search by group mathematicians (NO Token required and partial search): 

```
curl --location --request GET 'localhost:8080/users/group/math' \
--data-raw ''
```

* Search by group scientists (NO Token required and partial search): 

```
curl --location --request GET 'localhost:8080/users/group/scien' \
--data-raw ''
```

* Login: 

```
curl --location --request POST 'localhost:8080/users/login' \
--header 'Content-Type: application/json' \
--data-raw '{
	"uid": "einstein",
	"password": "password"
}'
```

* Search by uid (Token required): 

```
curl --location --request GET 'localhost:8080/users/uid/einstein' \
--header 'Authorization: Bearer GENERATED_TOKEN_ON_LOGIN_ENDPOINT' \
--header 'Cookie: JSESSIONID=2250E697F323CFE5536C2E2B1BDEB11F' \
--data-raw ''
```

* Search all users (Token required): 

```
curl --location --request GET 'localhost:8080/users' \
--header 'Authorization: Bearer GENERATED_TOKEN_ON_LOGIN_ENDPOINT' \
--header 'Cookie: JSESSIONID=2250E697F323CFE5536C2E2B1BDEB11F' \
--data-raw ''
```

* Search by partial mail (Token required): 

```
curl --location --request GET 'localhost:8080/users/mail/no' \
--header 'Authorization: Bearer GENERATED_TOKEN_ON_LOGIN_ENDPOINT' \
--header 'Cookie: JSESSIONID=2250E697F323CFE5536C2E2B1BDEB11F' \
--data-raw ''
```


### JWT Token 

JWT Token generated after call /login (defined above).
The Bearer Token is attached on header after success login


### Spring Security implementation inspired by

https://github.com/amigoscode/spring-boot-security-course