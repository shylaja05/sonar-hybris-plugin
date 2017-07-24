package org.divy.sonar.hybris.java.checks;

import de.hybris.platform.servicelayer.search.FlexibleSearchService; // Noncompliant {{Refactor Service to not use FlexibleSearchService directly}}

public class HybrisFlexibleSearchUsageSampleService {

    private FlexibleSearchService flexibleSearchService; // Noncompliant {{Refactor Service to not use FlexibleSearchService directly}}

    void nonCompliantMethod() {
        FlexibleSearchService flexibleSearchService; // Noncompliant {{Refactor Service to not use FlexibleSearchService directly}}
    }

    void compliantMethod() {
    }
}
