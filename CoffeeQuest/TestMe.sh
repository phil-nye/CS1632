#! /bin/bash

# Run tests

java -cp ./:junit-4.12.jar:mockito-all-1.10.19.jar org.junit.runner.JUnitCore InventoryTests RoomTests PlayerTests
