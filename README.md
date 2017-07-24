[![Stories in Ready](https://badge.waffle.io/divyakumarjain/sonar-hybris-plugin.png?label=ready&title=Ready)](https://waffle.io/divyakumarjain/sonar-hybris-plugin?utm_source=badge)
[![Build Status](https://travis-ci.org/divyakumarjain/sonar-hybris-plugin.svg?branch=master)](https://travis-ci.org/divyakumarjain/sonar-hybris-plugin)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=org.divy%3Asonar-hybris-plugin)](https://sonarqube.com/dashboard?id=org.divy%3Asonar-hybris-plugin)
[![Gitter](https://badges.gitter.im/sonar-hybris-plugin.svg)](https://gitter.im/sonar-hybris-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# About Hybris Rules for SonarQube




## Purpose

SonarQube is a leading tool to monitor code quality. This plugin helps monitor code issues for Hybris implementation
 

# License
Licensed under the Apache License, Version 2.0

## Installation

```commandline
mvn package
cp ./target/sonar-hybris-plugin-<version>.jar /opt/sonarqube/extensions/plugins
```

## Developer Guide lines

Following are guideline for writing plugin for Sonar

* https://docs.sonarqube.org/display/DEV/Coding+Rule+Guidelines
* https://docs.sonarqube.org/display/PLUG/Custom+Rules+for+Java


For writing better commit message Leverage commitizen when doing git commit and raising Pull request.

http://commitizen.github.io/cz-cli/

```commandline
npm install

npm install commitizen -g

commitizen init cz-conventional-changelog

```

Commits can generated using following command

```commandline
git cz
```
Change logs can be generated when releasing by running below command

```commandline
// Need to run this if conventional-changelog is not already installed 
npm install -g conventional-changelog-cli  

conventional-changelog -p angular -i CHANGELOG.md -s 
```