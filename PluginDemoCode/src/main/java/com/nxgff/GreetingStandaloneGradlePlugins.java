package com.nxgff;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GreetingStandaloneGradlePlugins implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("GreetingStandaloneGradlePlugins(standalone) ---> apply");
        project.task("helloStandalone").doLast(task -> System.out.println("Hello from the com.nxg.plugins.GreetingStandaloneGradlePlugins(standalone)"));
    }
}
