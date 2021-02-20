### (1) Create a Table
```
aws dynamodb create-table ^
	--endpoint-url http://localhost:8000 ^
	--table-name Books ^
	--attribute-definitions ^
		AttributeName=publisher,AttributeType=S ^
		AttributeName=title,AttributeType=S ^
		AttributeName=isbn,AttributeType=S ^
	--key-schema ^
		AttributeName=publisher,KeyType=HASH ^
		AttributeName=title,KeyType=RANGE ^
	--provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5 ^
	--global-secondary-indexes file://book_gsi.json

	On success of command, you should see the description of table.

	Reference: reference for "create-table" with examples
	https://docs.aws.amazon.com/cli/latest/reference/dynamodb/create-table.html
```
### (2) Describe Table
```
aws dynamodb describe-table ^
	--table-name Books ^
	--endpoint-url http://localhost:8000
```
### (3) List All Tables
```
aws dynamodb list-tables ^
	--endpoint-url http://localhost:8000
```
### (4) Put a data Object into Books table
(Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/put-item.html)
```
aws dynamodb put-item ^
	--endpoint-url http://localhost:8000 ^
	--table-name Books ^
	--item file://book1.json ^
	--return-values ALL_OLD
```
### (5) Put multiple data Object into Books table
Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/batch-write-item.html
```
aws dynamodb batch-write-item ^
	--endpoint-url http://localhost:8000 ^
	--request-items file://books.json
```
### (6) Get book by Key
(Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/get-item.html)
```
aws dynamodb get-item ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --key file://keybook.json
```
### (7) Get Multiple books by Key
(Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/batch-get-item.html)
```
aws dynamodb batch-get-item ^
	--endpoint-url http://localhost:8000 ^
    --request-items file://reqitems1.json 
```
## "QUERY" a table
(Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/query.html)

### (8) Querying on Partition Key
```
aws dynamodb query ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --key-condition-expression "publisher = :pub" ^
	--filter-expression "pages >= :pages" ^	
    --expression-attribute-values file://pub1.json
```

### (9) Querying on Partition Key - Projection
```
aws dynamodb query ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
	--projection-expression "isbn, title, publisher" ^
    --key-condition-expression "publisher = :pub" ^
    --expression-attribute-values file://pub1.json
```
### (10) Querying on Partition Key - COUNT
```
aws dynamodb query ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --select COUNT ^
    --key-condition-expression "publisher = :pub" ^
    --expression-attribute-values file://pub1.json
```
### (11) Querying on Partition Key - FILTER
```
aws dynamodb query ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --key-condition-expression "publisher = :pub" ^
	--filter-expression "pages >= :pages" ^
    --expression-attribute-values file://values2.json
```
### (12) Querying with Index
```
aws dynamodb query ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
	--index-name isbnIndex ^
	--key-condition-expression "isbn=:isbn" ^
    --expression-attribute-values file://values3.json ^
	--projection-expression "isbn, title, publisher"
```

## "SCAN" a table
(Reference: https://docs.aws.amazon.com/cli/latest/reference/dynamodb/scan.html)

### (13) List all Books
```
aws dynamodb scan ^
	--endpoint-url http://localhost:8000 ^
	--output text ^
	--table-name Books
```
### (14) Count Number of Items
```
aws dynamodb scan ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --select COUNT
```
### (15) List all Books - Projection and Selection applied
```
aws dynamodb scan ^
	--endpoint-url http://localhost:8000 ^
	--output text ^
	--projection-expression "title, publisher" ^
	--table-name Books
```
### (16) Update an Item
```
aws dynamodb update-item ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books ^
    --key file://keybook.json ^
    --update-expression "SET website = :website, published = :published" ^
    --expression-attribute-values file://bookupdate_values.json  ^
    --return-values ALL_NEW 
```
### (17) Drop a Table
```
aws dynamodb delete-table ^
	--endpoint-url http://localhost:8000 ^
    --table-name Books1
```
