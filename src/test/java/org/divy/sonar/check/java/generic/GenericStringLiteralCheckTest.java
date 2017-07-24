package org.divy.sonar.check.java.generic;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;


public class GenericStringLiteralCheckTest {
    @Test
    public void checkNoIssue() {
        GenericStringLiteralCheck check = new GenericStringLiteralCheck();

        check.setTargetTypeName("Controller");
        check.setTargetTypeNameMatch("(.)*(Controller)$");

        JavaCheckVerifier.verifyNoIssue("src/test/files/controller/HybrisCheckNoUsageIssue.java" , check);
    }

    @Test
    public void checkHtmlUsage() {
        GenericStringLiteralCheck check = new GenericStringLiteralCheck();

        check.setTargetTypeName("Controller");
        check.setTargetTypeNameMatch("(.)*(Controller)$");

        JavaCheckVerifier.verify("src/test/files/controller/HtmlUsageTestSampleController.java", check);
    }
}