package net.hisme.masaki.access_2ch;

public class BbsThread {
  private String thread_id;
  private String title;

  public String getThreadId() {
    return thread_id;
  }

  public String getTitle() {
    return title;
  }

  public BbsThread(String thread_id, String title) {
    this.thread_id = thread_id;
    this.title = title;
  }

  public static BbsThread parseLine(String line) {
    String[] columns = line.split(".dat<>");
    String thread_id = columns[0];
    String title = columns[1];
    return new BbsThread(thread_id, title);
  }
}
