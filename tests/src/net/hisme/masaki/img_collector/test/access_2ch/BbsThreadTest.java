package net.hisme.masaki.img_collector.test.access_2ch;

import static org.junit.Assert.*;

import net.hisme.masaki.access_2ch.BbsThread;

import org.junit.Before;
import org.junit.Test;

public class BbsThreadTest {
  private BbsThread bbs_thread;

  @Before
  public void setUp() {
    bbs_thread = new BbsThread("10101010", "title title title");
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
    bbs_thread = BbsThread.parseLine("1234567890.dat<>テスト (1)");
    assertEquals("1234567890", bbs_thread.getThreadId());
    assertEquals("テスト (1)", bbs_thread.getTitle());
  }

}
