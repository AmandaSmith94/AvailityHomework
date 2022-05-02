# Availity Homework Assignment
### 1. Tell me about your proudest professional achievement.  It can also be a personal or school project.
My proudest professional achievement was when I was handed a microservice to work on from beginning to end.  I worked on the POC and followed the project to PROD.  This microservice in specific waited for a kafka topic, grabbed the message from that topic, parsed the xml that was handed to us, made some rest calls, and then used a WSDL and a soap call to send off the new message.  In grandmothers terms, when someone wanted a form signed electronically, we would get the information needed to print the form with the signature spaces and email that form out to agents and insureds.  We were a small part of that service, but a necessary part to get the email and forms formatted.  I learned a lot with this project as I started with a mess of a singleton class and broke it out into multiple POJOs as time went on.  This project was a turning point in my career because it also helped me learn that it is okay to ask for help if you are banging your head against a wall.  There are people on your team as a resource, not just to do their own work.


### 2. Tell me about something you have read recently that you would recommend and why. (Can be a Github Repo, Article, Blog, Book, etc)
Something that I read recently is The Good Earth by Pearl S. Buck.  I recommend this book because it is about how money and fortune can get in the way of humble beginnings, and how those beginnings are lost as generations proceed them.  This book tells this lesson by following a farmer from owning his first land plot to owning multiple land plots and a villa.  He gets lost in the fame and fortune and his children do not understand the importance of the land by the time he is of old age.  As a parent, I work hard to make sure my kids have it better.  This book reminds me that my children should also understand humble beginnings and hard work, or they might lose it all without even realizing that is what they are doing.  This book also reminds me that we are in a tech heavy world and it is important to remember and recognize where our food comes from.  There is a lot to digest in this book, and I suspect you can learn new lessons in different walks of life as you reread the book down the line.

### 3. How would you explain to your grandmother what Availity does?
Availity helps doctors and insurance companies communicate patients charts and needs.  It makes talking to the insurance companies easier by putting all the patients in one space, no matter what their insurance company is.  Availity takes the guessing work out of what the insurance company needs from the provider by having a contract ready to go with the information that needs to be collected.

### 4. Coding exercise: You are tasked to write a checker that validates the parentheses of a LISP code.  Write a program (in Java or JavaScript) which takes in a string as an input and returns true if all the parentheses in the string are properly closed and nested.
Please see src/lisp/ParenMatcher.java main method.

### 5. skipped

### 6. Coding exercise:  Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms).  Most of these files are typically in EDI format.  However, there are some files in CSV format.  For the files in CSV format, write a program in a language that seems appropriate to you that will read the content of the file and separate enrollees by insurance company in its own file. Additionally, sort the contents of each file by last and first name (ascending).  Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. The following data points are included in the file:
* User Id (string)
* First and Last Name (string)
* Version (integer)
* Insurance Company (string)

Please see src/csv/CSVReader.java main method. Uses Enrollee class.