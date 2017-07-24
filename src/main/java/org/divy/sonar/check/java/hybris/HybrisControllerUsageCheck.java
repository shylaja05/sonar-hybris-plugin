package org.divy.sonar.check.java.hybris;

import org.divy.sonar.check.java.generic.AbstractLayerUsageCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.regex.Pattern;

@Rule(
        key = "HybrisControllerUsageCheck",
        priority = Priority.MINOR,
        name = "Controller should not use DAO/Model/Service classes directly",
        tags = {"hybris"},
        description = "Use facade classes in controller instead of using DAO/Model/Service classes directly"
)
public class HybrisControllerUsageCheck extends AbstractLayerUsageCheck {

    private static final String SPRING_CONTROLLER_ANNOTATION = "org.springframework.stereotype.Controller";
    private static final String SPRING_REST_CONTROLLER_ANNOTATION = "org.springframework.web.bind.annotation.RestController";
    private static final String DAO_MESSAGE = "Refactor Controller to use facade instead of dao directly";
    private static final String MODEL_MESSAGE = "Refactor Controller to use DTO from request or a facade instead of using models";
    private static final String SERVICE_MESSAGE = "Refactor Controller to use facade instead of service directly";

    private static final Pattern TARGET_TYPE_NAME_MATCH = Pattern.compile("(.)*Controller$", Pattern.CASE_INSENSITIVE);
    private static final Pattern DAO_RESTRICTED_TYPE_NAME_MATCH = Pattern.compile("(.)*dao$", Pattern.CASE_INSENSITIVE);
    private static final Pattern MODEL_RESTRICTED_TYPE_NAME_MATCH = Pattern.compile("(.)*Model$");
    private static final Pattern SERVICE_RESTRICTED_TYPE_NAME_MATCH = Pattern.compile("(.)*service$", Pattern.CASE_INSENSITIVE);

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
    protected boolean isRestricted(Tree tree) {
        String typeName = tree.lastToken().text();
        return DAO_RESTRICTED_TYPE_NAME_MATCH.matcher(typeName).matches()
        		|| MODEL_RESTRICTED_TYPE_NAME_MATCH.matcher(typeName).matches()
        		|| SERVICE_RESTRICTED_TYPE_NAME_MATCH.matcher(typeName).matches();
    }

    @Override
    protected String getMessage(Tree tree) {
    	String typeName = tree.lastToken().text();
    	return DAO_RESTRICTED_TYPE_NAME_MATCH.matcher(typeName).matches() ? DAO_MESSAGE : 
    		(MODEL_RESTRICTED_TYPE_NAME_MATCH.matcher(typeName).matches() ? MODEL_MESSAGE : SERVICE_MESSAGE);
    }

}
