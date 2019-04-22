package com.example.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.example.archunit")
public class InterfaceRulesTest {

    @ArchTest
    public static final ArchRule interfaces_should_not_have_names_ending_with_the_word_interface =
            noClasses().that().areInterfaces()
            		   .should().haveNameMatching(".*Interface");
    
    @ArchTest
    public static final ArchRule interfaces_must_not_be_placed_in_implementation_packages =
            noClasses().that().resideInAPackage("..impl..")
            		   .should().beInterfaces();

}
