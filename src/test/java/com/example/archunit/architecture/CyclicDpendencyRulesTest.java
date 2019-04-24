package com.example.archunit.architecture;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.example.archunit")
public class CyclicDpendencyRulesTest {

    @ArchTest
    public static final ArchRule no_cycles_dependencies =
    		slices().matching("..(*)..")
            		.should().beFreeOfCycles();
}
