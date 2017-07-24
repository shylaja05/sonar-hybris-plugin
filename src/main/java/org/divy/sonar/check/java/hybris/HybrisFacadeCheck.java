package org.divy.sonar.check.java.hybris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Rule(key = "HybrisFacadeCheck")
public class HybrisFacadeCheck extends IssuableSubscriptionVisitor {
    private Map<Tree.Kind, UsageCheckVisitor> usageChecks = new EnumMap<>(Tree.Kind.class);

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return new ArrayList<>(usageChecks.keySet());
    }

    @Override
    public void visitNode(Tree tree) {
        List<UsageCheckVisitResult> results = usageChecks.get(tree.kind()).visit(tree);

        results.forEach(usageCheckVisitResult -> reportIssue(tree, usageCheckVisitResult.getMessage()));
    }
}
