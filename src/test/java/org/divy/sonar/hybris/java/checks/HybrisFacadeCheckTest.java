package org.divy.sonar.hybris.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class HybrisFacadeCheckTest {

  @Test
  public void checkDaoUsage() {

    JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
  }

  @Test
  public void checkHtmlUsage() {

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
    public void checkSessionUsage() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
    }

    @Test
    public void checkFlexibleSearchUsage() {
        JavaCheckVerifier.verify("src/test/files/MyCustomRule.java", new HybrisFacadeCheck());
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
