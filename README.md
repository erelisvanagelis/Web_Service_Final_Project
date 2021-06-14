# Web_Service_Final_Project
SocialNetworkAPI
SocialNetworkAPI is an API designed for social networking. It allows to register users, add friends, posts, check the weather in the city where the user lives, see posts made by your friends, detect the language it's users are using and more!

#Getting Started
Software used for the project.

Prerequisites
IntelliJ IDEA Community edition
Apache Tomcat®
GitHub

#End points
GET: /user/register/{name}/{password}/{Email} - registers an user by its name, password and email.
GET: /user/login/{name}/{password} - gets an user by its name and password.
GET: /users - gets all users
GET: /user/delete/{name}/{password} - deletes user by its name and password.
GET: /routes/placeQuote - gets users based on options
GET: /routes/quote - gets quotes in based on options


#Built with
Maven - Dependency Management

#Authors
Dainius Dzikevičius - Developer - Raevon
Liudas Staugaitis - Database - balbazauras
Ignas Norkus - Quality assurance specialist - IgnasNorkus
Antanas Tamašauskas - Developer - erelisvanagelis
