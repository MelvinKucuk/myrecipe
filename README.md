# My Recipe

App that connects with this [Mockable.io](https://demo2328879.mockable.io/recipes) to fetch a list of mocked recipes. The user can search by recipe name or ingridient, can look at a detail screen of the recipe and also a map view where the recipe comes from.

I take the approach of a MVI architechture with Event based UI actions to manage the interaction between the viewModel and de UI. I used Jetpacl Compose Navigation to manage the flows of the app with arguments when needed. I used mocks instead of fakes in the unit test to have a better control of the repository and mock success and error response when necesarry. I divided the structure by feature and each feature has its own data, domain and presentation layer so it could be refactor as an independent module. I dindt make this app a multi module app since the complexity of setting up multi module for a project this small I believe is not necessary.

## Tech stack
* Kotlin
* Jetpack Compose
* Clean Architechure
* Jetpack Compose Navigation
* MVI
* Unit Test
* Dark/Light Mode
* Dagger Hilt
* Retrofit
* Google Maps SDK

## Preview
<img src="https://github.com/MelvinKucuk/myrecipe/assets/42274125/b360064e-7871-43b3-bc91-7d9d90561e38" width="400" >
