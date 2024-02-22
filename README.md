# Code100 Competition - Java Sample

This codebase contains everything you need to complete coding challenges of the Code100 competition in Java.
The code example of communicating with the API is already present in the `App.java` file located under `src/main/java/com/mycompany/app`.

If you need additional information regarding the API you may find it in the rest of this document.

### Running the project

Open the terminal and run:
```sh
mvn compile exec:java@App -q
```

## API Base URL
```
https://challenger.code100.dev
```

## Swagger Documentation
```
https://challenger.code100.dev/doc/
```
## Login

In order to request the coding puzzle or submit the solution, you first need to retrieve the JSON Web Token by logging into the API:

### Request
```
POST /login
```
### Body
```
{
  "email": <email here>,
  "password": <password here>
}
```
### Response
```
{
   ...
   "token": <JWT token>
   ...
}
```
The token given in this response needs to be supplied for the Request Puzzle and Submit Solution requests.

## Request Puzzle

### Request
```
GET /getpuzzle
```
### Headers
```
Authorization: Bearer <JWT token>
```
### Response
```
{
  "name": <name of the puzzle>,
  "description": <description of the puzzle>,
  "input": <coding challenge>
}
```
(coding challenge depends from round to round)

## Submit Solution

### Request
```
POST /postanswer
```
### Headers
```
Authorization: Bearer <JWT token>
```
### Body
```
{
  "answer": <your solution to the posted puzzle>
}
```
### Response

There is a 15 seconds delay for you to receive the answer for this request.
```
{
  "message": <information if solution was correct or not>,
}
```