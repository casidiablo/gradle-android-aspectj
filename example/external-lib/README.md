The only purpose of this mini project is to generate a jar
that contains a typical android bug: it calls the
`requestWindowFeature` method after calling `setContentView`.

The idea is that AspectJ should be able to patch this bug
by weaving an @Around advice around this `requestWindowFeature`
and catching the exception.