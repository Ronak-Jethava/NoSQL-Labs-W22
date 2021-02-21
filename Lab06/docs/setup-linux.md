# Setting up DynamoDB locally (Linux)

### prerequisite:  
Java must be installed on your machine.
If you don’t have Java installed on your machine you will need to run following commands to install java.
```
$ sudo apt update
$ sudo apt install default-jdk
$ update-alternatives `--config java`  
$ sudo gedit /etc/environment
```
Last command will open the text file. Now, copy the following code and paste into it then save the file.
```
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/bin/java"
PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:$JAVA_HOME/bin"
```
### (1) Download and install Dynamo DB server:
```
$ mkdir dynamodb
$ cd dynamodb
$ wget http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_latest.tar.gz  
$ tar xzf dynamodb_local_latest.tar.gz
```
### (2) Start DynamoDB Server: 
  
In the command prompt window, change the working directory to the dynamoDB directory and issue following command:
```
$ java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```
> Do not close this console window. Closing this window will shut down the dynamoDB server.
  
### (3) Download AWS CLI
```
$ curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64-2.0.30.zip" -o "awscliv2.zip"
$ unzip awscliv2.zip
$ sudo ./aws/install
```
### (4) Configure Dynamo DB. 
```
C:/dynamodb>aws configure
```
It prompts for some inputs. Give some inputs as following
```
AWS Access Key ID [None]: 1234
AWS Secret Access Key [None]: 1234
Default region name [None]: ap-south-1
Default output format [None]: json
```
May preserve this inputs for some later use; we may never need these values, though.  
  
## Test if set up is done correctly:  
  
Create a work directory, say `c:\dynamodb-work`. Move to this directory before doing following tasks  
  
**Create Table** (named as `ProductCatalog`) as following:
```
$ aws dynamodb create-table \
  --table-name ProductCatalog \
  --attribute-definitions \
    AttributeName=Id,AttributeType=N \
  --key-schema \
    AttributeName=Id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 \
  --endpoint-url http://localhost:8000
```
If everything is successful, you should see following as a response.
```
{
	"Table": {
    	"AttributeDefinitions": [
        	{
            	"AttributeName": "Id",
            	"AttributeType": "N"
        	}
    	],
    	"TableName": "ProductCatalog",
    	"KeySchema": [
        	{
            	"AttributeName": "Id",
            	"KeyType": "HASH"
        	}
    	],
    	"TableStatus": "ACTIVE",
    	"CreationDateTime": "2021-02-16T00:16:00.227000+05:30",
    	"ProvisionedThroughput": {
        	"LastIncreaseDateTime": "1970-01-01T05:30:00+05:30",
        	"LastDecreaseDateTime": "1970-01-01T05:30:00+05:30",
        	"NumberOfDecreasesToday": 0,
        	"ReadCapacityUnits": 10,
        	"WriteCapacityUnits": 5
    	},
    	"TableSizeBytes": 0,
    	"ItemCount": 0,
    	"TableArn": "arn:aws:dynamodb:ddblocal:000000000000:table/ProductCatalog"
	}
}
```
**Describe Table**
```
$aws dynamodb describe-table ^
  --table-name ProductCatalog ^
	--endpoint-url http://localhost:8000
```
**List tables** in your AmazonDB database:
```
$aws dynamodb list-tables --endpoint-url http://localhost:8000
```
Response should be as following
```
{
    "TableNames": [
       " ProductCatalog"
    ]
}
```
## Done!
