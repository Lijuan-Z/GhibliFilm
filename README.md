# Ghibli Film

## Overview
This Android application provides users with a list of Studio Ghibli films and allows them to manage a personal wishlist. The app fetches film data from an external API, displays it using a modern Android UI, and enables users to add or remove films from their wishlist, which is stored locally in a Room database.

## Features
- **Film Repository**: Fetches data from an external API using `HttpClient` with `Gson` for JSON parsing.
- **Wishlist Management**: Allows users to add or remove films from a wishlist, which is persisted locally using Room.
- **Jetpack Compose**: Utilizes Jetpack Compose for building a reactive and modern UI.
- **Room Database**: Manages local data storage for the wishlist.

## Architecture
The app follows a repository pattern with a clean separation of concerns:
- **MyApp Class**: Serves as the application class, initializing the `HttpClient` and Room database, and providing repositories for film data and wishlist management.
- **MainActivity**: The main entry point of the app, setting up the UI and managing the film and wishlist states.

## Libraries Used
- **Ktor Client**: For making HTTP requests to the external API.
- **Gson**: For JSON serialization and deserialization.
- **Room**: For local database storage and management.
- **Jetpack Compose**: For building the UI in a declarative way.

## Getting Started
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Project Structure
- **`MyApp`**: Initializes the HTTP client and Room database, provides repositories.
- **`MainActivity`**: Sets up the main UI and manages film and wishlist states.
- **`FilmRepository`**: Fetches film data from the external API.
- **`WishListRepository`**: Manages the wishlist data stored in the Room database.

## Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue for suggestions and improvements.

## License
This project is licensed under the MIT License.
