import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.HarFileSystem;
import org.apache.hadoop.fs.Path;

public class HarMain {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");
		HarFileSystem fs = new HarFileSystem();
		fs.initialize(new URI("har:///hartest/ceshi.har"), conf);
		FileStatus[] listStatus = fs.listStatus(new Path("a1.txt"));
		for (FileStatus fileStatus : listStatus) {
			System.out.println(fileStatus.getPath().toString());
		}
		fs.close();
	}
}
