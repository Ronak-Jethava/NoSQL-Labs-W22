## First Map Reduce Program ##
### Compute Maximum Temperature of NCDC weather data! ###
sourced from code of the book: The Definitive Guide, Fourth Edition by Tom White  
https://github.com/tomwhite/hadoop-book

### The Program works as following: ###
* Computes maximum temperature from NCDC weather data. The data file contains weather data for two years.
* It outputs max temperature for each year.

### Source Files ###
* MaxTemperature.java
** Driver program - instantiates, provides required parameters, and submits Map Reduce Job
* MaxTemperatureMapper.java //Mapper class
* MaxTemperatureReducer.java //Reducer class

### input data files ###
combines two year's data from  
1901: https://github.com/tomwhite/hadoop-book/blob/master/input/ncdc/all/1901.gz  
1902: https://github.com/tomwhite/hadoop-book/blob/master/input/ncdc/all/1902.gz  
