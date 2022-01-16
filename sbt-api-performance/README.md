# api-performance
Scala Gatling to Verify Performance

### build

```shell
sbt clean compile package
```

```shell
sbt test -Dbuild.env=stage
```


# First Testing #

```shell
sbt -Dbuild.env=local "Gatling/testOnly com.sillycat.performance.epjapi.EPJGetAPISimulation"
sbt -Dbuild.env=local "testOnly com.sillycat.performance.epjapi.ClassifierPostIndustrySimulation"
```


# For Production #
```shell
sbt -Dbuild.env=prod "testOnly com.j2c.performance.classifier.ClassifierPostIndustrySimulation"
```


# More Params #
```shell
-Denv.stage.ramp.user.num=1
-Denv.stage.requests.per.user=1
```
