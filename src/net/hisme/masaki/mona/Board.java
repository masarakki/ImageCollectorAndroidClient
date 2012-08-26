package net.hisme.masaki.mona;

import java.net.URL;

public class Board {
  private String board_name;
  private String host_name;
  private String board_key;

  public Board(String board_name, String host_name, String board_key) {
    this.board_name = board_name;
    this.host_name = host_name;
    this.board_key = board_key;
  }

  public String getBoardName() {
    return board_name;
  }

  public String getHostName() {
    return host_name;
  }

  public String getBoardKey() {
    return board_key;
  }

  protected URL getUrl() {
    try {
      return new URL(String.format("http://%s/%s/subject.txt", host_name, board_key));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ThreadList getThreadList() {
    return MonaUtils.getThreadList(this);
  }
}
