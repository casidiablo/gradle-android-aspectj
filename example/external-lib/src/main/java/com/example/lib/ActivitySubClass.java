package com.example.lib;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.lang.Override;

public class ActivitySubClass extends Activity {

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);

    TextView textView = new TextView(this);
    textView.setText("Hello AspectJ");

    setContentView(textView);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
  }
}