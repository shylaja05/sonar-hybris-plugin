package org.divy.sonar.check.java.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.squidbridge.annotations.RuleTemplate;

import java.util.regex.Pattern;

@Rule(key = "GenericGetterUsageCheck", priority = Priority.MINOR, name = "Make all getter as Private methods", tags = {
        "hybris" }, description = "Remove or make private all the public getter methods and properties that make Services available to Controllers")
@RuleTemplate
public class GenericGetterUsageCheck extends BaseTreeVisitor implements JavaFileScanner {

    private static final String MESSAGE = "Remove or make private all the public getter methods and properties that make Services available to Controllers";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected JavaFileScannerContext context;

    @RuleProperty(description = "Pattern to match the Architecture layer where the check needs to be performed")
    String targetTypeNameMatch;

    @RuleProperty(description = "Pattern to match restricted layer to be checked")
    String restrictedTypeNameMatch;

    @RuleProperty(description = "Human readable name of Architecture layer where the check needs to be performed")
    String targetTypeName;

    @RuleProperty(description = "Human readable name of restricted layer")
    String restrictedTypeName;

    private Pattern targetTypeNameMatchPattern;
    private Pattern restrictedTypeNameMatchPattern;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    protected boolean hasTargetedTypeName(ClassTree type) {
        return resolveTargetTypeNameMatchPattern().matcher(type.simpleName().name()).matches();
    }

    @Override
    public void visitMethod(MethodTree tree) {
        if (isPublicGetterForRestrictedType(tree)) {
            context.reportIssue(this, tree, getMessage(tree));
        }
        super.visitMethod(tree);
    }

    protected boolean isPublicGetterForRestrictedType(MethodTree tree) {
        for (ModifierKeywordTree modifier : tree.modifiers().modifiers()) {
            if (modifier.modifier().equals(Modifier.PUBLIC)) {
                String methodReturnType = tree.returnType().toString().toLowerCase();
                String methodName = tree.simpleName().toString();
                if (methodName.startsWith("get") && methodReturnType.endsWith("service")) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Tree modifier name :" + modifier + "\n");
                        logger.debug("Method Name :" + methodName);
                        logger.debug("Method Return Type : " + methodReturnType);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isRestricted(Tree tree) {
        String typeName = tree.lastToken().text();
        return resolveRestrictedTypeNameMatchPattern().matcher(typeName).matches();
    }

    protected String getMessage(Tree tree) {
        return String.format(MESSAGE, targetTypeName, restrictedTypeName);
    }

    private Pattern resolveRestrictedTypeNameMatchPattern() {
        if (restrictedTypeNameMatchPattern == null) {
            restrictedTypeNameMatchPattern = Pattern.compile(restrictedTypeNameMatch);
        }
        return restrictedTypeNameMatchPattern;
    }

    private Pattern resolveTargetTypeNameMatchPattern() {
        if (targetTypeNameMatchPattern == null) {
            targetTypeNameMatchPattern = Pattern.compile(targetTypeNameMatch);
        }
        return targetTypeNameMatchPattern;
    }

}
