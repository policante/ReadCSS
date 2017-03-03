package com.crmall.styleparse.Parser;

import java.io.InputStream;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class DefaultHandler implements CSSHandler{

    @Override
    public void handleError(String error, Throwable t) {
        System.err.println(error);
        t.printStackTrace(System.out);
    }

    @Override
    public InputStream handleImport(TokenSequence importString) {
        System.out.println("Found @import " + importString.toString());
        return null;
    }

    @Override
    public void handleNewCharset(String charset) {
        System.out.println("New Charset: " + charset);
    }

    @Override
    public void handleNamespace(String namespace) {
        System.out.println("Found Namespace " + namespace);
    }

    @Override
    public boolean supports(TokenSequence logic) {
        System.out.println("Skipping support check for logic: " + logic.toString());
        return false;
    }

    @Override
    public void handleKeyframes(String identifier, List<KeyFrame> keyframes) {
        StringBuilder builder = new StringBuilder(" {\n");
        for (int i = 0; i < keyframes.size(); i++)
        {
            builder.append("  ").append(keyframes.get(i)).append(";\n");
        }
        builder.append("}");
        System.out.println(" Found @keyframes " + identifier + builder.toString());
    }

    @Override
    public void handleFontFace(FontFace font) {
        System.out.println("Found New Font: " + font.toString());
    }

    @Override
    public void handleRuleSet(RuleSet ruleSet) {
        System.out.println("\n" + ruleSet.toString() + "\n");
    }

    @Override
    public boolean queryMedia(TokenSequence[] types) {
        StringBuilder builder = new StringBuilder("Skipping media query for media types: [ ");
        for (int i = 0; i < types.length; i++)
        {
            builder.append(types[i].toString());
            if (i != types.length-1)
                builder.append(", ");

        }
        builder.append(" ]");
        System.out.println(builder.toString());
        return false;
    }

    @Override
    public void handlePage(TokenSequence pseudoClass, List<Declaration> properties) {
        StringBuilder builder = new StringBuilder(" {\n");
        for (int i = 0; i < properties.size(); i++)
        {
            builder.append("  ").append(properties.get(i)).append(";\n");
        }
        builder.append("}");
        System.out.println(" Found @page " + pseudoClass.toString() + builder.toString());
    }

    @Override
    public boolean queryDocument(TokenSequence[] functions) {
        StringBuilder builder = new StringBuilder("Skipping document query for document functions: [ ");
        for (int i = 0; i < functions.length; i++)
        {
            builder.append(functions[i].toString());
            if (i != functions.length-1)
                builder.append(", ");

        }
        builder.append(" ]");
        System.out.println(builder.toString());
        return false;
    }
}
