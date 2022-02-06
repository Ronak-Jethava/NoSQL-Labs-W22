package pmj.mapr.lab01.q2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmpSumMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context)
	   throws IOException, InterruptedException {
		//emp-no,name, dno, salary, state, gender
		String line = value.toString();
		String[] record = line.split(",");
		String dep = record[2];
		int salary = Integer.parseInt( record[3]);
		String state = record[4];
		if ( state.equals("MA") )
			context.write(new Text(dep), new IntWritable( salary) );
	}

}
