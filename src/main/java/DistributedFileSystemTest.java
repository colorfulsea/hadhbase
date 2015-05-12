

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;
import java.net.URI;

public class DistributedFileSystemTest {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		String uriin = "hdfs://master:9000/b.txt";
		FileSystem fs = FileSystem.get(URI.create(uriin), conf);
		try {
			DistributedFileSystem hdfs = (DistributedFileSystem) fs;
			DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
			for (int i = 0; i < dataNodeStats.length; i++) {
				System.out.println("DataNode_" + i + "_Name:"
						+ dataNodeStats[i].getHostName());
			}
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
