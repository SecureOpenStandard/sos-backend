#!/bin/bash

pushd gen || bin/gen.sh "yes" &&  pushd gen || exit;
  mvn spring-boot:run
popd || exit;
