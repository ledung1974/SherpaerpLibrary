# Backend Developer Trial

We're building the start of a simple library application. We're going to start with two entities, books and borrowers.

Design and build a microservice stack using Java Spring that shows a list of books and, for each book or borrower, can fetch a list of borrowers that have previously borrowed that book.

### Entities

A book consists of the following properties:

```json
{
	"id": Number,
	"title": String,
	"author": {
		"firstName": String,
		"lastName": String
	},
	"genres": [
		{
			"name": String
		}
	]
	"pageCount": Number
}
```

A borrower consists of the following properties:

```json
{
	"id": Number,
	"firstName": String,
	"lastName": String,
	"borrowingLimit": Number
}
```

### Operations

The application should support the following operations:

* Get a list of books
* Get an individual book
* Add a book
* Get a list of borrowers
* Get an individual borrower
* Add a borrower
* Create a loan record for a given book/borrower
* Get a list of past borrowers for a book
* Get a list of past books borrowed by a given borrower

### Deliverables

Archive file (zip, tar, etc) containing the source code and instructions to compile and run.