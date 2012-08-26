package net.hisme.masaki.img_collector.test.access_2ch;

import static org.junit.Assert.*;

import net.hisme.masaki.access_2ch.BbsThread;
import net.hisme.masaki.access_2ch.BbsThreadList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BbsThreadListTest {
  private BbsThreadList bbs_thread_list;
  private BbsThread bbs_thread;

  private String[] ids = {
      "200000",
      "2100000000000",
      "1900000022222"
  };

  private String[] titles = {
      "a",
      "b",
      "c"
  };

  @Before
  public void setUp() throws Exception {
    bbs_thread_list = new BbsThreadList();
    for (int i = 0; i < ids.length; i++) {
      bbs_thread_list.add(new BbsThread(ids[i], titles[i]));
    }
  }

  @After
  public void tearDown() throws Exception {
    bbs_thread_list = null;
    bbs_thread = null;
  }

  @Test
  public void testGet() {
    bbs_thread = bbs_thread_list.get(1);
    assertEquals(ids[1], bbs_thread.getThreadId());
    assertEquals(titles[1], bbs_thread.getTitle());
  }

  @Test
  public void testSort() {
    bbs_thread_list.sort();
    bbs_thread = bbs_thread_list.get(1);
    assertEquals(ids[2], bbs_thread.getThreadId());
    assertEquals(titles[2], bbs_thread.getTitle());
  }
}
