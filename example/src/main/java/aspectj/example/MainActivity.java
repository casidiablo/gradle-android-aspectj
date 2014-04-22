package aspectj.example;

import android.os.Bundle;

import com.example.lib.ActivitySubClass;

public class MainActivity extends ActivitySubClass {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    new Foo().doFoo(this, "alborosie");
  }
}
