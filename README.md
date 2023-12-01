# EldenRingDatabase


### **The project intends to follow the Clean MVVM architecture, which consists of the following layers:**

* Presentation Layer: Contains the UI views and ViewModels.
* Domain Layer: Contains the business logic and use cases.
* Data Layer: Contains data sources, respositories, API communication and music service.
* Dependency Injection: Uses Koin for dependency injection.

### **The following libraries have been used to implement the project:**

* Kotlin Coroutines - For asynchronous programming
* Retrofit - For networking and API communication
* Koin - For dependency injection
* Lottie: For Animations.
* Glide: For Images.
* Room: For local database.


### **The folder structure is as follows:**


* common -> Contains constants aplication and resources state file. 
* data -> Contains repository implementations, data sources, api communication methods and music service.
* domain -> Contains use cases and logic.
* view -> Contains ViewModels and UI views.
* models -> Contains local and remote model classes.
* di -> Contains di module files
