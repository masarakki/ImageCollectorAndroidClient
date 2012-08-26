package net.hisme.masaki.mona.test;

import static org.junit.Assert.*;

import net.hisme.masaki.mona.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreadListTest {
  private ThreadList thread_list;
  private Thread2ch bbs_thread;

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
    thread_list = new ThreadList();
    for (int i = 0; i < ids.length; i++) {
      thread_list.add(new Thread2ch(ids[i], titles[i]));
    }
  }

  @After
  public void tearDown() throws Exception {
    thread_list = null;
    bbs_thread = null;
  }

  @Test
  public void testGet() {
    bbs_thread = thread_list.get(1);
    assertEquals(ids[1], bbs_thread.getThreadId());
    assertEquals(titles[1], bbs_thread.getTitle());
  }

  @Test
  public void testSort() {
    thread_list.sort();
    bbs_thread = thread_list.get(1);
    assertEquals(ids[2], bbs_thread.getThreadId());
    assertEquals(titles[2], bbs_thread.getTitle());
  }
}
