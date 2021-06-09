<p align="center">
  <a href="https://yavin.dev">
    <img alt="yavin-logo" src="assets/yavin-logo-transparent.svg" height="150px"/>
  </a>
</p>
<h1 align="center">Yavin</h1>
<p align="center">Rapidly build production quality analytics applications</p>
<p align="center">
    <a href="https://yavin.dev">Docs</a> - <a href="https://yavin-dev.github.io/framework">Demo</a> - <a href="https://github.com/yavin-dev/framework/discussions">Community</a>
</p>

## Yavin App

Yavin is a framework for rapidly building custom data applications that offers both a UI and an API. Yavin can also be deployed as a standalone business intelligence tool in a few simple steps. Build reports, assemble dashboards, and explore data with ad-hoc queries.

This is an example app built using the [Yavin Framework](https://github.com/yavin-dev/framework) showing a [Netflix dataset](https://www.kaggle.com/shivamb/netflix-shows) that is sourced from [Kaggle](https://www.kaggle.com/) data.

## Getting Started

### Quick start (no build required)
Launch demo app using latest yavin-app jar
`curl https://raw.githubusercontent.com/yavin-dev/app/master/yavin-run.sh | bash`

### Pre-requisites for local build
-  Install Java 8 or greater (more info [here](https://yavin.dev/pages/guide/02-start.html#prerequisites))
- `git clone https://github.com/yavin-dev/app.git`
- `cd yavin-app`

### Demo Data
The default build comes bundled with a demo data source running in H2 (in memory). To disable or remove the demo data source please do the following to the webservice build file (`ws/src/build.gradle.kts`):

Comment or remove following line and run the build commands.

`implementation("dev.yavin","demo-config","0.10")`

### Start Yavin App

- `./gradlew bootRun`
- Open [http://localhost:8080](http://localhost:8080)

### Build & Test Deployable Jar

- Build & Test:`./gradlew build`
- Build Only:`./gradlew build -x test`
- Build For Prod:`./gradlew build -Penvironment=production`
- Run Jar: `java -jar ws/build/libs/yavin-app*.jar`

### Run Tests

- Test All: `./gradlew test`
- Test UI: `./gradlew ui:test`
- Test WS: `./gradlew ws:test`
 
### Clean Build

- Clean All: `./gradlew clean`
- Clean UI: `./gradlew ui:clean`
- Clean WS: `./gradlew ws:clean`

### Customize

- Define your [DB config](./ws/src/main/resources/demo-configs/db/sql/DemoConnection.hjson) (currently loads data via the [create-demo-data.sql](./ws/src/main/resources/create-demo-data.sql) script)
- Define your [table config](./ws/src/main/resources/demo-configs/models/tables/DemoTables.hjson)
- Check out our [installation guide](https://yavin.dev/pages/guide/03-start.html#yavin-detailed-installation-guide) for more info

## Resources

[Yavin framework resources](https://github.com/yavin-dev/framework#resources)

More documentation can be found on [yavin.dev](https://yavin.dev)

## License

This project is licensed under the [MIT License](LICENSE.md).
