package org.divy.sonar.check.java.hybris;

import org.divy.sonar.check.java.generic.GenericLayerUsageCheck;
import org.divy.sonar.check.java.generic.GenericStringLiteralCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class HybrisServiceCheckTest {

  @Test
  public void checkHtmlUsage() {
    GenericStringLiteralCheck check = new GenericStringLiteralCheck();

    check.setTargetTypeName("Service");
    check.setTargetTypeNameMatch("(.)*(Service)$");

    JavaCheckVerifier.verify("src/test/files/service/HtmlUsageTestSampleService.java", check);
  }

  @Test
  public void checkPublicDaoGetter() {
    JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisServiceCheck());
  }

  @Test
  public void checkModelInstantiation() {
    JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisServiceCheck());
  }

  @Test
  public void checkSessionUsage() {
    GenericLayerUsageCheck check = new GenericLayerUsageCheck();

    check.setRestrictedTypeName("SessionService");
    check.setRestrictedTypeNameMatch ("(.)*(SessionService)$");
    check.setTargetTypeName("Service");
    check.setTargetTypeNameMatch("(.)*(Service)$");

    JavaCheckVerifier.verify("src/test/files/service/HybrisSessionServiceUsageSampleService.java", check);
  }

  @Test
  public void checkFlexibleSearchUsage() {
    GenericLayerUsageCheck check = new GenericLayerUsageCheck();

    check.setRestrictedTypeName("FlexibleSearchService");
    check.setRestrictedTypeNameMatch ("(.)*(FlexibleSearchService)$");
    check.setTargetTypeName("Service");
    check.setTargetTypeNameMatch("(.)*(Service)$");

    JavaCheckVerifier.verify("src/test/files/service/HybrisFlexibleSearchUsageSampleService.java", check);
  }
}
