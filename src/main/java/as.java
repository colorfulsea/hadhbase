//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
///**
//* Created by taofengbing on 2015/5/7.
//*/
//public class as {
//
//        enum Counter
//        {
//            LINESKIP;//添加计数器
//        }
//        public static class TokenizerMapper
//                extends Mapper<Object, Text, Text, IntWritable> {
//
//            private final static IntWritable one = new IntWritable(3);
//            private Text word = new Text();
//
//            public void map(Object key, Text value, Context context
//            ) throws IOException, InterruptedException {
//                StringTokenizer itr = new StringTokenizer(value.toString());
//                while (itr.hasMoreTokens()) {
//                    word.set(itr.nextToken());
//                    context.write(word, one);
//                }
//            }
////            public void map(Object key,Text value,Context context) throws IOException, InterruptedException {
////                String line = value.toString();//读取源数据
////
////                //处理数据
////                try{
////                    String[] lineSplit = line.split(" ");
////                    String month = lineSplit[0];
////                    String time = lineSplit[1];
////                    String mac = lineSplit[2];
////                    Text out = new Text(month+" "+time+" "+mac);
////
////
////                    context.write(out, one);
////                }
////                catch (ArrayIndexOutOfBoundsException e) {
////                    context.getCounter(Counter.LINESKIP).increment(1);
////                    return;
////                }
////
////            }
//        }
//
//
//    public static class IntSumReducer
//            extends Reducer<Text, IntWritable, Text, IntWritable> {
//        private IntWritable result = new IntWritable();
//
//        public void reduce(Text key, Iterable<IntWritable> values,
//                           Context context
//        ) throws IOException, InterruptedException {
//            int sum = 0;
//            for (IntWritable val : values) {
//                sum += val.get();
//            }
//            result.toString();
//            context.write(key, result);
//            context.getCurrentKey();
//        }
//
//    }
//
//
//
//    public static void main(String[] args) throws Exception {
//        Configuration conf = new Configuration();
//
//
//        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//        if (otherArgs.length != 2) {
//            System.err.println("Usage: wordcount <in> <out>");
//            System.exit(2);
//        }
//        Job job = new Job(conf, "aa");
//        System.out.println(job);
//        job.setJarByClass(as.class);
//        job.setMapperClass(as.TokenizerMapper.class);//在此处做过改动的  一些小细节
//        job.setCombinerClass(IntSumReducer.class);
//        job.setReducerClass(IntSumReducer.class);
//        job.setOutputKeyClass(Text.class);    //指定输出的KEY的
//        job.setOutputValueClass(IntWritable.class);
//        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//    }
//}
//
//
