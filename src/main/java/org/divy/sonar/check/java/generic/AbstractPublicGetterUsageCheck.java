package org.divy.sonar.check.java.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

public abstract class AbstractPublicGetterUsageCheck extends BaseTreeVisitor implements JavaFileScanner {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    protected boolean isTargetedType(ClassTree type) {
        return hasTargetedTypeName(type);
    }

    protected abstract boolean isPublicGetter(MethodTree tree);

    protected abstract String getMessage(Tree tree);

    protected abstract boolean hasTargetedTypeName(ClassTree type);

    protected abstract boolean isRestricted(Tree tree);
}
