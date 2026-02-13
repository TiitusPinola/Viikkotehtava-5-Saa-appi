# Sääsovellus

## 1. Mitä Retrofit tekee

Retrofit hoitaa sovelluksessa HTTP-pyyntöjen tekemisen sää-APIin. Interfaceen määritellään GET-pyyntö, ja Retrofit muodostaa sen perusteella oikean verkko-osoitteen ja lähettää pyynnön.
HTTP-pyyntöjen hallinta tapahtuu Retrofitin kautta, joten verkkokutsuja ei tarvitse toteuttaa manuaalisesti.

## 2. Miten JSON muutetaan dataluokiksi

API palauttaa datan JSON-muodossa. Projektissa on määritelty Kotlin-dataluokat, jotka vastaavat API:n JSON-rakennetta.
Retrofit käyttää taustalla Gson-kirjastoa, joka muuntaa JSON-vastauksen automaattisesti näiksi dataluokiksi. JSON-datan käsin jäsentämistä ei siis tarvita.

## 3. Miten coroutines toimii tässä

API-kutsu tehdään coroutineilla ViewModelissa. Verkkokutsu suoritetaan taustasäikeessä, jotta käyttöliittymä ei jäädy.
Kun data saadaan, coroutine palaa pääsäikeelle ja päivittää UI-tilan, jolloin käyttöliittymä päivittyy.

## 4. Miten UI-tila toimii

ViewModel hallitsee WeatherUiState-oliota, joka sisältää esimerkiksi lataustilan, haetun datan ja mahdollisen virhetilan.
Jetpack Compose reagoi tilan muutoksiin automaattisesti. Kun WeatherUiState päivittyy, Compose piirtää käyttöliittymän uudelleen ilman erillistä päivityslogiikkaa.

## 5. Miten API-key on tallennettu

API-avain on tallennettu local.properties-tiedostoon, jotta sitä ei tarvitse kovakoodata projektiin.
Avain siirretään BuildConfigiin build.gradle-tiedoston kautta, ja Retrofit käyttää sitä API-kutsussa parametrina.
