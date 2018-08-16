# Task Condition
- Necessary to develop REST API server, while sending a POST request to localhost:8080/customer, need to 
create a csv file with unique name. File name should be sent to the client upon successful execution of the request;
- While sending a GET request to localhost:8080/customer/{uniqueFileName} the client receives information from csv file;
- Need to save customer data to database.
# Example
- Protocol: POST | URI: /customer | Request body:
```json
{
"id":101,
"name":"John",
"age":22,
"mobile_no":380633221100
}
```
Response with status 201:
```json
{
"file_name":"201808141059"
}
```
- Protocol: GET | URI: /customer/ 201808141059  
Response with status 201:
```json
{
"id":101,
"name":"John",
"age":22,
"mobile_no":380633221100
}
```
# How to run app
Just clone this repository and run MetroApplication class which annotated @SpringBootApplication.
Then you will see all results in console.
