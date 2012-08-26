package net.hisme.masaki.mona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ThreadList extends ArrayList<Thread2ch> {
  private static final long serialVersionUID = 1L;
  
  public void sort() {
    Collections.sort(this, new Comparator<Thread2ch>() {
      @Override
      public int compare(Thread2ch a, Thread2ch b) {
        return (int) (Double.parseDouble(b.getThreadId()) - Double.parseDouble(a.getThreadId()));
      }
    });
  }
}
