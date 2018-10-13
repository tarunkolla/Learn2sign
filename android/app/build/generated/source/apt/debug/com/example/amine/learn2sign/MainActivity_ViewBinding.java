// Generated code from Butter Knife. Do not modify!
package com.example.amine.learn2sign;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.VideoView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296293;

  private View view2131296294;

  private View view2131296291;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.rg_practice_learn = Utils.findRequiredViewAsType(source, R.id.rg_practice_learn, "field 'rg_practice_learn'", RadioGroup.class);
    target.rb_learn = Utils.findRequiredViewAsType(source, R.id.rb_learn, "field 'rb_learn'", RadioButton.class);
    target.rb_practice = Utils.findRequiredViewAsType(source, R.id.rb_practice, "field 'rb_practice'", RadioButton.class);
    target.sp_words = Utils.findRequiredViewAsType(source, R.id.sp_words, "field 'sp_words'", Spinner.class);
    target.sp_ip_address = Utils.findRequiredViewAsType(source, R.id.sp_ip_address, "field 'sp_ip_address'", Spinner.class);
    target.vv_video_learn = Utils.findRequiredViewAsType(source, R.id.vv_video_learn, "field 'vv_video_learn'", VideoView.class);
    target.vv_record = Utils.findRequiredViewAsType(source, R.id.vv_record, "field 'vv_record'", VideoView.class);
    view = Utils.findRequiredView(source, R.id.bt_record, "field 'bt_record' and method 'record_video'");
    target.bt_record = Utils.castView(view, R.id.bt_record, "field 'bt_record'", Button.class);
    view2131296293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.record_video();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_send, "field 'bt_send' and method 'sendToServer'");
    target.bt_send = Utils.castView(view, R.id.bt_send, "field 'bt_send'", Button.class);
    view2131296294 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendToServer();
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_cancel, "field 'bt_cancel' and method 'cancel'");
    target.bt_cancel = Utils.castView(view, R.id.bt_cancel, "field 'bt_cancel'", Button.class);
    view2131296291 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancel();
      }
    });
    target.ll_after_record = Utils.findRequiredViewAsType(source, R.id.ll_after_record, "field 'll_after_record'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rg_practice_learn = null;
    target.rb_learn = null;
    target.rb_practice = null;
    target.sp_words = null;
    target.sp_ip_address = null;
    target.vv_video_learn = null;
    target.vv_record = null;
    target.bt_record = null;
    target.bt_send = null;
    target.bt_cancel = null;
    target.ll_after_record = null;

    view2131296293.setOnClickListener(null);
    view2131296293 = null;
    view2131296294.setOnClickListener(null);
    view2131296294 = null;
    view2131296291.setOnClickListener(null);
    view2131296291 = null;
  }
}
