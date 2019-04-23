package com.example.archunit.architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.example.archunit")
public class LayeredArchitectureTest {
	
    @ArchTest
    public static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

            .layer("Application").definedBy("com.example.archunit.application..")
            .layer("Domain").definedBy("com.example.archunit.domain..")
            .layer("Infrastructure").definedBy("com.example.archunit.infrastructure..")

            .whereLayer("Application").mayNotBeAccessedByAnyLayer()
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure")
            .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Application");

}
