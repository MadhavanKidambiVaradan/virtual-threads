# Virtual-threads
To study and understand the capability of virtual threads

## Contents
* Two spring boot applications - one acts as Native Server and another acts as Gateway.
<br> (Both have been committed using Virtual Threads but easy to comment out to disable Virtual Threads)

   * gateway-app
   * nativeserver-app

* VirtualThreads.jmx file contains the Jmeter file used for running the load tests against these 2 spring microservices. 

## Purpose
Using these 2 spring boot services to compare the performance improvements in Virtual Threads by enabling/disabling the Virtual Threads usage. 
