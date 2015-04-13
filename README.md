# ace-scrape

A project to provide the rural commodity exchange market data and contracts data from [Ace Africa](http://aceafrica.org) via a RESTful API and consume the API from an AngularJS client

The API uses JSON for exchanging the market data.

>NB: The data from the website is obtained by [web scraping](https://en.wikipedia.org/wiki/Web_scraping) - which should be okay, since the data is in the public domain according to info on [this page](http://aceafrica.org/about-ace.aspxhttp://aceafrica.org/about-ace.aspx)

>[quote] "ACE is generating real time market information from these bids/offers and contracts generated. This information is public and available to all.." [/quote]


**There are no pre-built binaries so you have to build it yourself ;)**

## Prerequisites:
Stuff you need to have installed for this to work
* Maven 3
* Java 8
* Node.js (for running Apidoc and Bower)

## Building / Running

Install requirements, clone this repository locally, generate the api doc and execute the maven project. 

Install apidoc, bower
```
$ cd ace-scrape
$ npm install -g apidoc bower
```
Clone this repo
```
$ git clone https://github.com/zikani03/ace-scrape.git
```
Generate the apidoc for the resources
```
$ apidoc --input ./src/main/java --output ./src/main/resources/webapp/apidoc -f .java
```

Run the maven project
```
$ mvn clean compile exec:exec
```

After the last command the app should be running at `http://localhost:4567/index.html` and the apidoc available at `http://localhost:4567/apidoc`

## API Resources

MarketsCollection - `curl -iG http://localhost:4567/markets`   
A specific Market - `curl -iG http://localhost:4567/markets/:marketName`

ContractsCollection - `curl -iG http://localhost:4567/contracts`   
A single Contract - `curl -iG http://localhost:4567/contracts/:year/:id`

The current format (as at 2015-04-12) for the contract reference is year/id (e.g. 2014/10)

## TODO

* Retrieve commodities available at each rural market
* Integrate google maps to show District where market is located
* Visualizations of contracts via D3.js 

## Tech

* [Angular JS 1.x](http://angularjs.org) - You know, the super heroic framework 
* [Spark Framework 2.0.0](http://sparkjava.com) - Awesome Java 8 micro-framework for building APIs in Java
* [Jsoup](http://jsoup.org) for web scraping using a jQuery inspired API - it's awesome

## Contributions
Contributions are more than welcome! Just send a PR, really just want to learn :)
