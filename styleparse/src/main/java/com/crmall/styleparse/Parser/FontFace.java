package com.crmall.styleparse.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TODO: Add a class header comment!
 */
public class FontFace implements GlobalConstants {
    /**
     * Normal font weight
     */
    public static final int FONT_WEIGHT_NORMAL = 0;
    /**
     * bold font weight
     */
    public static final int FONT_WEIGHT_BOLD = 1;
    /**
     * Font Family
     */
    private String fontFamily;
    /**
     * Specifies how to stretch or condense the font
     */
    private FontStretch stretch = FontStretch.normal;
    /**
     * Normal, italic, or oblique
     */
    private FontStyle style = FontStyle.normal;
    /**
     * Specifies the font weight.
     *
     * @see #FONT_WEIGHT_NORMAL
     * @see #FONT_WEIGHT_BOLD
     */
    private int fontWeight = FONT_WEIGHT_NORMAL;
    /**
     * Unicode range
     */
    private String unicodeRange = "U+0-10FFFF";
    /**
     * Provides access to the scanner, in order to get the line separator character
     */
    private CSSScan scanner;
    /**
     * Maps function name to a list of resources. This is useful so that there can be multiple resources
     * used. For example, it will map the following:
     * <pre>
     * url("http://www.example.com/font.ttf"), url("http://www.example.com/font.woff"), asset("fonts/font.ttf")
     * </pre>
     * to:
     * <pre>
     * {"url" : {"http://www.example.com/font.ttf", "http://www.example.com/font.woff"}},
     * {"asset" : {"fonts/font.ttf"}}
     * </pre>
     */
    private Map<String, List<String>> sources;

    /**
     * Constructor
     *
     * @param fontFamily
     * @param sources
     */
    public FontFace(String fontFamily, Map<String, List<String>> sources, CSSScan s) {
        this.fontFamily = fontFamily;
        this.sources = sources;
        this.scanner = s;
    }

    /**
     * Constructor
     *
     * @param declarationBlock
     * @throws Exception if the declarations are not correctly formatted.
     */
    public FontFace(List<Declaration> declarationBlock, CSSScan s) throws Exception {
        this.scanner = s;
        for (int i = 0; i < declarationBlock.size(); i++) {
            Declaration d = declarationBlock.get(i);
            String property = d.getProperty().toString();
            TokenSequence value = d.getValue();
            if (property.equals("font-family")) fontFamily = property;
            else if (property.equals("src")) {
                sources = new HashMap<String, List<String>>();
                List<Token> tokens = value.getTokens();
                Iterator<Token> iterator = tokens.iterator();
                while (iterator.hasNext()) {
                    Token t = iterator.next();
                    if (t.tokenCode != COMMA && t.tokenCode != SPACE && t.tokenCode != scanner.getLineSeparator()) {
                        String function = t.toString();
                        match(t, IDENTIFIER);
                        t = iterator.next();
                        match(t, LEFTPAREN);
                        t = iterator.next();
                        StringBuilder uri = new StringBuilder();
                        while (t.tokenCode != RIGHTPAREN) {
                            uri.append(t.toString());
                            t = iterator.next();
                        }
                        List<String> resources = sources.get(function);
                        if (resources == null) resources = new ArrayList<String>();
                        resources.add(uri.toString());
                        sources.put(function, resources);
                    }


                }

            } else if (property.equals("font-stretch")) {
                String fontStretch = value.toString();
                if (fontStretch.equalsIgnoreCase("normal")) stretch = FontStretch.normal;
                else if (fontStretch.equalsIgnoreCase("condensed")) stretch = FontStretch.condensed;
                else if (fontStretch.equalsIgnoreCase("ultra-condensed")) stretch = FontStretch.ultra_condensed;
                else if (fontStretch.equalsIgnoreCase("extra-condensed")) stretch = FontStretch.extra_condensed;
                else if (fontStretch.equalsIgnoreCase("semi-condensed")) stretch = FontStretch.semi_condensed;
                else if (fontStretch.equalsIgnoreCase("expanded")) stretch = FontStretch.expanded;
                else if (fontStretch.equalsIgnoreCase("ultra-expanded")) stretch = FontStretch.ultra_expanded;
                else if (fontStretch.equalsIgnoreCase("extra-expanded")) stretch = FontStretch.extra_expanded;
                else if (fontStretch.equalsIgnoreCase("semi-expanded")) stretch = FontStretch.semi_expanded;
            } else if (property.equals("font-style")) {
                String fontStyle = value.toString();
                if (fontStyle.equalsIgnoreCase("normal")) style = FontStyle.normal;
                else if (fontStyle.equalsIgnoreCase("oblique")) style = FontStyle.oblique;
                else if (fontStyle.equalsIgnoreCase("italic")) style = FontStyle.italic;
            } else if (property.equals("font-weight")) {
                String weight = value.toString();
                if (weight.equalsIgnoreCase("normal")) fontWeight = FONT_WEIGHT_NORMAL;
                else if (weight.equalsIgnoreCase("bold")) fontWeight = FONT_WEIGHT_BOLD;
                else {
                    int number = Integer.parseInt(weight);
                    switch (number) {
                        case 100:
                            fontWeight = 100;
                            break;
                        case 200:
                            fontWeight = 200;
                            break;
                        case 300:
                            fontWeight = 300;
                            break;
                        case 400:
                            fontWeight = 400;
                            break;
                        case 500:
                            fontWeight = 500;
                            break;
                        case 600:
                            fontWeight = 600;
                            break;
                        case 700:
                            fontWeight = 700;
                            break;
                        case 800:
                            fontWeight = 800;
                            break;
                        case 900:
                            fontWeight = 900;
                            break;
                    }
                }
            } else if (property.equals("unicode-range")) unicodeRange = value.toString();
        }
    }

    /**
     * Used to ensure the proper syntax is used
     *
     * @param t
     * @param matcher
     * @return
     * @throws Exception
     */
    private boolean match(Token t, int matcher) throws Exception {
        if (t.tokenCode == matcher) return true;
        throw new Exception("bad src! Found unexpected token: " + t.toDebugString());
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public FontStretch getStretch() {
        return stretch;
    }

    public void setStretch(FontStretch stretch) {
        this.stretch = stretch;
    }

    public FontStyle getStyle() {
        return style;
    }

    public void setStyle(FontStyle style) {
        this.style = style;
    }

    public int getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(int fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getUnicodeRange() {
        return unicodeRange;
    }

    public void setUnicodeRange(String unicodeRange) {
        this.unicodeRange = unicodeRange;
    }

    public Map<String, List<String>> getSources() {
        return sources;
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder("@font-face {\n");
        builder.append("  font-family: ")
                .append(fontFamily)
                .append("\n")
                .append("  src: ")
                .append(sources)
                .append("\n")
                .append("  font-stretch: ")
                .append(stretch.name())
                .append("\n")
                .append("  font-style: ")
                .append(style.name())
                .append("\n")
                .append("  font-weight: ")
                .append(fontWeight)
                .append("\n")
                .append("  unicode-range: ")
                .append(unicodeRange)
                .append("\n}");
        return builder.toString();
    }

    /**
     * Specifies how to strech or condense the font
     */
    public static enum FontStretch {
        normal,
        condensed,
        ultra_condensed,
        extra_condensed,
        semi_condensed,
        expanded,
        ultra_expanded,
        extra_expanded,
        semi_expanded
    }

    /**
     * Specifies the font style
     */
    public static enum FontStyle {
        normal,
        italic,
        oblique
    }
}
