package com.example.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.example.archunit")
public class HexagonalArchitectureRulesTest {
	
    @ArchTest
    public static final ArchRule domain_should_only_depend_on_persistence_or_other_services =
    		classes().that().resideInAPackage("..domain..")
    				 .should().onlyDependOnClassesThat().resideInAnyPackage("..domain..", "java..");
	
    @ArchTest
    public static final ArchRule application_should_not_depend_on_infrastructure =
    		noClasses().that().resideInAPackage("..application..")
	                    .should().dependOnClassesThat().resideInAPackage("..infrastructure..");	
	
}
