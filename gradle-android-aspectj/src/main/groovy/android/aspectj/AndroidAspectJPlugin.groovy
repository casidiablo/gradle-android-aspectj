package android.aspectj

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException

/**
 * @author Luke Taylor
 * @author Mike Noordermeer
 * @author Cristian Castiblanco
 */
class AndroidAspectJPlugin implements Plugin<Project> {

  void apply(Project project) {
    def variants = null;
    if (project.plugins.findPlugin("android")) {
      variants = "applicationVariants";
    } else if (project.plugins.findPlugin("android-library")) {
      variants = "libraryVariants";
    } else {
      throw new ProjectConfigurationException("The android or android-library plugin must be applied to the project", null)
    }

    if (!project.hasProperty('aspectjVersion')) {
      throw new GradleException("You must set the property 'aspectjVersion' before applying the aspectj plugin")
    }

    if (project.configurations.findByName('ajtools') == null) {
      project.configurations.create('ajtools')
      project.dependencies {
        ajtools "org.aspectj:aspectjtools:${project.aspectjVersion}"
        compile "org.aspectj:aspectjrt:${project.aspectjVersion}"
      }
    }

    for (configuration in ['aspectPath', 'ajInpath']) {
      if (project.configurations.findByName(configuration) == null) {
        project.configurations.create(configuration)
      }
    }

    project.afterEvaluate {
      project.android[variants].all { variant ->
        // name of the new task compileXxxxAspect
        def newTaskName = 'compile' + variant.name.capitalize() + 'Aspect'

        project.tasks.create(name: newTaskName, overwrite: true, description: 'Compiles AspectJ Source', type: AndroidAjc) {
          destinationDir = variant.javaCompile.destinationDir
          classpath = variant.javaCompile.classpath
          sourceCollections = variant.javaCompile.source.asFileTree
          androidBootClasspath = project.android.plugin.getRuntimeJars()

          inputs.files(variant.javaCompile.source.sourceCollections)
          outputs.dir(destinationDir)

          aspectPath = project.configurations.aspectPath
          ajInpath = project.configurations.ajInpath
        }

        def compileAspect = project.tasks.getByName(newTaskName)
        compileAspect.setDependsOn(variant.javaCompile.dependsOn)
        variant.javaCompile.deleteAllActions()
        variant.javaCompile.dependsOn compileAspect
      }
    }
  }
}

