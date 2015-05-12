import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by taofengbing on 2015/5/8.
 */
public class Want {
    public static class MP extends Mapper<Object, Text, Text, IntWritable>{
                  //集成Mapper（org.apache.hadoop.MapReduce下面的接口实现）
        private final static IntWritable one = new IntWritable(3);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }
    public static class RD extends Reducer<Text,IntWritable,Text,IntWritable>{
        private  IntWritable result = new IntWritable();
        public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        int su = 0;
            for (IntWritable val:values){
                su += val.get();
            }
            result.set(su);
            context.write(key,result);
        }

    }

org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();

//    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//    if (otherArgs.length != 2) {
//        System.err.println("Usage: wordcount <in> <out>");
//        System.exit(2);
//    }
//    Job job = new Job(conf, "aa ");
//    System.out.println(job);
//    job.setJarByClass(aa.class);
//    job.setMapperClass(TokenizerMapper.class);
//    job.setCombinerClass(IntSumReducer.class);
//    job.setReducerClass(IntSumReducer.class);
//    job.setOutputKeyClass(Text.class);    //指定输出的KEY的
//    job.setOutputValueClass(IntWritable.class);
//    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//    System.exit(job.waitForCompletion(true) ? 0 : 1);



    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("high");
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }
        Job job = new Job(conf,"want");
        System.out.println(job);
        job.setJarByClass(Want.class);
        job.setMapperClass(MP.class);
        job.setCombinerClass(RD.class);
        job.setReducerClass(RD.class);
        job.setOutputKeyClass(Text.class);               //指定输出的key类
        job.setOutputValueClass(IntWritable.class);      //指定输出类value来源
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ?0:1);
    }
}
