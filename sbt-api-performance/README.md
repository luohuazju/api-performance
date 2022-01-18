# api-performance
Scala Gatling to Verify Performance

### build

```shell
sbt clean compile package
```


# First Testing #

```shell
sbt -Dbuild.env=local "Gatling/testOnly com.sillycat.performance.epjapi.EPJGetAPISimulation"
sbt -Dbuild.env=local "Gatling/testOnly com.sillycat.performance.epjapi.EPJPostAPISimulation"
```

#Run all #
```shell
sbt -Dbuild.env=local "Gatling/test"
```

# For Production #
```shell
sbt -Dbuild.env=prod "Gatling/testOnly com.sillycat.performance.epjapi.EPJGetAPISimulation"
```


# More Params #
```shell
-Denv.stage.ramp.user.num=1
-Denv.stage.requests.per.user=1
```
