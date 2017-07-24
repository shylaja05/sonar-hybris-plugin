package org.divy.sonar.check.java.generic;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.squidbridge.annotations.RuleTemplate;

import java.util.regex.Pattern;

@Rule(key = "GenericGetterUsageCheck", priority = Priority.MINOR, name = "Architectural layer should not be skipped", tags = { "hybris" }, description = "Use objects from immediate layer instead of skipping layers")
@RuleTemplate
public class GenericGetterUsageCheck extends AbstractPublicGetterUsageCheck {

    private static final String MESSAGE = "Remove or make private all the public getter methods and properties that make Services available to Controllers";

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
    protected boolean hasTargetedTypeName(ClassTree type) {
        return resolveTargetTypeNameMatchPattern().matcher(type.simpleName().name()).matches();
    }

    @Override
    public void visitMethod(MethodTree tree) {
        if (isPublicGetter(tree)) {
            context.reportIssue(this, tree, getMessage(tree));
        }
        super.visitMethod(tree);
    }

    @Override
    protected boolean isPublicGetter(MethodTree tree) {
        for (ModifierKeywordTree modifier : tree.modifiers().modifiers()) {
            if (modifier.modifier().equals(Modifier.PUBLIC)) {
                if (logger.isDebugEnabled()) {
                    logger.info("Tree modifier name :" + modifier);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isRestricted(Tree tree) {
        String typeName = tree.lastToken().text();
        return resolveRestrictedTypeNameMatchPattern().matcher(typeName).matches();
    }

    @Override
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
