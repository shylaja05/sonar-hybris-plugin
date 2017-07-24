package org.divy.sonar.check.java.generic;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class GenericGetterUsageCheckTest {
    GenericGetterUsageCheck check = new GenericGetterUsageCheck();
    private static String restrictedTypeName = "Service";
    private static String restrictedTypeNameMatch = "(.)*(Service|service)$";
    private static String targetTypeName = "Facade";
    private static String targetTypeNameMatch = "(.)*(Facade)$";

    @Test
    public void checkNoIssue() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = targetTypeName;
        check.targetTypeNameMatch = targetTypeNameMatch;
        JavaCheckVerifier.verifyNoIssue("src/test/files/facade/PublicServiceGetterTestSample.java", check);
    }

    @Test
    public void detected() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = targetTypeName;
        check.targetTypeNameMatch = targetTypeNameMatch;
        JavaCheckVerifier.verify("src/test/files/facade/PublicServiceGetterTestSampleFacade.java", check);
    }
}