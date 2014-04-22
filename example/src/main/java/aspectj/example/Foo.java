package aspectj.example;

import android.content.Context;
import android.widget.Toast;

public class Foo {
  public void doFoo(Context context, String foo) {
    Toast.makeText(context, "toast: " + foo, Toast.LENGTH_SHORT).show();
  }
}
