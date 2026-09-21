# News Reader Android App

Kotlin + Jetpack Compose News Reader using NewsAPI, Retrofit, Coroutines/Flow, MVVM, StateFlow, Navigation Compose and Coil.

## Setup

1. Open the project in Android Studio.
2. Copy `local.properties.example` to `local.properties`.
3. Add your NewsAPI key:
   `NEWS_API_KEY=YOUR_REAL_KEY`
4. Sync Gradle.
5. Run the app on an emulator or physical Android device.

The app requests top headlines for Nigeria (`country=ng`).

## API key security

`local.properties` is ignored by Git. The key is read by Gradle and exposed to the app as `BuildConfig.NEWS_API_KEY`.

Important: an API key bundled into a mobile APK can ultimately be extracted by a determined attacker. For a production app, proxy NewsAPI requests through your own backend.

## Screens

- Headlines list: thumbnail, title and source.
- Article details: image, title, source, author, date, description and original-article button.
- Loading, error and empty states.
