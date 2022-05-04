# Zadania

### Zadanie 1 - konfiguracja środowiska
Skonfiguruj środowisko IntelliJ (opcjonalnie Android Studio) wraz z wirtualną maszyną (Pixel2, Android 8.1 lub wyżej). Pobierz kod źródłowy aplikacji i uruchom ją w przygotowanym środowisku.

### Zadanie 2 - fragmenty
Utwórz nowy fragment (settingsFragment), który będzie się pojawiał po kliknięciu w przycisk "Settings", który znajduje się w menu w prawym, górnym rogu ekranu. W tym celu utwórz 2 pliki: ... > ui > settings > settingsFragment.java oraz res > layout > fragment_settings.xml. Następnie dodaj fragment w pliku mobile_navigation.xml oraz MainActivity.java (TODO 2.1). W pliku MainActivity.java napisz kod, który zastąpi bieżacy fragment, po naciśnięciu przycisku (TODO 2.2). 

### Zadanie 3 - layout
Następnie edytuj layout (plik fragment_settings.xml). Dodaj EditText, umiejscowiony na środku ekranu wraz z atrybutem hint oraz Button, umiejscowiony w prawym, dolnym rogu ekranu. W klasie settingsFragment.java napisz kod, który uruchomi się po przyciśnięciu Button'a i wyświetli komunikat (Toast) o treści zawartej przez użytkownika w polu EditText'u.

### Zadanie 4 - zapis do pliku
W aplikacji został usunięty kod odpowiedzialny za zapisywanie studentów (students), spotkań (appointments) oraz zadań (assignments) do pliku. Napisz własny kod, który będzie zapisywał wszystkie 3 klasy do jednego pliku (użyj kartę SD). W tym celu zmodyfikuj pliki: Teacher.java oraz MainActivity.java.

### Zadanie 5 - zmiana ikon
Zastąp obecne ikonki (home, students, appointments, assignments) w bocznym menu nowymi, stworzonymi lub pobranymi przez siebie, ikonami. Następnie w pliku AndroidManifest.xml zmień ikonę programu, która wyświetla się w menu głównym Androida.
