package net.hisme.masaki.mona.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.hisme.masaki.img_collector.test.TestBase;
import net.hisme.masaki.mona.*;

public class MonaUtilsTest extends TestBase {
  private Board board;
  private Thread2ch thread;

  @Before
  public void setUp() throws Exception {
    board = new Board("board_name", "host_name", "board_key");
    thread = new Thread2ch("thread_id", "Title");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetThreadUrl() {
    assertEquals("http://host_name/test/read.cgi/board_key/thread_id/", MonaUtils.getThreadUrl(board, thread));
  }
}
