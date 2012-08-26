package net.hisme.masaki.access_2ch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BbsThreadList extends ArrayList<BbsThread> {
  private static final long serialVersionUID = 1L;

  public void sort() {
    Collections.sort(this, new Comparator<BbsThread>() {
      @Override
      public int compare(BbsThread a, BbsThread b) {
        return (int) (Double.parseDouble(b.getThreadId()) - Double.parseDouble(a.getThreadId()));
      }
    });
  }
}
