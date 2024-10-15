# wallet service
## a test project to show how a wallet can perform

### description
welcome to wallet service.
this service has developed as a sample to show some basic functionalities of a wallet service and how it uses
transactions as it's main source of truth.</br>
<b>functionalities</b> <br>
1. deposit money to wallet
2. withdraw from wallet
3. transfer money to another wallet 
4. get wallet balance
5. get total amount of each transaction type(withdraw,deposit,send,receive)
6. get list of all transactions


this project is a login based project which user should signup to system and then login to use wallet services.


### installation and running the project
1. clone this repository
2. use docker-compose up --build -d to install and run the project and it's required dependencies on docker environment
3. to see services and test them we can use swagger address : [Swagger](localhost:8080/swagger-ui.html)

### how to use this project:
1. go to [swagger ui](localhost:8080/swagger-ui.html)
2. [ signup service to create a new user](http://localhost:8080/swagger-ui/index.html#/authentication%20api/register)
3. [login service to get a jwt token](http://localhost:8080/swagger-ui/index.html#/authentication%20api/login)
   1. this jwt token should be put as a header to call following Apis,example: header(Authorization:Bearer jwtToken)
4. [deposit api](http://localhost:8080/swagger-ui/index.html#/wallet%20api/deposit)
5. [withdraw api](http://localhost:8080/swagger-ui/index.html#/wallet%20api/withdraw)
6. [transfer](http://localhost:8080/swagger-ui/index.html#/wallet%20api/transfer)
7. [total-by-period api](http://localhost:8080/swagger-ui/index.html#/wallet%20api/getTotalByTypeAndFromTime)
8. [wallet balance](http://localhost:8080/swagger-ui/index.html#/wallet%20api/getWalletBalance)
9. [all-transactions api](http://localhost:8080/swagger-ui/index.html#/wallet%20api/getAll)


### Features
- database : postgres
- logging : used logback and aspect component(ControllerLogger) to log start and end of Apis.
- validations : java validation on all input fields.

### issues
there are some issues as below:
#### 1. thread safety and speed <br>
critical transactions like withdraw and transfer , need to know the current balance of the user. therefore I used synchronized keyword on 
_persistTransaction_ method of _TransactionService_ class. this cause all transactions wait for the lock and this issue make the applicaiton slow.
and I should find better way for this part.
#### 2. read list of transactions: <br>
i used join table strategy for transaction classes. this strategy prevents of data duplication and null fields.
but for each select it makes a join. so it is not suitable for read data in list. solution is to use a nosql db 
which holds required data for display in tables. I did not have enough time to implement this idea so I read data 
from postgres.


### tests
I wrote integration test for most of important Apis. which can be find in test directory. <br>
1. authentication test:
   1. AuthenticationControllerIntegrationTest
2. wallet test:
   1. WalletControllerIntegrationTest
   


