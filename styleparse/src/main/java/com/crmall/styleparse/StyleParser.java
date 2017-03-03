package com.crmall.styleparse;

import android.util.Log;

import com.crmall.styleparse.Parser.CSSHandler;
import com.crmall.styleparse.Parser.Declaration;
import com.crmall.styleparse.Parser.FontFace;
import com.crmall.styleparse.Parser.KeyFrame;
import com.crmall.styleparse.Parser.Parser;
import com.crmall.styleparse.Parser.RuleSet;
import com.crmall.styleparse.Parser.TokenSequence;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public final class StyleParser implements CSSHandler{

    private Parser parser;
    private StyleCSS css;

    public static void init(InputStream file) throws Exception {
        new StyleParser(file);
    }

    private StyleParser(InputStream file) throws Exception {
        css = StyleCSS.getInstance();
        css.clearRules();

        parser = new Parser(file, this);
        parser.parse();
    }

    /**
     * Handle a parse error. This is called before the parser exits.
     *
     * @param error the error message
     * @param t     the error. This may be {@code null}.
     */
    @Override public void handleError(String error, Throwable t) throws Exception {
        throw new Exception(error, t);
    }

    /**
     * Handle the {@literal @}import At-Rule
     *
     * @param importSequence@return an {@code InputStream} associated with the given name, or {@code null}.
     */
    @Override public InputStream handleImport(TokenSequence importSequence) {
        return null;
    }

    /**
     * Alerts the listener that a {@literal @}charset At-Rule has been parsed and handled by the
     * {@link com.crmall.styleparse.Parser.CSSScan}.
     *
     * @param charset the charset String.
     *                FIXME: this needs further tests.
     */
    @Override public void handleNewCharset(String charset) {

    }

    /**
     * Handle the {@literal @}namespace At-Rule.
     *
     * @param namespace the namespace
     */
    @Override public void handleNamespace(String namespace) {

    }

    /**
     * Gets whether or not the logic is {@code true} for the given {@literal @}support query
     *
     * @param logic the supports logic to query
     * @return {@code true} if the support block should be included (at the end of the CSS Input Stream).
     * Otherwise {@code false}.
     */
    @Override public boolean supports(TokenSequence logic) {
        return false;
    }

    /**
     * Handle {@literal @}Keyframes At-Rule
     *
     * @param identifier the name of the keyframes animation
     * @param keyframes  a list of the keyframes
     */
    @Override public void handleKeyframes(String identifier, List<KeyFrame> keyframes) {

    }

    /**
     * Handle the {@literal @}Font-Face At-Rule
     *
     * @param font the FontFace that has been parsed.
     */
    @Override public void handleFontFace(FontFace font) {
        Log.d("PARSER","FONT FACE");
    }

    /**
     * Query to see if the given media is supported. If so, the logic within will be appended
     * to the end of the css input stream
     *
     * @param types a list of media types used for the media query
     * @return true if any of the the given media is supported
     */
    @Override public boolean queryMedia(TokenSequence[] types) {
        return false;
    }

    /**
     * Handle the {@literal @}page At-Rule
     *
     * @param pseudoClass the pseudo-class
     * @param properties  the list of properties
     */
    @Override public void handlePage(TokenSequence pseudoClass, List<Declaration> properties) {

    }

    @Override public boolean queryDocument(TokenSequence[] functions) {
        return false;
    }

    /**
     * Called once the parser has completed.
     *
     * @param ruleSet contains all the parsed rules
     */
    @Override public void handleRuleSet(RuleSet ruleSet) {
        css.addRule(ruleSet);
    }
}
