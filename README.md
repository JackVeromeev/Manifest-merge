# Salesforce manifest merge tool

This merge tool is a helping hand in case you have multiple Salesforce manifests that need to be combined some way: summarized together or found the components that both contain.

## Build

You need Java 8+ and Maven 3

```shell
# build the package
mvn clean install;

# copy the result file to the parent
mv target/manifest-merge-0.1-IN\ DEVEOPMENT-jar-with-dependencies.jar .

# rename to the short name
mv manifest-merge-0.1-IN\ DEVEOPMENT-jar-with-dependencies.jar manifest-merge.jar
```

## Usage

Use the built jar file "with dependencies"

```shell
java -jar manifest-merge.jar STRATEGY target.xml source1.xml soutce2.xml [...source.xml files]
```

Additional flags:

- `-h` or `--help` to print the usage. You can also write the `java -jar manifest-merge.jar HELP`

## Implemented component merge strategies

- [x] UNIFY (A ∩ B), allows as many sources as entered, at least 2
- [x] INTERSECT (A ∪ B), allows as many sources as entered, at least 2
- [x] SUBTRACT (A ∖ B), remove elements of source 2 from source 1

## Implemented version choose strategy

- [x] Highest
- [x] Lowest
- [x] Custom
- [x] Form the first package
- [x] From the last package
- [ ] Set as separate CLI argument <- WIP, currently takes the highest version, but can be changed through the source code

## Implemented package name and description strategy

- [x] Custom
- [x] Form the first package
- [x] From the last package
- [ ] Set as separate CLI argument <- WIP, currently takes nulls, but can be changed through the source code

## License

MIT