package org.divy.sonar.hybris.java.checks;

import org.sonar.java.checks.verifier.JavaCheckVerifier; // Compliant

@Facade
public class PublicServiceGetterTestSampleFacade {

    public productService getProductService() { // Noncompliant {{Remove or make private all the public getter methods and properties that make Services available to Controllers}}
        return "invalid";
    }

    private String getService() { // Compliant
        return "valid";
    }

}
