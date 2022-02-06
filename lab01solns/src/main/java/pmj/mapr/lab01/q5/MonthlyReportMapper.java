package pmj.mapr.lab01.q5;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MonthlyReportMapper extends Mapper<LongWritable,Text,Text,Text> {

	public void map(LongWritable key, Text val, Context c) 
			throws IOException, InterruptedException {		
		String ts = val.toString().split(" ")[3];
		String month= ts.substring(4,12);
		int k=month.lastIndexOf("/");
		month=month.substring(0,k)+"-"+month.substring(k+1);
		String ssize =val.toString().split(" ")[9];
		int size=0;
		if(!ssize.equals("-")) {
			size=Integer.parseInt(ssize);
		}
		String out = "1," + size;
		c.write(new Text(month), new Text( out ) );
	}
}