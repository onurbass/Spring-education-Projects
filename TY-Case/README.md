To Run the application, please follow the steps below:

1. In the application, no database is used. You can easily run the project in your IDE 
and utilize Swagger to make requests to the application using the following link.
   http://localhost:8080/swagger-ui/index.html#/
 
With Docker:
2. Pull the docker image from docker hub with the command below:
docker pull onurbass/ty-checkout-case:v001

3. Run the application with the command below:
docker run --name "OnurBasTYcheckout"  -d -p 8080:8080 onurbass/ty-checkout-case:v001

