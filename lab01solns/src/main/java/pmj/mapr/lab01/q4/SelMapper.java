package pmj.mapr.lab01.q4;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context)
	   throws IOException, InterruptedException {
		//emp-no,name, dno, salary, state, gender
		String line = value.toString();
		String[] record = line.split(",");
		String eno = record[0];
		String name = record[1];
		int dno = Integer.parseInt(record[2]);	
		int salary = Integer.parseInt( record[3]);
		String out = eno + ", " + name + ", " + salary;
		if ( dno == 5 && salary > 100000 )
			context.write(new Text(eno), new Text( out ) );
	}

}
