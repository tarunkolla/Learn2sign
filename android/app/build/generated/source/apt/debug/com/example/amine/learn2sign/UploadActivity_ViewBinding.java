// Generated code from Butter Knife. Do not modify!
package com.example.amine.learn2sign;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadActivity_ViewBinding implements Unbinder {
  private UploadActivity target;

  @UiThread
  public UploadActivity_ViewBinding(UploadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadActivity_ViewBinding(UploadActivity target, View source) {
    this.target = target;

    target.rv_videos = Utils.findRequiredViewAsType(source, R.id.rv_videos, "field 'rv_videos'", RecyclerView.class);
    target.tv_filename = Utils.findRequiredViewAsType(source, R.id.tv_filename, "field 'tv_filename'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.pb_progress, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rv_videos = null;
    target.tv_filename = null;
    target.progressBar = null;
  }
}
