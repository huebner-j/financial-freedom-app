# financial-freedom-app

[![Java CI](https://github.com/huebner-j/financial-freedom-app/workflows/Java%20CI/badge.svg)](https://github.com/huebner-j/financial-freedom-app/workflows/Java%20CI/badge.svg)

The financial freedom app enables private individuals to plan and track their path to early retirement. 

# What this repository is about

Technically this project contains a bunch of microservices written in different programming languages and frameworks.
Furthermore, all services are automatically tested and released using a CI / CD pipeline implemented by [github actions](.github/workflows).
Finally, Docker images are built from the program artifacts so that they can be easily deployed in AWS. 

Let's take a closer look to the services and their purpose:

* [simple-calculation-service](./simple-calculation-service) - This service provides a simple RESTful API to 
calculate the year an individual can reach financial freedom based on currently valid parameters, 
i.e. seed capital, monthly saving rate, expected interest and desired monthly income. The service 
is written in Java using [Dropwizard](https://www.dropwizard.io/en/latest/) framework.

# Semantic versioning

This project uses conventional commits and semantic versioning to implement a simple and automatic 
release process. If you would like to collaborate on this project, please make sure your commit 
messages follow the structure of conventional commits according to 
[https://www.conventionalcommits.org/en/v1.0.0/](https://www.conventionalcommits.org/en/v1.0.0/) 