package com.crmall.styleparse.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * TODO: Add a class header comment!
 */
public class KeyFrame {
    /** The percent of the animation at which the values in the decalaration block should be set. */
    private double percent;
    /** The list of properties and their values that will be changed at the given percentage time */
    private List<Declaration> declarationBlock;

    /**
     * Default constructor
     */
    public KeyFrame()
    {
        declarationBlock = new ArrayList<Declaration>();
    }
    /**
     * Constructor
     * @param percent
     * @param declarationBlock
     */
    public KeyFrame(double percent, List<Declaration> declarationBlock)
    {
        this.percent = percent;
        this.declarationBlock = declarationBlock;
    }

    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
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
        StringBuilder block = new StringBuilder("{\n");
        for (int i = 0; i < declarationBlock.size(); i++)
        {
            block.append(" ").append(declarationBlock.get(i)).append(";\n");
        }
        block.append("}");

        return String.format(Locale.US, "%f%%: %s", percent, block.toString());
    }
}
