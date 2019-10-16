# CurrencyConversion

## Goal:

#### Develop a Currency Conversion App that allows a user view exchange rates for any given currency

- [x] Create a Project for a Mobile Phone
- [x] Android: _Kotlin_ | iOS: _Swift_ (sorry, no Objective-C or Java please! You can learn Kotlin/Swift easily I'm sure:))
      (Android: Kotlin)
### Functional Requirements:
- [x] Exchange rates must be fetched from: https://currencylayer.com/documentation  
- [x] Use free API Access Key for using the API
- [x] User must be able to select a currency from a list of currencies provided by the API(for currencies that are not available, convert them on the app side. When converting, floating-point error is accpetable)
- [x] User must be able to enter desired amount for selected currency
- [x] User should then see a list of exchange rates for the selected currency
- [x] Rates should be persisted locally and refreshed no more frequently than every 30 minutes (to limit bandwidth usage)
      ( Currently USD to five currencies exchange rates available, Switching source currency isn't allowed in free API access.)

### UI Suggestion:
- [X] Some way to select a currency
- [x] Some text entry widget to enter the amount
- [x] A list/grid of exchange rates
- [x] It doesn’t need to be super pretty, but it shouldn’t be broken as well ;)

### What we're looking for: (Reviewer)
- [ ] An App that meets the Functional Requirements above
- [ ] Your coding style! Show us how you like to write your code
- [ ] Architecture, how you've structured your code
- [ ] Principles, how you belive code should be written
- [ ] Qualities, how you guarantee your code is functioning
