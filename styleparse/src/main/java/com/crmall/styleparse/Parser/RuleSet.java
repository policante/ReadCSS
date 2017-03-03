package com.crmall.styleparse.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * TODO: Add a class header comment!
 */
public class RuleSet {
    /** Selector */
    private TokenSequence selector;
    /** List of Property-Value pairings */
    private List<Declaration> declarationBlock;

    /**
     * Constructor
     */
    public RuleSet()
    {
        declarationBlock = new ArrayList<Declaration>();
    }

    /**
     * Constructor
     * @param selector
     */
    public RuleSet(TokenSequence selector)
    {
        this();
        this.selector = selector;
    }

    /**
     * Constructor
     * @param selector
     * @param declarationBlock
     */
    public RuleSet(TokenSequence selector, List<Declaration> declarationBlock)
    {
        this.selector = selector;
        this.declarationBlock = declarationBlock;
    }

    public TokenSequence getSelector() {
        return selector;
    }

    public void setSelector(TokenSequence selector) {
        this.selector = selector;
    }

    public List<Declaration> getDeclarationBlock() {
        return declarationBlock;
    }

    public void setDeclarationBlock(List<Declaration> declarationBlock) {
        this.declarationBlock = declarationBlock;
    }

    @Override
    public String toString()
    {
        StringBuilder block = new StringBuilder(" {\n");
        for (int i = 0; i < declarationBlock.size(); i++)
        {
            block.append(" ").append(declarationBlock.get(i)).append(";\n");
        }
        block.append("}");

        return String.format(Locale.US, "%s %s", selector.toString(), block.toString());
    }

    public String toDebugString()
    {
        StringBuilder block = new StringBuilder(" {\n");
        for (int i = 0; i < declarationBlock.size(); i++)
        {
            block.append(" [DECLARATION]: ").append(declarationBlock.get(i)).append(";\n");
        }
        block.append("}");

        return String.format(Locale.US, "[SELECTOR]: %s %s", selector.toString(), block.toString());
    }
}
