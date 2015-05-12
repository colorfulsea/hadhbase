

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileStatusMetadata {
	public static void main(String args[]) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", "hdfs://master:9000");// 如果不写就只能本地操作了
		conf.set("hadoop.job.ugi", "hadoop,hadoop");// 如果不写系统将按照默认的用户进行操作
		// 查看元数据：
		String uri = "hdfs://master:9000/b.txt";
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FileStatus fst = fs.getFileStatus(new Path(uri));
		if (!fst.isDir()) {
			System.out.println("这是个文件");
		}
		System.out.println("路径：" + fst.getPath());
		System.out.println("长度：" + fst.getLen());
		System.out.println("文件修改日期："
				+ new Timestamp(fst.getModificationTime()).toString());
		System.out.println("上次文件访问日期："
				+ new Timestamp(fst.getAccessTime()).toString());
		System.out.println("文件备份数：" + fst.getReplication());
		System.out.println("文件块大小：" + fst.getBlockSize());
		System.out.println("文件所有者：" + fst.getOwner());
		System.out.println("文件所在分组：" + fst.getGroup());
		System.out.println("文件权限：" + fst.getPermission().toString());
		fs.close();
	}
}
