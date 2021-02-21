# Setting up DynamoDB locally (Windows)

1. Download and install Dynamo DB server:
    Download from:   
    https://s3.ap-south-1.amazonaws.com/dynamodb-local-mumbai/dynamodb_local_latest.zip  
  Un-compress the zip and put in a folder, say `c:/dynamodb`
  
2. Start DynamoDB Server: In the command prompt window, change the working directory to the dynamoDB directory and issue following command:  
    `C:/dynamodb>java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb`  
    Do not close this console window. Closing this window will shutdown dynamoDB server.
  
3. Download AWS CLI from following, and
    https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html
    Run its installation. Installation program does all necessary setting, and it should be ready to be used.
  
4. Configure Dynamo DB. Open another command window and submit command
  It prompts for some inputs. Give some inputs as following
```
C:/dynamodb>aws configure
AWS Access Key ID [None]: 1234
AWS Secret Access Key [None]: 1234
Default region name [None]: ap-south-1
Default output format [None]: json
```
  May preserve this inputs for some later use; we may never need these values, though.  
  
## Test if set up is done correctly:  
  
Create a work directory, say `c:\dynamodb-work`. Move to this directory before doing following tasks  
  
Create Table (named as `ProductCatalog`) as following:
```
aws dynamodb create-table ^
--table-name ProductCatalog ^
--attribute-definitions ^
AttributeName=Id,AttributeType=N ^
--key-schema ^
AttributeName=Id,KeyType=HASH ^
--provisioned-throughput ^
   	ReadCapacityUnits=10,WriteCapacityUnits=5 ^
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
Describe the Table
```
$aws dynamodb describe-table ^
  --table-name ProductCatalog ^
	--endpoint-url http://localhost:8000
```
List tables in your AmazonDB database:
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
### Done!
