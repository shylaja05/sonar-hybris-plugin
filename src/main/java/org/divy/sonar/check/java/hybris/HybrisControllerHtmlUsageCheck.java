package org.divy.sonar.check.java.hybris;

import org.divy.sonar.check.java.generic.AbstractStringLiteralCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.regex.Pattern;

@Rule(
        key = "HybrisControllerHtmlUsageCheck",
        priority = Priority.MINOR,
        name = "Controller should not define html layout classes directly",
        tags = {"hybris"},
        description = "Return view template names from controller and let view template render the layer instead of controller classes directly defining the layout"
)
public class HybrisControllerHtmlUsageCheck extends AbstractStringLiteralCheck {

    private static final String SPRING_CONTROLLER_ANNOTATION = "org.springframework.stereotype.Controller";
    private static final String SPRING_REST_CONTROLLER_ANNOTATION = "org.springframework.web.bind.annotation.RestController";

    private static final Pattern TARGET_TYPE_NAME_MATCH = Pattern.compile("(.)*Controller$", Pattern.CASE_INSENSITIVE);

    private static final Pattern HTML_PATTERN = Pattern.compile("\"(</?(\\s*\"[^\"]*\"|'[^']*'|[^'\">])*>)+\"");

    @Override
    protected boolean isTargetedType(ClassTree type) {
        return isAnnotatedWith(type, SPRING_CONTROLLER_ANNOTATION)
                || isAnnotatedWith(type, SPRING_REST_CONTROLLER_ANNOTATION)
                || hasTargetedTypeName(type);
    }

    @Override
    protected boolean hasTargetedTypeName(ClassTree type) {
        return TARGET_TYPE_NAME_MATCH.matcher(type.simpleName().name()).matches();
        }

    @Override
    protected String getMessage(Tree tree) {
        return "Refactor Controller to nor use html tags directly and let view template define the tags";
    }

    @Override
    protected Pattern resolveRestrictedCompiledPattern() {
        return HTML_PATTERN;
    }
}
