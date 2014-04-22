Gradle AspectJ for Android plugin
=================================

Usage
-----

Set `ext.aspectjVersion` to your AspectJ version and then `apply plugin: 'aspectj'`:

```groovy
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath 'io.cristian:gradle-android-aspectj:1.1'
    }
}

project {
    ext.aspectjVersion = '1.8.0'
}

apply plugin: 'android'
apply plugin: 'android-aspectj'
```

Use the `aspectPath` or `ajInpath` to specify external aspects or external code to weave, e.g.:

```groovy
dependencies {
    aspectPath "org.example:some-package:some-version"
}
```

By default, `xlint: ignore` is used. Specify a different value for the `xlint` variable of the
`compileAspect` or `compileTestAspect` task to show AspectJ warnings:

```groovy
compileAspect {
    xlint = 'warning'
}
```

It is possible to specify a different value for the `maxmem` variable of the `compileAspect`
to increase or decrease the max heap size:

```groovy
compileAspect {
    maxmem = '1024m'
}
```

License
-------

The project is licensed under the Apache 2.0 license. Most/all of the code
originated from the Spring Security project and was created by Luke Taylor and
Rob Winch to work on Java. Later on was modified by Cristian Castiblanco
to work on Android projects. See `LICENSE` for details.
