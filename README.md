# rabobank
Rabobank Customer Statement Processor
Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.


There are two validations:
	•	all transaction references should be unique 
	•	the end balance needs to be validated 
At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

Steps to execute:
1. Import the project as maven project in Eclipse IDE
2. Maven clean install
3. Maven run
4. Access the REST API using Advanced REST client
  Request URL:
  http://localhost:8080/rabobank/generateReport
  
  Method:
  POST
  
  Headers:
  Header name: Content-Type
  Header value: multipart/form-data
  
  Body:
  Choose the Rabobank customer files of type text/csv or text/xml.
  
The format of the file is a simplified format of the MT940 format. The format is as follows:

Transaction reference : A numeric value
Account number :  An IBAN
Start Balance  :  The starting balance in Euros
Mutation       :  Either and addition (+) or a deducation (-)
Description    :  Free text
End Balance    :  The end balance in Euros
  
  
  
