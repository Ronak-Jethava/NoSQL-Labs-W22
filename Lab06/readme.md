# Lab06: Working with DynamoDB

In this lab, we get acquaintance with Dynamo DB and do some hands on working with.

## Do following:

1. Download and setup Dynamo DB and AWS CLI client on your machine as per instructions in given here:  
Windows: [setup-dynamodb-windows](./docs/setup-windows.md)  
Linux: [setup-dynamodb-linux](./docs/setup-linux.md)
1. Perform some data definition and manipulation operations on dynamo db database using AWS CLI. 
[Scripts](./scripts/book-repository.md)

1. [optional] You may also like to explore “NoSQL Workbench for DynamoDB”, a GUI work around Dynamo DB databases. Particularly “Data Visualization” and “Operation Building” parts of it. 
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/workbench.html 
[Note: Amazon is probably extending this to connect with other NoSQL databases for instance Cassandra]

1. Run few operations in a host programming Language. 
    * Java [Here is how you access dynamo db tables in Java](./docs/java-access.md)
        * DynamoDBLowerLevelDemo.java
        * CatalogMain.java (requires CatalogItem.java)
    * Python. Requires Installing Boto3 (AWS SDK for Python). Try executing the example program from  
https://github.com/sanket143/DynamoDB-Lab/wiki/DynamoDB-Reference-Codes, or
https://docs.aws.amazon.com/code-samples/latest/catalog/code-catalog-python-example_code-dynamodb-GettingStarted.html 
