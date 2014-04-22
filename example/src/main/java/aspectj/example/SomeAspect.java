package aspectj.example;

import android.content.Context;
import android.widget.Toast;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SomeAspect {
  @Before("call(* Foo.doFoo(..)) && args(context, *)")
  public void beforeFoo(Context context) {
    Toast.makeText(context, "before showing toast", Toast.LENGTH_SHORT).show();
  }

  @After("call(* Foo.doFoo(..)) && args(context, *)")
  public void afterFoo(Context context) {
    Toast.makeText(context, "after showing toast", Toast.LENGTH_SHORT).show();
  }
}
