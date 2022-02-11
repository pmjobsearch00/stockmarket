
## Super Simple Stock Market 

** Requirements **
The Global Beverage Corporation Exchange is a new stock market trading in drinks companies. 

Your company is building the object-oriented system to run that trading.  

You have been assigned to build part of the core object model for a limited phase 1 

Provide the complete source code for below functions.

For a given stock,  

Given any price as input, calculate the dividend yield 

Given any price as input,  calculate the P/E Ratio 

Record a trade, with timestamp, quantity, buy or sell indicator and price 

Calculate Volume Weighted Stock Price based on trades in past  5 minutes 

Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks 


** Constraints & Notes  **

Written in one of these languages - Java, C#, C++, Python 

The source code should be suitable for forming part of the object model of a production application, and can be proven to meet the requirements. A shell script is not an appropriate submission for this assignment.  

No database, GUI or I/O is required, all data need only be held in memory 

No prior knowledge of stock markets or trading is required – all formulas are provided below. 

The code should provide only the functionality requested, however it must be production quality. 

** Table1. Sample data from the Global Beverage Corporation Exchange **

Stock Symbol<=========>Type<======>Last Dividend<======>Fixed Dividend<============>Par Value

TEA<===============>Common<======>0<================>NA<================>100 

POP<===============>Common<======>8<================>NA<================>100 

ALE<===============>Common<======>23<================>NA<================>60

GIN<===============>Preferred<======>8<================>2%<=================>100

JOE<===============>Common<======>13<================>NA<================>250


** Table 2 Formula **

Dividend Yield (for Common stock) = (Last Dividend)/Price  	

Dividend Yield (for Preferred stock) = (Fixed Dividend)/ParValue

P/E Ratio	= Price/Dividend

Geometric Mean = √n (p1 p2 p3… pn)

Volume Weighted Stock Price = 	∑ (Traded Price×Quantity)/∑Quantity


** Testing **

If you have git installed and configured in the path then use below command to checkout source code into your system,

git clone https://pmjobsearch00@bitbucket.org/pmjobsearch00/stockmarket.git

If you have maven installed and in the path then use below command to build and run junit test cases that proves the core functions,

Go to the folder where the code is checked-out (you should see the pom.xml in your current folder)

mvn clean install

mvn test

** Build **

mvn clean install


