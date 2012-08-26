package net.hisme.masaki.img_collector.test.access_2ch;

import net.hisme.masaki.img_collector.test.TestBase;
import java.net.URL;

import net.hisme.masaki.access_2ch.Board;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest extends TestBase {
  private Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board("board_name", "example.com", "board_key");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testBoardName() {
    assertEquals("board_name", board.getBoardName());
  }

  @Test
  public void testHostName() {
    assertEquals("example.com", board.getHostName());
  }

  @Test
  public void testBoardKey() {
    assertEquals("board_key", board.getBoardKey());
  }

  @Test
  public void testGetUrl() {
    URL url = (URL) send(board, "getUrl");

    assertEquals("example.com", url.getHost());
    assertEquals("/board_key/subject.txt", url.getPath());
  }
}
