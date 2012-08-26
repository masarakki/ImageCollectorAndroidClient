package net.hisme.masaki.img_collector.test;

import java.lang.reflect.Method;

public class TestBase {
  public Object send(Object object, String method_name, Object... params) {
    try {
      Class<?> c = object.getClass();
      Class<?>[] param_types = new Class<?>[params.length];

      Method m = c.getDeclaredMethod(method_name, param_types);
      m.setAccessible(true);
      return m.invoke(object, params);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
