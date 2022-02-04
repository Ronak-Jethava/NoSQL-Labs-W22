package pmj.spark.weblog;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class ImageCounter {
	
	private static HashSet<String> IMAGES = new HashSet<String>(new ArrayList<String>(Arrays.asList("jpeg","jpg","png","jif","jfif","jfi","gif","webp","tiff","tif","psd","raw","arw","cr2","nrw","k25","bmp","dib","heif","heic","ind","indd","indt","jp2","j2k","jpx","jpm","mj2")));
	private static HashSet<String> JPGs = new HashSet<String>(new ArrayList<String>(Arrays.asList("jpeg","jpg","jif","jfif","jfi")));	
	
	public static void main(String[] args) {
		
        SparkConf conf = new SparkConf().setAppName("ImageCounter")
        		.setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> requests = sc.textFile("data/access_log.txt");
        
	    //Define filter function that keeps requests that are for images
        Function<String, Boolean> imgFilter = new Function<String, Boolean>() {
	    	@Override
	        public Boolean call(String request) throws Exception {
	    		String url= request.split(" ")[6];
	    		String method= request.split(" ")[5];
	    		int pos = url.lastIndexOf(".");	    		
	    		if(pos != -1 ) {
	    			String extension=url.substring(pos+1);
	    			if(ImageCounter.IMAGES.contains(extension) && method.equals("\"GET"))
	    				return Boolean.TRUE;
	    		}
	    		return Boolean.FALSE;
	    	}
	    };	    

	    //Apply Filter as per the above function
	    JavaRDD<String> imgRequests = requests.filter( imgFilter );
	        
	    //Perform MAP as per the defined in line anonymous function
	    JavaPairRDD<String, Integer> images = imgRequests.mapToPair( 
	    		new PairFunction<String, String, Integer>() {
	    	@Override
	        public Tuple2<String, Integer> call(String request) throws Exception {
	    		String url= request.split(" ")[6];
	    		int pos = url.lastIndexOf(".");	    		
    			String extension=url.substring(pos+1);
    			
				if(extension.equals("gif"))
					return new Tuple2<String, Integer>("GIF Images",1);
				else if(ImageCounter.JPGs.contains(extension))
					return new Tuple2<String, Integer>("JPG Images",1);
				else
					return new Tuple2<String, Integer>("Other Images",1);
    			    			
	    	}
	    });
	    
	    //output map output 
	    List<Tuple2<String, Integer>> map1 = images.collect();
        map1.forEach( tuple -> System.out.println(tuple._1 + ": " + tuple._2));

	    //Apply Reduce Function
	    JavaPairRDD<String, Integer> counts = images.reduceByKey((x, y) -> x + y);	    

	    //collect and output on console
	    List<Tuple2<String, Integer>> output = counts.collect();
        output.forEach( tuple -> System.out.println(tuple._1 + ": " + tuple._2));
	    
	    sc.stop();        
        sc.close();        
		
	}
}
