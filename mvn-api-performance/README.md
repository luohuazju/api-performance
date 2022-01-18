

```shell
mvn gatling:test -Dbuild.env=local -Dgatling.simulationClass=com.sillycat.performance.epjapi.EPJGetAPISimulation
mvn gatling:test -Dbuild.env=local -Dgatling.simulationClass=com.sillycat.performance.epjapi.EPJPostAPISimulation 
```

```shell
mvn gatling:test -Dbuild-env=local
```