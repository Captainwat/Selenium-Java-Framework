# Selenium Java Framework

![Java CI with Maven](https://github.com/Captainwat/Selenium-Java-Framework/actions/workflows/maven.yml/badge.svg)

This is my pet project for practicing UI and API test automation with Java.

I built it as a small automation framework to cover common web testing flows, work with Page Object Model, practice API testing with Rest Assured, and run everything through Maven and GitHub Actions. The UI part is based on SauceDemo, and the API part uses Restful Booker.

The goal of this project was not to build some huge enterprise framework, but to create something clean, practical, and good enough to show my skills in test automation.

## Tech stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Rest Assured
- Allure
- GitHub Actions

## What is covered

### UI tests
The UI part covers the main purchase flow and a few validation scenarios on SauceDemo, including:

- successful login
- login with invalid credentials
- login with locked user
- opening product details
- verifying product details by item id
- completing an order
- checkout validation errors
- sorting by price
- sorting by product name

### API tests
The API part covers basic CRUD-style checks for Restful Booker, including:

- health check
- getting booking ids
- creating a booking
- getting a single booking
- updating a booking
- deleting a booking
- getting auth token

## Project structure

The project uses Page Object Model for UI tests.

- `pages` — page objects for UI interactions
- `tests` — UI and API test classes
- `utils` — config and test data helpers
- `driver` — browser setup
- `.github/workflows` — GitHub Actions configuration

## How to run the tests

### Run all tests
```bash
mvn clean test