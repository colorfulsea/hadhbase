

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileStatusMetadata2 {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		// 查看HDFS文件的元信息：
		String uri = "hdfs://master:9000/ceshi";
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FileStatus fst = fs.getFileStatus(new Path(uri));
		if (fst.isDir()) {
			System.out.println("这是个目录");
		}
		System.out.println("目录路径：" + fst.getPath());
		System.out.println("目录长度：" + fst.getLen());
		System.out.println("目录修改日期："
				+ new Timestamp(fst.getModificationTime()).toString());
		System.out.println("上次目录访问日期："
				+ new Timestamp(fst.getAccessTime()).toString());
		System.out.println("目录备份数：" + fst.getReplication());
		System.out.println("目录块大小：" + fst.getBlockSize());
		System.out.println("目录所有者：" + fst.getOwner());
		System.out.println("目录所在分组：" + fst.getGroup());
		System.out.println("目录权限：" + fst.getPermission().toString());
		for (FileStatus a : fs.listStatus(new Path(uri))) {
			System.out.println(a.getPath());
		}
		fs.close();
	}
}
