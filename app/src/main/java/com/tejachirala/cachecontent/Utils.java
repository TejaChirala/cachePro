package com.tejachirala.cachecontent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;

/**
 * Created by tejachirala on 23/11/18
 */
public class Utils {

    public static Drawable setDrawableColor(Context context, int drawableResId, int color) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableResId);

        if (drawable != null && drawable.getConstantState() != null) {
            Drawable newDrawable = drawable.getConstantState().newDrawable().mutate();
            DrawableCompat.setTint(DrawableCompat.wrap(newDrawable), color);
            return newDrawable;
        }

        return drawable;
    }

}
