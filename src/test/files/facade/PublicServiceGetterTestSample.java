package org.divy.sonar.hybris.java.checks;

import org.sonar.java.checks.verifier.JavaCheckVerifier; // Compliant

@Facade
public class PublicServiceGetterTestSample {
	
	void compliantMethodService() { // Compliant
        return "valid"; 
    }
}
