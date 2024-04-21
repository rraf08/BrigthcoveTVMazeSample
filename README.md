# Brightcove Services - Mobile Coding Sample

## Development Notes:

For this project was integrated architecture using MVVM + Clean Architecture, about technology stack:

- Hilt (DI)
- Kotlin
- Coroutines(Flow)
- Jetpack Compose
- Retrofit

Decision of including Clean Architecture package structure is because, it gives us more scalability and modularity in case of extension in the functionality, maintainance  and avoid code duplicity with generic implementations.
About MVVM, also gives us granularity and and higher scope in decoupling UI from bussiness logic. Besides the event driven structure aligned to the use cases implementation included by Clean Architecture vs an opposite MVP pattern which is more coupled to the view and more difficult to integrate for example with Compose UIs.
Even on this implementation was included only a remote data source this structure allow us to include easily local data source like databases.
