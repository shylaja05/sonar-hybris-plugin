package org.divy.sonar.check.java.generic;

import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

public abstract class AbstractLayerUsageCheck extends AbstractTargetedCheck {

    @Override
    public void visitImport(ImportTree importTree) {
        Tree importQualifier = importTree.qualifiedIdentifier();

        if(logger.isDebugEnabled()) {
            logger.debug("visiting import : " + importQualifier.lastToken().text());
        }

        if(isTargetedCompilationUnit(importTree.parent()) && isRestricted(importQualifier)) {
            if(logger.isDebugEnabled()) {
                logger.debug("Found restricted import : " + importQualifier.lastToken().text());
            }
            context.reportIssue(this, importQualifier, getMessage(importQualifier));
        }
        super.visitImport(importTree);
    }

    @Override
    public void visitVariable(VariableTree tree) {
        if(isTargetedChild(tree) && isRestricted(tree.type())) {
            context.reportIssue(this, tree, getMessage(tree.type()));
        }
        super.visitVariable(tree);
    }

    protected abstract boolean isRestricted(Tree tree);
    protected abstract String getMessage(Tree tree);
}
