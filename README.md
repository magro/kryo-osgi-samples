# Kryo OSGI Samples

Samples that run kryo in an OSGI environment.

## Usage

```bash
$ mvn install
$ mvn -f run.xml -P simple
```

If the latter command prints

```
Serializing String: OK
Serializing MyClass: OK
```

at the end then everything is ok, kryo was used successfully in an OSGI container.

You can exit the osgi shell by entering `close`.
