# coffee-locate


Maven:
`mvn install` to download spark-core and gson dependencies

Either javac Main.java to compile or run via IDE like Intellij. 

After starting java ./Main: 
Server reachable at 0.0.0.0:4567

create: POST to /create with query params name, address, latitude, longitude.
	i.e. 0.0.0.0:4567/create?name=test&address=some address&latitude=3&longitude=5

read: GET to /:id

update: PUT to /:id

delete: DELETE to /:id

locate: GET to /locate?address=<address>
- Finding min distance using API latitude and longitude fields; URL and JSON parsing working as expected, but JSON pulled from the API doesn't match URL it's hitting. Timeboxing this for now and will look into if time allots.

API key for google requests: AIzaSyCC2JcvuE9gFibIturM2WAbThtimMbjf_g

Alternative a JSON body for parameter intakes
{
"name": "Some Coffee Place",
"address": "1234 stowers st.",
"latitude": "3",
"longitude": "5"
}
