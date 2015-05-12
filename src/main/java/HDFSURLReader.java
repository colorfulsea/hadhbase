import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HDFSURLReader {
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String args[]) {
		InputStream is = null;
		try {
			is = new URL("hdfs://master:9000/b.txt").openStream();
			IOUtils.copyBytes(is, System.out, 1024, false);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				IOUtils.closeStream(is);
				is.close();
			} catch (IOException e1) {
				is=null;
				e1.printStackTrace();
			}
		}
	}
}
