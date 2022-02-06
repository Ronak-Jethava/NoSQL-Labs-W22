package pmj.mapr.lab01.q5;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MonthlyReportReducer extends Reducer<Text,Text,Text,Text> {
	public void reduce(Text key, Iterable<Text> values, Context c) 
			throws IOException, InterruptedException {
		int n = 0;
		long ssum = 0;
		for(Text value : values) {
			String[] rec = value.toString().split(",");
			n += Integer.parseInt(rec[0]);
			ssum += Integer.parseInt(rec[1]);
		}
		c.write(key, new Text(""+n+", "+ssum/(1024*1024) + " MB"));
	}
}