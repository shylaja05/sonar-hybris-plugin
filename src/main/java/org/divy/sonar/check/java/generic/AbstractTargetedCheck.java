package org.divy.sonar.check.java.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;

public abstract class AbstractTargetedCheck extends BaseTreeVisitor implements JavaFileScanner {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree tree) {
        if(isTargetedType(tree)) {
            super.visitClass(tree);
        }
    }

    boolean isTargetedCompilationUnit(Tree parent) {
        return parent.is(Tree.Kind.COMPILATION_UNIT) && containsTargetedType(((CompilationUnitTree)parent).types() );
    }

    private boolean containsTargetedType(List<Tree> types) {
        for (Tree type : types) {
            if(type.is(Tree.Kind.CLASS) && isTargetedType((ClassTree)type)) {
                return true;
            }
        }
        return false;
    }

    boolean isTargetedChild(Tree tree) {
        Tree possibleClass = tree;
        while (possibleClass.parent() != null) {
            possibleClass = possibleClass.parent();
            if (possibleClass.is(Tree.Kind.CLASS)) {
                break;
            }
        }

        ClassTree classTree = (ClassTree) possibleClass;

        return isTargetedType(classTree);
    }

    protected boolean isAnnotatedWith(ClassTree it, String fullyQualifiedName) {
        return it.symbol().metadata().isAnnotatedWith(fullyQualifiedName);
    }

    protected boolean isTargetedType(ClassTree type) {
        return hasTargetedTypeName(type);
    }

    protected abstract boolean hasTargetedTypeName(ClassTree type);
}
