package android.aspectj

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class AndroidAjc extends DefaultTask {

  File destinationDir
  FileCollection classpath
  FileCollection sourceCollections
  String androidBootClasspath
  FileCollection aspectPath
  FileCollection ajInpath

  // ignore or warning
  String xlint = 'ignore'

  String maxmem

  AndroidAjc() {
    logging.captureStandardOutput(LogLevel.INFO)
  }

  @TaskAction
  def compile() {
    logger.info("=" * 30)
    logger.info("Running ajc ...")
    logger.info("classpath: $classpath.asPath")

    def iajcArgs = [classpath           : classpath.asPath,
                    destDir             : destinationDir,
                    bootclasspath       : androidBootClasspath,
                    source              : project.convention.plugins.java.sourceCompatibility,
                    target              : project.convention.plugins.java.targetCompatibility,
                    inpath              : ajInpath.asPath,
                    xlint               : xlint,
                    fork                : 'true',
                    aspectPath          : aspectPath.asPath,
                    sourceRootCopyFilter: '**/*.java,**/*.aj',
                    showWeaveInfo       : 'true']

    if (maxmem != null) {
      iajcArgs['maxmem'] = maxmem
    }

    ant.taskdef(resource: "org/aspectj/tools/ant/taskdefs/aspectjTaskdefs.properties", classpath: project.configurations.ajtools.asPath)
    ant.iajc(iajcArgs) {
      sourceroots {
        sourceCollections.each {
          logger.info("   sourceRoot $it")
          pathelement(location: it.parent)
        }
      }
    }
  }
}
