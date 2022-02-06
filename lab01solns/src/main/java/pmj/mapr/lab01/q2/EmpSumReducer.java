package pmj.mapr.lab01.q2;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;

public class EmpSumReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		double max = 0;
		for (IntWritable value : values) 
			if (max < value.get() )
				max = value.get();
		context.write(key, new DoubleWritable(max));
	}
}