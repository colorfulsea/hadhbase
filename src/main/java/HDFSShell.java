import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSShell {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
			fs.copyFromLocalFile(new Path("/home/hadoop/derby.log"), new Path(
					"hdfs://master:9000/b1.txt"));// mkdirs等hdfs操作方法
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				fs.close();
			} catch (IOException e1) {
				fs=null;
				e1.printStackTrace();
			}
		}

	}

}
