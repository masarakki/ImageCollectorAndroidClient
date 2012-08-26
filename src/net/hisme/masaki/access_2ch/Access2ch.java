package net.hisme.masaki.access_2ch;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Access2ch {
  public static ThreadList threads(String host, String board) {
    ThreadList thread_list = new ThreadList();
    try {
      URL uri = new URL(String.format("http://%s/%s/subject.txt", host, board));
      HttpURLConnection http = (HttpURLConnection) uri.openConnection();
      http.setRequestMethod("GET");
      http.addRequestProperty("Accept-Encoding", "gzip");
      http.addRequestProperty("User-Agent", "Monazilla/1.00");
      http.addRequestProperty("Connection", "close");
      http.connect();

      BufferedReader reader = new BufferedReader(new InputStreamReader(
          new GZIPInputStream(http.getInputStream()), "SHIFT_JIS"));
      String line = null;
      while ((line = reader.readLine()) != null) {
        thread_list.add(BbsThread.parseLine(line));
      }
      thread_list.sort();

      reader.close();
      http.disconnect();
    } catch (java.net.MalformedURLException e) {
      // return e.toString();
    } catch (java.io.IOException e) {
      // return e.toString();
    } catch (java.lang.IllegalStateException e) {
      // return e.toString();
    } catch (NullPointerException e) {
      // return e.toString();
    }
    return thread_list;
  }
}
