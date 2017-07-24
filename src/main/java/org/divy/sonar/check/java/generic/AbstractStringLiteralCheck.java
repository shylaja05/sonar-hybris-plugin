package org.divy.sonar.check.java.generic;

import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.regex.Pattern;

public abstract class AbstractStringLiteralCheck extends AbstractTargetedCheck {

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitLiteral(LiteralTree tree) {
        if (tree.is(Tree.Kind.STRING_LITERAL) && isRestrictedLiteral(tree)) {
            context.reportIssue(this, tree, getMessage(tree));
        }
    }

    private boolean isRestrictedLiteral(LiteralTree tree) {
        return resolveRestrictedCompiledPattern().matcher(tree.value()).matches();
    }

    protected abstract Pattern resolveRestrictedCompiledPattern();

    protected abstract String getMessage(Tree tree);
}
