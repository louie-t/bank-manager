# bank-manager
This is a GUI application that uses the swing and swt libraries in Eclipse. To run this GUI application, those resources will need to be installed. This application stores the data of different users in an sqlite database that is currently a `.db` file in the project directory. 

## The login window
The program currently has three windows with the first one being the login window. In the login screen, users can login using an existing account in the sql database, or create a new account. The other two pop up windows extend from here.

## The create account window
By selecting to create an account in the login, users can create a new bank account that gets added to the sql database. Note that all the fields must be filled for an account to be created, and that the username must not already exist in the database. Money also needs the be specified as an integer.

## The banking window
If the user chooses to login instead, they will be redirected to their own banking page with the ability to check their balance, withdraw or deposit money. Note that money needs to be specified as an integer, as coins are not allowed to be deposited.