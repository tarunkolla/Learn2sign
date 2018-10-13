// Generated code from Butter Knife. Do not modify!
package com.example.amine.learn2sign;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296292;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.et_email = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'et_email'", EditText.class);
    target.et_id = Utils.findRequiredViewAsType(source, R.id.et_id, "field 'et_id'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_login, "field 'bt_login' and method 'login'");
    target.bt_login = Utils.castView(view, R.id.bt_login, "field 'bt_login'", Button.class);
    view2131296292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_email = null;
    target.et_id = null;
    target.bt_login = null;

    view2131296292.setOnClickListener(null);
    view2131296292 = null;
  }
}
