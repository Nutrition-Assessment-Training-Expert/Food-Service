package com.natehealth.foodservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.natehealth.foodservice");

        noClasses()
            .that()
                .resideInAnyPackage("com.natehealth.foodservice.service..")
            .or()
                .resideInAnyPackage("com.natehealth.foodservice.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.natehealth.foodservice.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
