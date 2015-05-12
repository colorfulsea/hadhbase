////import org.apache.hadoop.conf.Configuration;
////import org.apache.hadoop.fs.Path;
////import org.apache.hadoop.io.IntWritable;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//import org.apache.hadoop.util.ToolRunner;
//
//import java.io.IOException;
//
////import org.apache.hadoop.mapreduce.Job;
////import org.apache.hadoop.mapreduce.Mapper;
////import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
////import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
////import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//
///**
// * Created by taofengbing on 2015/5/6.
// */
//
//public class reckon implements rec {
//    enum Counter
//    {
//        LINESKIP;//出错的行
//    }
//    public static class Map
//            extends Mapper<Object, Text, IntWritable,Text > {
//
//        private final static IntWritable one = new IntWritable(3);
////        private Text word = new Text();
//
//        public void map(Object key, Text value, Context context
//        ) throws IOException, InterruptedException {
//
//            String line = value.toString();//读取源数据
//
//            //处理数据
//            try{
//            String[] lineSplit = line.split(" ");
//            String month = lineSplit[0];
//            String time = lineSplit[1];
//            String mac = lineSplit[2];
//            Text out = new Text(month+"::"+time+"::"+mac);
//
//
//                context.write(one,out);
//            }
//            catch (ArrayIndexOutOfBoundsException e) {
//                context.getCounter(Counter.LINESKIP).increment(1);
//                return;
//            }
//
//        }
//    }
//  @Override
//    public int run(String[] args) throws Exception {
//        Configuration conf = new Configuration();
//
//      Job job = new Job(conf,"reckon");  //任务名称
//        job.setJarByClass(reckon.class);   //制定Class
//
//        FileInputFormat.addInputPath(job, new Path(args[0])); //输入路径
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));//输出路径
//
//        job.setMapperClass(Map.class);   //调用上面的Map类作为Map任务代码
//        job.setOutputFormatClass(TextOutputFormat.class);
//        job.setOutputKeyClass(IntWritable.class);   //指定输出的ＫＥＹ的格式
//        job.setOutputValueClass(Text.class);        //指定输出的VALUE的格式
//
//        job.waitForCompletion(true);
//
//        return job.isSuccessful()?0:1;
//    }
//
//    public static void main(String[] args) {
//     int res = ToolRunner.run(new Configuration(),new reckon);
//        System.exit(res);
//    }
//
//}
///*
//出现的问题就是源码没有下载下来的真心讨厌
//*/