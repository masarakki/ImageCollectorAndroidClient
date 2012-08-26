package net.hisme.masaki.mona.test;

import static org.junit.Assert.*;

import net.hisme.masaki.mona.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class Thread2chTest {
  private Thread2ch bbs_thread;

  @Before
  public void setUp() {
    bbs_thread = new Thread2ch("10101010", "title title title");
  }

  @After
  public void tearDown() {

    bbs_thread = null;
  }

  @Test
  public void testGetThreadId() {
    assertEquals("10101010", bbs_thread.getThreadId());
  }

  @Test
  public void testGetTitle() {
    assertEquals("title title title", bbs_thread.getTitle());
  }

  @Test
  public void testParseLine() {
    bbs_thread = Thread2ch.parseLine("1234567890.dat<>テスト (1)");
    assertEquals("1234567890", bbs_thread.getThreadId());
    assertEquals("テスト (1)", bbs_thread.getTitle());
  }

}
