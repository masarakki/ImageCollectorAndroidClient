package net.hisme.masaki.img_collector;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.hisme.masaki.mona.*;

public class ImageCollector extends Activity {

  private final Board[] boards = {
      new Board("女神", "kilauea.bbspink.com", "megami"),
      new Board("ニー速pink", "pele.bbspink.com", "neet4pink")
  };

  private Toast toast;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    LinearLayout buttons = (LinearLayout) findViewById(R.id.buttons);
    for (final Board board : boards) {
      Button button = new Button(ImageCollector.this);
      button.setText(board.getBoardName());
      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          updateThreadList(board);
        }
      });
      buttons.addView(button);
    }
  }

  private void updateThreadList(final Board board) {
    final Handler handler = new Handler();
    showMessage("Open board...");

    new Thread() {
      public void run() {
        final ThreadList thread_list = board.getThreadList();
        hideMessage();
        handler.post(new Runnable() {
          public void run() {
            ImageCollector.this.displayThreadList(board, thread_list);
          }
        });
      }
    }.start();
  }

  private void displayThreadList(final Board board, final ThreadList threads) {
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ImageCollector.this, R.layout.thread);
    for (Thread2ch thread : threads) {
      adapter.add(thread.getTitle());
    }
    ListView list = (ListView) findViewById(R.id.thread_list);
    list.setAdapter(adapter);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        postAddRequest(MonaUtils.getThreadUrl(board, threads.get(position)));
      }
    });
  }

  private void hideMessage() {
    if (toast != null)
      toast.cancel();
  }

  private void showMessage(String message) {
    hideMessage();
    toast = Toast.makeText(ImageCollector.this, message, Toast.LENGTH_SHORT);
    toast.show();
  }

  private void postAddRequest(final String thread_url) {
    showMessage("Send request...");
    final Handler handler = new Handler();
    new Thread() {
      public void run() {
        try {
          URL uri = new URL("http://hisme.net/~masaki/img_collector/_add_thread.php");
          HttpURLConnection http = (HttpURLConnection) uri.openConnection();
          http.setRequestMethod("POST");
          http.setDoOutput(true);
          http.setDoInput(true);
          PrintWriter writer = new PrintWriter(http.getOutputStream());

          writer.print("uri=" + thread_url);
          writer.flush();
          writer.close();

          BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
          final String post_result = reader.readLine();
          handler.post(new Runnable() {
            public void run() {
              showMessage(post_result);
            }
          });
          reader.close();

          http.disconnect();
        } catch (java.net.MalformedURLException e) {
          ImageCollector.log(e.toString());
        } catch (java.io.IOException e) {
          ImageCollector.log(e.toString());
        } catch (java.lang.IllegalStateException e) {
          ImageCollector.log(e.toString());
        } catch (NullPointerException e) {
          ImageCollector.log(e.toString());
        } catch (Exception e) {
          ImageCollector.log(e.toString());
        }
      }
    }.start();
  }

  static void log(String str) {
    Log.d("[ImageCollector]", str);
  }
}