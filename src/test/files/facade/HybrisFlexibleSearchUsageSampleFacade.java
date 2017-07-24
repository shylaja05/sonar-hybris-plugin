package org.divy.sonar.hybris.java.checks;

import de.hybris.platform.servicelayer.search.FlexibleSearchService; // Noncompliant {{Refactor Facade to not use FlexibleSearchService directly}}

public class HybrisFlexibleSearchUsageSampleFacade {

    private FlexibleSearchService flexibleSearchService; // Noncompliant {{Refactor Facade to not use FlexibleSearchService directly}}

    void nonCompliantMethod() {
        FlexibleSearchService flexibleSearchService; // Noncompliant {{Refactor Facade to not use FlexibleSearchService directly}}
    }

    void compliantMethod() {
    }
}
