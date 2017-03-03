package com.crmall.styleparse;

import android.util.Log;

import com.crmall.styleparse.Parser.Declaration;
import com.crmall.styleparse.Parser.RuleSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Add a class header comment!
 */
public class StyleCSS {
    private static StyleCSS ourInstance = new StyleCSS();
    private List<RuleSet> ruleSet;

    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private Pattern pattern;
    private Matcher matcher;

    private StyleCSS() {
        ruleSet = new ArrayList<>();
        pattern = Pattern.compile(HEX_PATTERN);
    }

    public static StyleCSS getInstance() {
        return ourInstance;
    }

    private ArrayList<Integer> getPositionsAttributeFromID(String... ids) {
        String prefix = "#";
        RuleSet rule;
        ArrayList<Integer> positions = new ArrayList<>();

        for (int ruleID = 0; ruleID < ruleSet.size(); ruleID++) {
            rule = ruleSet.get(ruleID);
            for (String id : ids) {
                if (rule.getSelector().toString().equals(prefix + id)) {
                    positions.add(ruleID);
                }
            }
        }

        return positions;
    }

    private ArrayList<Integer> getPositionsAttributeFromClazz(String... clazzz) {
        String prefix = ".";

        RuleSet rule;
        ArrayList<Integer> positions = new ArrayList<>();

        for (int ruleID = 0; ruleID < ruleSet.size(); ruleID++) {
            rule = ruleSet.get(ruleID);
            for (String clazz : clazzz) {
                if (rule.getSelector().toString().equals(prefix + clazz)) {
                    positions.add(ruleID);
                }
            }
        }
        return positions;
    }

    public CSS getAttribute(String idOrClazz) {

        String[] split = idOrClazz.split(",");

        ArrayList<Integer> fromID = getPositionsAttributeFromID(split);
        ArrayList<Integer> fromClazz = getPositionsAttributeFromClazz(split);

        Collections.sort(fromID);
        Collections.sort(fromClazz);

        ArrayList<Integer> attributes = new ArrayList<>();
        if (fromClazz != null && fromClazz.size() > 0) {
            attributes.addAll(fromClazz);
        }
        if (fromID != null && fromID.size() > 0) {
            attributes.addAll(fromID);
        }

        if (attributes.size() > 0) {
            CSS css = new CSS();

            List<Declaration> declarations;
            for (Integer id : attributes) {
                declarations = ruleSet.get(id).getDeclarationBlock();
                for (Declaration dec : declarations) {

                    String value = prepareHexColor(dec.getValue().toString());

                    if (dec.getProperty().toString().equals("background-color")) {
                        css.setBackground(value);
                    } else if (dec.getProperty().toString().equals("background-image")) {
                        css.setBackgroundImage(value);
                    } else if (dec.getProperty().toString().equals("color")) {
                        css.setColor(value);
                    } else if (dec.getProperty().toString().equals("font-size")) {
                        css.setFontSize(value);
                    } else if (dec.getProperty().toString().equals("font-style")) {
                        //                        css.setFont();
                    } else if (dec.getProperty().toString().equals("border-color")) {
                        css.setBorderColor(value);
                    } else if (dec.getProperty().toString().equals("border-width")) {
                        css.setBorderWidth(value);
                    } else if (dec.getProperty().toString().equals("border-radius")) {
                        css.setBorderRadius(value);
                    }
                }
            }

            return css;
        }

        //        ruleSet.get(0).getSelector().toString(); // .BTN || #BTN
        //
        //        ruleSet.get(0).getDeclarationBlock().get(0).getProperty(); // COLOR
        //
        //        ruleSet.get(0).getDeclarationBlock().get(0).getValue(); // #FFF

        return null;

    }

    public void addRule(RuleSet rule) {
        ruleSet.add(rule);
    }

    public void clearRules() {
        ruleSet.clear();
    }

    private String prepareHexColor(String hex){

        if (hex.length() == 4 && validateHex(hex)){
            hex += hex.substring(1);
        }else if (hex.equals("transparent")){
            return "#00000000";
        }

        return hex;
    }

    private boolean validateHex(String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
