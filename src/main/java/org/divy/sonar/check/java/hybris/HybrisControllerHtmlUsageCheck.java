package org.divy.sonar.check.java.hybris;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Rule(
        key = "HybrisControllerHtmlUsageCheck",
        priority = Priority.MINOR,
        name = "Controller should not define html layout classes directly",
        tags = {"hybris"},
        description = "Return view template names from controller and let view template render the layer instead of controller classes directly defining the layout"
)
public class HybrisControllerHtmlUsageCheck extends IssuableSubscriptionVisitor {

    private static final Pattern HTML_PATTERN = Pattern.compile("\"(</?(\\s*\"[^\"]*\"|'[^']*'|[^'\">])*>)+\"");

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.STRING_LITERAL);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.STRING_LITERAL) && isTag((LiteralTree) tree)) {
            context.reportIssue(this, tree, "Refactor Controller to not use html tags directly and let view template define the tags");
        }
    }

    private boolean isTag(LiteralTree tree) {
        return HTML_PATTERN.matcher(tree.value()).matches();
    }
}
