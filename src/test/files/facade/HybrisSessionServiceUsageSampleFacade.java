package org.divy.sonar.hybris.java.checks;

import de.hybris.platform.servicelayer.session.SessionService; // Noncompliant {{Refactor Facade to not use SessionService directly}}

public class HybrisHybrisSessionServiceUsageSampleServiceUsageSampleFacade {

    private SessionService sessionService; // Noncompliant {{Refactor Facade to not use SessionService directly}}

    void nonCompliantMethod() {
        SessionService sessionService; // Noncompliant {{Refactor Facade to not use SessionService directly}}
    }

    void compliantMethod() {
    }
}
