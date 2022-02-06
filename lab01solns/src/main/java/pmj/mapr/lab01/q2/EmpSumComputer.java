package pmj.mapr.lab01.q2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EmpSumComputer {

	public static void main(String[] args) throws Exception {
	 
		Job job = Job.getInstance();
		job.setJarByClass(EmpSumComputer.class);
		job.setJobName("Average Salary");
		
		String input = "E:/programs-nosql-22/data/mr/employee1/employee.csv";
		String output = "E:/programs-nosql-22/output/mr/lab02/q2";
		
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
	 
		job.setMapperClass(EmpSumMapper.class);
		job.setReducerClass(EmpSumReducer.class);
	
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
	 
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}