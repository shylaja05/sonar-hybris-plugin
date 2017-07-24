package org.divy.sonar.check.java.hybris;

import org.divy.sonar.check.java.generic.GenericLayerUsageCheck;
import org.divy.sonar.check.java.generic.GenericStringLiteralCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class HybrisFacadeCheckTest {

    @Test
    public void checkDaoUsage() {

        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }

    @Test
    public void checkPublicServiceGetter() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }

    @Test
    public void checkModelInstantiation() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }

    @Test
    public void checkHtmlUsage() {
        GenericStringLiteralCheck check = new GenericStringLiteralCheck();

        check.setTargetTypeName("Facade");
        check.setTargetTypeNameMatch("(.)*(Facade)$");

        JavaCheckVerifier.verify("src/test/files/facade/HtmlUsageTestSampleFacade.java", check);
    }

    @Test
    public void checkSessionUsage() {
        GenericLayerUsageCheck check = new GenericLayerUsageCheck();

        check.setRestrictedTypeName("SessionService");
        check.setRestrictedTypeNameMatch ("(.)*(SessionService)$");
        check.setTargetTypeName("Facade");
        check.setTargetTypeNameMatch("(.)*(Facade)$");

        JavaCheckVerifier.verify("src/test/files/facade/HybrisSessionServiceUsageSampleFacade.java", check);
    }

    @Test
    public void checkFlexibleSearchUsage() {
        GenericLayerUsageCheck check = new GenericLayerUsageCheck();

        check.setRestrictedTypeName("FlexibleSearchService");
        check.setRestrictedTypeNameMatch ("(.)*(FlexibleSearchService)$");
        check.setTargetTypeName("Facade");
        check.setTargetTypeNameMatch("(.)*(Facade)$");

        JavaCheckVerifier.verify("src/test/files/Facade/HybrisFlexibleSearchUsageSampleFacade.java", check);
    }

    @Test
    public void checkDTOPopulation() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }

    @Test
    public void checkModelPopulation() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }
}
