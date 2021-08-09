###### Domain Driven Design Shipping example

**Required: Java 11+**

`./gradlew clean build`  
`./gradlew mainapp:run `

**MongoDB Docker setup**

````
cd docker
docker-compose up -d
docker exec -it dddshipping_mongodb bash
````

*Inside the MongoDB Docker container*

````
mongo
> db.createUser( { user: "root", pwd: "root", roles: [ "root" ] } );
> use dddshipping;
> db.createUser( { user: "dddshippinguser", pwd: "dddshipping12345", roles: [ { role: "readWrite", db: "dddshipping" } ] } );
````

`mongo -u dddshippinguser -p dddshipping12345 --authenticationDatabase dddshipping`