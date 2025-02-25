package pmj.spark.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class WordCounter {

	public static void main(String[] args) throws Exception {

	    SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount")
	    		.setMaster("local[*]");
	    JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		String inputFile = "data/article1.txt";
	    JavaRDD<String> lines = ctx.textFile(inputFile, 1);

	    JavaRDD<String> words 
	    	= lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
	    JavaPairRDD<String, Integer> ones 
	      = words.mapToPair(word -> new Tuple2<>(word, 1));
	    JavaPairRDD<String, Integer> counts 
	      = ones.reduceByKey((Integer i1, Integer i2) -> i1 + i2);

	    List<Tuple2<String, Integer>> output = counts.collect();
	    for (Tuple2<?, ?> tuple : output) {
	        System.out.println(tuple._1() + ": " + tuple._2());
	    }
	    ctx.stop();
	    ctx.close();
	}	
	
}
