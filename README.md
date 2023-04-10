# Asteroid-Radar

Near Earth Asteroids

This is an Android app that displays information on near-Earth asteroids using data from the NASA API. The app is built using Kotlin and MVVM architecture, and the data is gathered using Moshi and Retrofit. The app also uses Room to save the data to a local database for offline access.

Getting Started

To use this app, you will need to obtain an API key from NASA's website. You can obtain a key by registering at NASA API portal.

Once you have your API key, replace the placeholder value in the gradle.properties file with your actual API key:

makefile
Copy code
NASA_API_KEY="your_api_key_here"
Features
Display a list of near-Earth asteroids based on the current date
View detailed information about each asteroid, including its name, size, and closest approach to Earth
Save favorite asteroids to a local database for offline access
Search for asteroids by name
Architecture
This app uses MVVM (Model-View-ViewModel) architecture, which separates the UI logic from the business logic. The app is divided into the following layers:

View: The UI layer, responsible for displaying data to the user and capturing user input.
ViewModel: The business logic layer, responsible for managing the state of the UI and interacting with the data layer.
Model: The data layer, responsible for retrieving and storing data.
The app uses the following libraries:

Retrofit: For networking and API requests
Moshi: For parsing JSON responses from the API
Room: For local data storage

Contributions
Contributions to this project are welcome! If you find any issues or would like to add a feature, feel free to submit a pull request.
