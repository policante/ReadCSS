package com.crmall.styleparse.UI;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.crmall.styleparse.CSS;
import com.crmall.styleparse.R;
import com.crmall.styleparse.StyleCSS;

/**
 * TODO: Add a class header comment!
 */
public class Button extends AppCompatButton {

    private CSS css;
    private BitmapShader shader;

    private float rounded = 0;

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Button, 0, 0);
        String identifier = a.getString(R.styleable.Button_id);
        String clazz = a.getString(R.styleable.Button_styleClass);

        if (clazz == null || clazz.trim().isEmpty()) {
            throw new IllegalArgumentException("Style class not found");
        }

        String searchTxt = "";

        if (identifier != null && !identifier.trim().isEmpty()) {
            searchTxt = identifier;
        }

        clazz = clazz.replaceAll(" ", ",");

        searchTxt += "," + clazz;

        css = StyleCSS.getInstance().getAttribute(searchTxt);

        if (css != null) {
            if ((css.getBorderColor() != null && !css.getBorderColor().trim().isEmpty()) ||
                    (css.getBorderWidth() != null && !css.getBorderWidth().trim().isEmpty()) ||
                    (css.getBorderRadius() != null && !css.getBorderRadius().trim().isEmpty())) {

                GradientDrawable gd = new GradientDrawable();
                if (css.getBorderRadius() != null && !css.getBorderRadius().trim().isEmpty()) {
                    if (css.getBorderRadius().contains("%")) {
                        this.rounded = (100 / Float.parseFloat(css.getBorderRadius().replaceAll("\\D+", "")));
                    } else {
                        gd.setCornerRadius(Float.parseFloat(css.getBorderRadius().replaceAll("\\D+", "")));
                    }
                }

                int width = 0;
                if (css.getBorderWidth() != null && !css.getBorderWidth().trim().isEmpty()) {
                    width = Integer.parseInt(css.getBorderWidth());
                }

                int color = Color.TRANSPARENT;
                if (css.getBorderColor() != null && !css.getBorderColor().trim().isEmpty()) {
                    color = Color.parseColor(css.getBorderColor());
                }
                gd.setStroke(width, color);

                if (css.getBackground() != null && !css.getBackground().trim().isEmpty()) {
                    String co = css.getBackground();
                    gd.setColor(Color.parseColor(co));
                }

                setBackgroundDrawable(gd);
            } else {

                if (css.getBackground() != null && !css.getBackground().trim().isEmpty()) {
//                    setBackgroundColor(Color.parseColor(css.getBackground()));

                    ColorStateList bgColorStateList = new ColorStateList(
                            new int[][]{
                                    new int[]{android.R.attr.state_pressed},
                                    new int[]{android.R.attr.state_enabled},
                                    new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
                                    new int[]{-android.R.attr.state_enabled},
                                    new int[]{} // this should be empty to make default color as we want
                            },
                            new int[]{
                                    Color.DKGRAY,
                                    Color.parseColor(css.getBackground()),
                                    Color.DKGRAY,
                                    Color.DKGRAY,
                                    Color.parseColor(css.getBackground())
                            }
                    );

                    setSupportBackgroundTintList(bgColorStateList);
                }
            }

            //            if (css.getBackgroundImage() != null && !css.getBackgroundImage().trim().isEmpty()){
            //                setBackgroundResource(Integer.parseInt(css.getBackgroundImage()));
            //            }
            if (css.getColor() != null && !css.getColor().trim().isEmpty()) {
//                String co = css.getColor();
//                setTextColor(Color.parseColor(co));

                ColorStateList textColorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_pressed},
                                new int[]{android.R.attr.state_enabled},
                                new int[]{android.R.attr.state_focused, android.R.attr.state_pressed},
                                new int[]{-android.R.attr.state_enabled},
                                new int[]{} // this should be empty to make default color as we want
                        },
                        new int[]{
                                Color.DKGRAY,
                                Color.parseColor(css.getColor()),
                                Color.DKGRAY,
                                Color.DKGRAY,
                                Color.parseColor(css.getColor())
                        }
                );

                setTextColor(textColorStateList);
            }

        } else {
            throw new IllegalArgumentException("Style css not found for identifier #" + identifier + " or classes ." + clazz
                    .replaceAll(",", ", .") + " attributes ");
        }

    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Button(Context context) {
        super(context);
    }


}
