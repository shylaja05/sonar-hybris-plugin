package org.divy.sonar.check.java.generic;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class GenericGetterUsageCheckTest {
    GenericGetterUsageCheck check = new GenericGetterUsageCheck();
    private static String facadeRestrictedTypeName = "Service";
    private static String facadeRestrictedTypeNameMatch = "(.)*(Service|service)$";
    private static String facadeTargetTypeName = "Facade";
    private static String facadeTypeNameMatch = "(.)*(Facade)$";
    private static String serviceRestrictedTypeName = "Dao";
    private static String serviceRestrictedTypeNameMatch = "(.)*(Dao|dao)$";
    private static String serviceTargetTypeName = "Service";
    private static String serviceTypeNameMatch = "(.)*(Service)$";

    @Test
    public void checkFacadeNoIssue() {
        check.restrictedTypeName = facadeRestrictedTypeName;
        check.restrictedTypeNameMatch = facadeRestrictedTypeNameMatch;
        check.targetTypeName = facadeTargetTypeName;
        check.targetTypeNameMatch = facadeTypeNameMatch;
        JavaCheckVerifier.verifyNoIssue("src/test/files/facade/PublicServiceGetterTestSample.java", check);
     }

    @Test
    public void detectedFacade() {
        check.restrictedTypeName = facadeRestrictedTypeName;
        check.restrictedTypeNameMatch = facadeRestrictedTypeNameMatch;
        check.targetTypeName = facadeTargetTypeName;
        check.targetTypeNameMatch = facadeTypeNameMatch;
        JavaCheckVerifier.verify("src/test/files/facade/PublicServiceGetterTestSampleFacade.java", check);
    }
    
    @Test
    public void checkDaoNoIssue() {
        check.restrictedTypeName = serviceRestrictedTypeName;
        check.restrictedTypeNameMatch = serviceRestrictedTypeNameMatch;
        check.targetTypeName = serviceTargetTypeName;
        check.targetTypeNameMatch = serviceTypeNameMatch;
        JavaCheckVerifier.verifyNoIssue("src/test/files/facade/PublicServiceGetterTestSample.java", check);
    }

    @Test
    public void detectedDao() {
        check.restrictedTypeName = serviceRestrictedTypeName;
        check.restrictedTypeNameMatch = serviceRestrictedTypeNameMatch;
        check.targetTypeName = serviceTargetTypeName;
        check.targetTypeNameMatch = serviceTypeNameMatch;
        JavaCheckVerifier.verify("src/test/files/service/PublicServiceGetterTestSampleService.java", check);
    }
}