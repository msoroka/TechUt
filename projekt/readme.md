# Projekt Technologie Utrwalania Java

Wybrany temat: Samoloty.

### Uruchomienie

Aby uruchomić projekt niezbędne jest posiadanie Maven oraz naturalnie Javy, najlepiej w wersji 8. Po sklonowaniu repozytorium należy przejść do folderu z projektem i uruchomić sewer bazy danych.
```sh
$ ./scripts/runHSQLDBServer.sh
```
Następnie należy uruchomić testy poleceniem:
```sh
$ mvn test
lub
$ mvn clean test
```
W bazie danych nie będzie żadnych zmian, ponieważ parametr **defaultRollback** ustawiony jest na **true**. Jeśli chcemy podejrzeć rekordy w bazie danych musimy najpierw we wszystkich testach zmienić linię:
```sh
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
na
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
```
oraz uruchomić klienta bazy danych i uruchomić testy jeszcze raz:
```sh
$ ./scripts/runHSQLDBClient.sh
$ mvn test
```
**UWAGA**

Jeśli **rollback** został ustawiony na **false** testy można wykonać tylko raz, ponieważ niektóre kolumny w bazie danych są **unique**.

W kliencie bazy danych aby wykonać zapytanie musimy najpierw odświeżyć widok przechodząc do **View** -> **Refresh Tree** następnie kliknąć prawym przyciskiem myszny na wybraną tabelę, wybrać rodzaj zapytania i kliknąć **Execute SQL**.

