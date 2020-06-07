# Typo 3 user to Keycloak migration
This app migrates users from the helfen-kann-jeder typo3 user repository to keycloak.

## Execution
Execute the migration on a system that has access to the typo3 database. 

The following parameters can be specified

| Param                        | required | default                                       | description                                                                                                 |
|------------------------------|----------|-----------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| `spring.datasource.username` | [x]      | -                                             |                                                                                                             |
| `spring.datasource.password` | [x]      | -                                             |                                                                                                             |
| `spring.datasource.url`      | -        | `jdbc:mysql://localhost:3306/helfenkannjeder` |                                                                                                             |
| `keycloak.baseUrl`           | -        | `http://localhost:8086`                       |                                                                                                             |
| `keycloak.clientId`          | [x]      | -                                             |                                                                                                             |
| `keycloak.clientSecret`      | [x]      | -                                             |                                                                                                             |
| `import.emailFilter`         | -        | -                                             | regex, if it matches to a users email, the user will not be imported. Example: .+@domain.com&#124;.+@domain2.de |

```
java -jar typo3-users-to-keycloak-migration -Dspring.datasource.username=abc \ 
-Dspring.datasource.password=abc \
-Dkeycloak.clientId=abc \
-Dkeycloak.clientSecret=abc
``
