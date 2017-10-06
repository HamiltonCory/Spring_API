# Spring_API

To run the Spring_API jar:
1. Open terminal and navigate to the directory where the project will be cloned
2. Type: git clone https://github.com/HamiltonCory/Spring_API.git
3. Navigate to Spring_API/out/artifacts/Spring_API_jar folder
4. Type: java -jar Spring_API.jar

After completing the above steps, the Spring_API Java application should be running on whatever host it has been deployed.
The Tomcat server runs on port 8080 by default.

The API has the following endpoints (using localhost:8080 as default host and port):
1.	GET localhost:8080/health
2.	POST localhost:8080/accounts
3.	GET localhost:8080/accounts/{id}
4.	GET localhost:8080/accounts/{id}/ledger
5.	POST localhost:8080/transactions

Note:
1.	{id} is the accountId
2. Remember to use Content-Type: application/json in the header of any HTTP posts with data in the body