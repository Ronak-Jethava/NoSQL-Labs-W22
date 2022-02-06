package pmj.mapr.lab01.q6;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Report404Mapper extends Mapper<LongWritable,Text,Text,Text> {

	public void map(LongWritable key, Text val, Context c) 
			throws IOException, InterruptedException {		
		String url= val.toString().split(" ")[6];
		String status= val.toString().split(" ")[8];
		if(status.equals("404")) {
			String ts = val.toString().split(" ")[3];
			String out = url + " " + ts.substring(1);
			c.write(null, new Text( out ) );
		}
	}
}