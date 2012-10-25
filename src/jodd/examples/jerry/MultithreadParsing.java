package jodd.examples.jerry;

import jodd.datetime.JStopWatch;
import jodd.exception.UncheckedException;
import jodd.io.FileUtil;
import jodd.io.NetUtil;
import jodd.jerry.Jerry;
import jodd.util.SystemUtil;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import java.io.File;
import java.nio.CharBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadParsing {

	public static final String[] URLS = {
			"http://jodd.org",
			"http://bbc.org",
			"http://lifehacker.com",
			"http://stackoverflow.com/",
			"http://google.com",
			"http://yahoo.com",
			"http://facebook.com",
			"http://cnn.com",
			"http://grooveshark.com"
	};

	/**
	 * 1 thread - 14.696  (2.41)
	 * 2 threads - 9.365  (1.41)
	 */
	public static void main(String[] args) throws Exception {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);
		root.detachAndStopAllAppenders();

		String tempFolder = SystemUtil.getTempDir();

		File[] files = new File[URLS.length];

		for (int i = 0, urlsLength = URLS.length; i < urlsLength; i++) {
			String url = URLS[i];

			File file = files[i] = new File(tempFolder, "file-" + i + ".html");

			if (file.exists() == false) {
				NetUtil.downloadFile(url, file);
			}

			System.out.println(url);
		}

		int maxThreads = Runtime.getRuntime().availableProcessors();

		ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);

		JStopWatch jsw = new JStopWatch();

		for (final File file : files) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						processFile(file);
					} catch (Exception e) {
						throw new UncheckedException(e);
					}
				}
			});
		}

		executorService.shutdown();

		executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

		jsw.stop();

		System.out.println(jsw);
	}

	static void processFile(File file) throws Exception {
		System.out.println("processing: " + file.getName());

		String fileContent = FileUtil.readString(file);

		Jerry.JerryParser jerryParser = new Jerry.JerryParser();

//		jerryParser.getDOMBuilder().setCalculatePosition(true);

		Jerry jerry = jerryParser.parse(CharBuffer.wrap(fileContent));

	}

}
