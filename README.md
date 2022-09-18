# Covid Data Web Processor

## Summary

The implementation of an API and web application for reading, displaying, filtering, and editing Covid data taken from
the repository maintained by Mathieu, Ritchie, Ortiz-Ospina et al. (Mathieu et al, 2021).

The website allows users as well as guests to view and filter the data stored in the database. Registered users of
the site can also add, update, or delete records, as well as countries. A single public API endpoint
is also implemented using the REST web services.

## Project Design

The project is built using the Java EE
framework JSF, as well as JPA for connecting to the database. Oracle SQL is used as the SQL
language and Tomcat as the website’s server.

The project is object-oriented and built using the MVC pattern (Model-View-Controller) to logically structure the code
on components that are built to handle specific development aspects of the application.

Another pattern used is DAO (Data Access Object) which is a pattern that provides an abstract interface to the database.
By mapping application calls to the persistence layer, DAO provides specific data operations without exposing details of
the database.

## Set Up

Given that the data is provided in a CSV file, the application has a built-in convertor that handles the migration
of the records from the CSV format to the Oracle SQL database. It is located
under [DBPopulator java file](https://github.com/anakalemi/Covid-Data-Processor/blob/master/src/main/java/com/covidstats/data/DBPopulator.java)

Before beginning the data migration process it is sufficient to execute
the [SQL file](https://github.com/anakalemi/Covid-Data-Processor/blob/master/src/main/resources/create-tables.sql), that
handles the creation of
structures of the DB tables necessary for this application.

### Credentials

To access the functionalities of an authorized user, find below the credentials:\
Email: *anakalemi@unyt.edu.al*\
Password: *1*

## Dependencies

Java SE 8 or newer\
JSF 2.5\
Primefaces 8.0\
Tomcat 8.5.75\
Oracle SQL 7.0

## Acknowledgments

Covid Dataset From:

Mathieu, E., Ritchie, H., Ortiz-Ospina, E. et al. A global database of COVID-19 vaccinations. Nat Hum Behav (2021).\
https://doi.org/10.1038/s41562-021-01122-8