package org.divy.sonar.check.java.generic;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.squidbridge.annotations.RuleTemplate;

import java.util.regex.Pattern;

@Rule(
        key = "GenericLayerUsageCheck",
        priority = Priority.MINOR,
        name = "Architectural layer should not be skipped",
        tags = {"hybris"},
        description = "Use objects from immediate layer instead of skipping layers"
)
@RuleTemplate
public class GenericLayerUsageCheck extends AbstractLayerUsageCheck {

    private static final String MESSAGE = "Refactor %1$s to not use %2$s directly";

    @RuleProperty(description = "Pattern to match the Architecture layer where the check needs to be performed")
    String targetTypeNameMatch;

    @RuleProperty(description = "Human readable name of Architecture layer where the check needs to be performed")
    String targetTypeName;

    @RuleProperty(description = "Pattern to match restricted layer to be checked")
    String restrictedTypeNameMatch;


    @RuleProperty(description = "Human readable name of restricted layer")
    String restrictedTypeName;

    private Pattern targetTypeNameMatchPattern;
    private Pattern restrictedTypeNameMatchPattern;

    @Override
    protected boolean hasTargetedTypeName(ClassTree type) {
        return resolveTargetTypeNameMatchPattern().matcher(type.simpleName().name()).matches();
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

    public void setTargetTypeNameMatch(String targetTypeNameMatch) {
        this.targetTypeNameMatch = targetTypeNameMatch;
    }

    public void setRestrictedTypeNameMatch(String restrictedTypeNameMatch) {
        this.restrictedTypeNameMatch = restrictedTypeNameMatch;
    }

    public void setTargetTypeName(String targetTypeName) {
        this.targetTypeName = targetTypeName;
    }

    public void setRestrictedTypeName(String restrictedTypeName) {
        this.restrictedTypeName = restrictedTypeName;
    }
}
