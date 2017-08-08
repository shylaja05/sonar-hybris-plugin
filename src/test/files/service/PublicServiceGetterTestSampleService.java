package org.divy.sonar.hybris.java.checks;

import org.sonar.java.checks.verifier.JavaCheckVerifier; // Compliant

public class PublicServiceGetterTestSampleService {

    public productDao getProductDao() { // Noncompliant {{Remove or make private all the public getter methods and properties in Service that make Dao available to Controllers}}
        return "invalid";
    }

    private String getProductDao() { // Compliant
        return "valid";
    }

}
