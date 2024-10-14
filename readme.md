# wallet service
## a test project to show how a wallet can perform

### description
welcome to wallet service.
this service is developed as a sample to show some basic functionalities of a wallet service and how it uses
transactions as it's main source of truth.

this project is a login based project which user should signup to system and then login to use wallet services.


### installation and running the project
1. clone this repository
2. use docker-compose up -d to install and run the project and it's required dependencies on docker environment
3. to see services and test them we can use swagger address : [HERE](localhost:8080/swagger-ui.html)

### how to use this project:
1. go to [swagger ui](localhost:8080/swagger-ui.html)
2. use signup service to create a new user.
3. use login service to get a new jwt token.
4. call ..... complete later

### Features
- database : postgres
- logging : used logback and aspect component(ControllerLogger) to log start and end of Apis.
- validations : java validation

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