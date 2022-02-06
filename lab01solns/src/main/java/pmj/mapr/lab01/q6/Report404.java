package pmj.mapr.lab01.q6;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.fs.Path;

public class Report404 {

	public static void main(String[] args) throws Exception,IOException {
		
		Job job = Job.getInstance();
		job.setJarByClass(Report404.class);
		job.setJobName("Monthly Summary");

		String input = "E:/programs-nosql-22/data/mr/webaccess/access_log.txt";
		String output = "E:/programs-nosql-22/output/mr/lab02/q6";
		

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
	 
		job.setMapperClass(Report404Mapper.class);
		job.setNumReduceTasks(0);
	
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
										
		System.exit(job.waitForCompletion(true)?0:1);		
		
	}

}
