This App returns primes numbers up to and including a given number

there are GET and POST requests configured.

GET uses paramaters "number" (positive only) and "algo" (1 or 2, defaults at one)

POST uses a request Body in the form of a JSON request. 

GET has the URI primes/get
Post has the URI primes/post

The app is deployed on a local docker container. the URL is as follows : http://193.237.11.162:8080/primes/get?number=200
or you can run the app on local host. 
