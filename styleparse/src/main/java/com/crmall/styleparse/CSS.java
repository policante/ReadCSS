package com.crmall.styleparse;

import android.util.Log;

import com.crmall.styleparse.Parser.FontFace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Add a class header comment!
 */
public class CSS {

    //General
    private String background;
    private String backgroundImage;

    //Fonts
    private String color;
    private String fontSize;
    private FontFace font;

    //Border
    private String borderColor;
    private String borderWidth;
    private String borderRadius;

    public CSS(){

    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontSize() {
        if (fontSize == null)
            return null;

        return fontSize.replaceAll("\\D+","");
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public FontFace getFont() {
        return font;
    }

    public void setFont(FontFace font) {
        this.font = font;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderWidth() {
        if (borderWidth == null)
            return null;

        return borderWidth.replaceAll("\\D+","");
    }

    public void setBorderWidth(String borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getBorderRadius() {
        if (borderRadius == null)
            return null;
        return borderRadius;

    }

    public void setBorderRadius(String borderRadius) {
        this.borderRadius = borderRadius;
    }

}
