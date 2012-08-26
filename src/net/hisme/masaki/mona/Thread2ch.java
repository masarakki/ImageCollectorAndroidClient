package net.hisme.masaki.mona;

public class Thread2ch {
  private String thread_id;
  private String title;

  public String getThreadId() {
    return thread_id;
  }

  public String getTitle() {
    return title;
  }

  public Thread2ch(String thread_id, String title) {
    this.thread_id = thread_id;
    this.title = title;
  }

  public static Thread2ch parseLine(String line) {
    String[] columns = line.split(".dat<>");
    String thread_id = columns[0];
    String title = columns[1];
    return new Thread2ch(thread_id, title);
  }

}
