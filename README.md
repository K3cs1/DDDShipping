###### Domain Driven Design Shipping application

https://dddshipping.herokuapp.com/

**Required: Java 17**

`./gradlew clean build`  
`./gradlew mainapp:bootRun`

**MongoDB Docker setup**

````
cd docker
docker-compose up -d
docker exec -it dddshipping_mongodb bash
````

*Before run the application, create a new database with name "dddshipping" :*

*Compass connection url:*

````
mongodb://rootuser:rootpass@localhost:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false
````