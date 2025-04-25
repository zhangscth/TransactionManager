# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

### Guides
The following guides illustrate how to use apis concretely:

* add
  POST http://localhost:8080/transaction-manager/api/transaction
* delete
  DELETE http://localhost:8080/transaction-manager/api/transaction/{id}
* list
  GET http://localhost:8080/transaction-manager/api/transaction?page={page}&size={size}
* modify
  PUT http://localhost:8080/transaction-manager/api/transaction

### third party dependencies
* spring-boot-starter-web
* lombok
* h2
* mybatis-spring-boot-starter

### performance testing
![add api perfermance](.\images\add_metric.png)
![add api perfermance](.\images\get_all_metric.png)
![add api perfermance](.\images\modify_metric.png)