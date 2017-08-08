package org.divy.sonar.check.java.generic;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class GenericGetterUsageCheckTest {
    GenericGetterUsageCheck check = new GenericGetterUsageCheck();
    private static String restrictedTypeName = "Service";
    private static String restrictedTypeNameMatch = "(.)*(Service|service)$";
    private static String facadeTargetTypeName = "Facade";
    private static String facadeTypeNameMatch = "(.)*(Facade)$";
    private static String daoTargetTypeName = "Dao";
    private static String daoTypeNameMatch = "(.)*(Dao)$";

    @Test
    public void checkFacadeNoIssue() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = facadeTargetTypeName;
        check.targetTypeNameMatch = facadeTypeNameMatch;
        JavaCheckVerifier.verifyNoIssue("src/test/files/facade/PublicServiceGetterTestSample.java", check);
    }

    @Test
    public void detectedFacade() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = facadeTargetTypeName;
        check.targetTypeNameMatch = facadeTypeNameMatch;
        JavaCheckVerifier.verify("src/test/files/facade/PublicServiceGetterTestSampleFacade.java", check);
    }
    
    @Test
    public void checkDaoNoIssue() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = daoTargetTypeName;
        check.targetTypeNameMatch = daoTypeNameMatch;
        JavaCheckVerifier.verifyNoIssue("src/test/files/facade/PublicServiceGetterTestSample.java", check);
    }

    @Test
    public void detectedDao() {
        check.restrictedTypeName = restrictedTypeName;
        check.restrictedTypeNameMatch = restrictedTypeNameMatch;
        check.targetTypeName = daoTargetTypeName;
        check.targetTypeNameMatch = daoTypeNameMatch;
        JavaCheckVerifier.verify("src/test/files/Dao/PublicServiceGetterTestSampleDao.java", check);
    }
}