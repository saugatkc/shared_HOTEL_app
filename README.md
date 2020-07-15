1. Title of my project

Hotel Finder(BagPacker)

2. Introduction

Bag Packer is an android app for booking hotels register in the app. Its a solution to finding the surrounding hotel and booking them. For the purpose of booking hotel and searching hotels in nearby area its a perfect app. User can know about hotel in the place they want to go and book it accordingly with the app. The main purpose of this app is to make user easy to find hotels in the area they don't know and give the user location of the hotel so the user can go to the hotel without any difficulties.

3. Features of your project
    • User registration and login
    • view all the hotels registered
    • view details of the hotel
    • Book Room 
    • view users bookings and old stays accordingly
    • cancel booking
    • add hotel to the saved list
    • view the location of hotel in the map 
	
4. Android project Youtube video link


5. API link 
https://github.com/softwarica-github/t2-backend-api-saugatkc

6. REST client : Explain about retrofit and its uses 

Retrofit is a type-safe HTTP client for Android and Java developed by Square. Using Retrofit we can easily translate the REST APIs to Java Interfaces. Retrofit can be used as the best alternative to Volley. Retrofit Uses OkHttp for making HTTP requests. Retrofit easily translates JSON or XML response to POJO’s (Plain Old Java Objects).
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based web service. In Retrofit you configure which converter is used for the data serialization.

By the use of retrofit, networking is easier in Android apps. As it has many features like easy to add custom headers and request types, file uploads, mocking responses, etc through which we can reduce boilerplate code in our apps and consume the web service easily.

To work with Retrofit we basically need the following three classes,a model class which is used as a JSON model.An interface that defines the HTTP operations needs to be performed,Retrofit.Builder class: Instance which uses the interface defined above and the Builder API to allow defining the URL endpoint for the HTTP operations. It also takes the converters we provide to format the Response.
