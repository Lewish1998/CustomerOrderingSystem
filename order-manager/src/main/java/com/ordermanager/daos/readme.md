# Database Access Objects

See (here)[https://www.baeldung.com/java-dao-pattern].

By creating interfaces for all of the classes, it allows for more structure within the code and for better readability.
The CustomerDAO interface, for example, contains 4 functions which are inherited by the CustomerDAOImpl class which then provides the functionality for the functions.

The CusttomerDAOImpl class requires a connection to be able to run.
It is then used to impiment the necessary SQL code used for executing the functionality for each method.
As the methods are being taken fro mthe CustomerDAO class, they must begin with the @Override decorator.