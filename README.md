# Weather App

Welcome to the Weather App repository! This application is built with modern technologies to provide a seamless weather forecasting experience. Below is a brief overview of the technologies used and a structured menu for exploring specific details.

## Technology Overview

- **Architecture:** MVVM (Model-View-ViewModel)
- **Dependency Injection:** Hilt Dagger
- **Local Storage:** Room Database
- **API Calls:** Retrofit
- **UI:** Jetpack Compose
- **Image Loading:** Coil

Explore further details and code structure in the sections below.

<details>
  <summary><b>Dependencies</b></summary>

## Dependencies

To manage dependencies efficiently, the following dependencies are incorporated into the project:

### Hilt
Dependency injection framework

### Room
Local database for efficient data storage

### Retrofit
Library for making API calls

### LiveData and ViewModel
Used for managing app logic and observing data in Compose functions

</details>

<details>
  <summary><b>Setup and Compatibility</b></summary>

## Setup and Compatibility

### Gradle Files

Fixed Kotlin version compatibility issues in Gradle scripts.

Updated Kotlin plugin version to 1.8.10 and Jetpack Compose version to 1.4.3.

Resolved conflicts and ensured proper setup for Dagger Hilt integration.

These changes address compatibility concerns, ensuring seamless integration of Kotlin, Jetpack Compose, and Dagger Hilt within the project.

</details>

<details>
  <summary><b>Data Management</b></summary>

## Data Management

To ensure stability and prevent errors from affecting the
## Data Management

To ensure stability and prevent errors from affecting the local database, the data is segregated into different entities:

### NetworkEntity
Represents data received from API

### DomainEntity
Acts as an intermediary for UI display

### CacheEntity
Persists data locally for offline use

Data flow is maintained as follows: `NetworkEntity -> DomainEntity -> CacheEntity`. Specific mappers for each entity category are introduced to ensure consistency and robust data handling.

</details>




<details>
  <summary><b>Video Links, Photos, and API Key</b></summary>

## Video Links, Photos, and API Key

### Video Links

[Watch the App Ui design](https://youtu.be/lCjYBXt5rlw)

[Watch the App fully implemented](https://youtu.be/E-CKEpr21Mg)


### Photos

![Weather App](https://raw.githubusercontent.com/mostafa-n3ma/WeatherApp/master/Screenshot%202024-01-11%20172531.png)
![Weather App](https://raw.githubusercontent.com/mostafa-n3ma/WeatherApp/master/Screenshot%202024-01-11%20172628.png)
![Weather App](https://github.com/mostafa-n3ma/WeatherApp/blob/master/Screenshot%202024-01-11%20172721.png?raw=true)


### API Website

https://www.weatherapi.com/login.aspx

### API Key

Note that the API key is stored locally in the repository for personal safety.

Feel free to explore the app and provide feedback. If you encounter any issues or have suggestions for improvement, please open an issue in the repository.

</details>
