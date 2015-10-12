package com.kickstarter.libs;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtils {
  private StatusBarUtils() {}

  public static void apply(@NonNull final BaseActivity activity, final @ColorInt int color) {
    if (!ApiCapabilities.canSetStatusBarColor()) {
      return;
    }

    final Window window = activity.getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(color);
  }

  public static void apply(@NonNull final BaseActivity activity, final @ColorInt int color,
    final boolean overlayShouldBeLight) {
    apply(activity, color);

    if (!ApiCapabilities.canSetDarkStatusBarIcons()) {
      return;
    }

    final int uiFlag = overlayShouldBeLight ?
      View.SYSTEM_UI_FLAG_VISIBLE :
      View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
    final Window window = activity.getWindow();
    window.getDecorView().setSystemUiVisibility(uiFlag);
  }
}
