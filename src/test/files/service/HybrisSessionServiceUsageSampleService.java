package org.divy.sonar.hybris.java.checks;

import de.hybris.platform.servicelayer.session.SessionService; // Noncompliant {{Refactor Service to not use SessionService directly}}

public class HybrisHybrisSessionServiceUsageSampleServiceUsageSampleService {

    private SessionService sessionService; // Noncompliant {{Refactor Service to not use SessionService directly}}

    void nonCompliantMethod() {
        SessionService sessionService; // Noncompliant {{Refactor Service to not use SessionService directly}}
    }

    void compliantMethod() {
    }
}
