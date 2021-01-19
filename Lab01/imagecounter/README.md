## Map Reduce Program to process "web access log" file ##

### input data file <web_access_log.txt> ###

Web servers generate the access log file that can be used for generating statistiscal information about the web site acces.  
You can understand about the content of web access log file from  
https://httpd.apache.org/docs/1.3/logs.html#common

### Program Functionality: ###
The program counts and outputs the number of times GIF, JPG, and other image files that have been accessed by clients.  

### Source Files ###
contributed by Amit Agrwal, 201701060  

* ImageCounter.java //driver program
* ImageCounterMapper.java //mapper 
* ImageCounterReducer.java //reducer
