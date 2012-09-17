package net.hisme.masaki.mona;

import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class MonaUtils {
  public static ThreadList getThreadList(Board board) {
    try {
      HttpURLConnection http = (HttpURLConnection) board.getUrl().openConnection();
      http.setRequestMethod("GET");
      http.addRequestProperty("Accept-Encoding", "gzip");
      http.addRequestProperty("User-Agent", "Monazilla/1.00");
      http.addRequestProperty("Connection", "close");
      http.connect();

      BufferedReader reader = new BufferedReader(new InputStreamReader(
          new GZIPInputStream(http.getInputStream()), "SHIFT_JIS"));

      ThreadList thread_list = new ThreadList();
      String line = null;

      while ((line = reader.readLine()) != null) {
        thread_list.add(Thread2ch.parseLine(line));
      }
      reader.close();
      http.disconnect();
      thread_list.sort();
      return thread_list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public static String getThreadUrl(Board board, Thread2ch thread) {
    return String.format("http://%s/test/read.cgi/%s/%s/", board.getHostName(), board.getBoardKey(), thread.getThreadId());
  }

}
