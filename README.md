# MatrixApp

## Описание проекта

Данное приложение, написанное на Java, предназначено для выполнения операций над двумя матрицами, такими как сложение, вычитание, умножение и вычисление определителя.  Приложение предоставляет графический интерфейс пользователя (GUI) на основе JavaFX для удобного взаимодействия.

## Функциональность

* **Загрузка матриц:**  Загрузка данных матриц из текстовых файлов.
* **Сложение:** Сложение двух матриц одинаковой размерности.
* **Вычитание:** Вычитание двух матриц одинаковой размерности.
* **Умножение:** Умножение двух матриц совместимых размерностей.
* **Определитель:** Вычисление определителя квадратной матрицы.
* **Обработка ошибок:**  Приложение обрабатывает исключительные ситуации, такие как несовместимые размерности матриц и некорректный формат входных файлов.
* **Логирование:**  Используется Log4j 2 для логирования операций и ошибок.

## Запуск приложения

**Требования:** JDK 21.0.5

1. **Сборка проекта:** `./gradlew build`
2. **Запуск Unit тестов:** `./gradlew clean test`
3. **Сборка JAR файла:** `./gradlew shadowJar`
4. **Запуск приложения:** `java -jar build/libs/MatrixApp.jar`
4. **Тестовые матрицы находятся в директории testMatrix**


## Запуск Jar Файла

**Требования:** JDK 21.0.5

На опреационных системах Windows и MacOS jar файл должен запускаться успешно. Если же возникают ошибки с библиотеками, который относятся к JavaFX, то необходимо скачать [JavaFX SDK](https://gluonhq.com/products/javafx/). После чего разорхивировать SDK и произвести запуск следующей командой:
`java --module-path <путь_к_javafx_lib> --add-modules javafx.controls,javafx.fxml,... -jar <jar-файл>`

## Структура проекта

* **com.matrix:** Содержит основные классы приложения:
    * `BaseMatrix`: Базовый класс для представления матрицы.
    * `Matrix`:  Расширяет `BaseMatrix` и реализует операции над матрицами.
    * `MatrixGUI`:  Реализует графический интерфейс.
    * `MatrixLoader`: Загружает матрицы из файлов.
    * `MatrixMain`:  Содержит точку входа приложения.
    * `MatrixOperationException`:  Пользовательское исключение для обработки ошибок, связанных с операциями над матрицами.
* **com.matrix.interfaces:** Содержит интерфейсы:
    * `iBaseMatrix`
    * `iMatrix`
    * `iMatrixGUI`
    * `iMatrixLoader`
    * `iMatrixOperation`

##  Документация

Документация к коду сгенерирована в HTML-формате с помощью JavaDoc (включить в репозиторий).


## Тестирование

Unit-тесты реализованы с использованием JUnit 5.  Для запуска тестов используйте команду `./gradlew test`.

## Контроль версий

Код проекта размещен на GitHub.


## Библиотеки

* JavaFX
* org.apache.logging.log4j
* JUnit 5
* Gradle


## Дальнейшее развитие

* Реализация дополнительных операций над матрицами (транспонирование, обратная матрица, ранг, решение систем линейных уравнений).
* Поддержка больших матриц и различных типов данных.
* Интеграция с другими математическими библиотеками.
* Улучшение графического интерфейса (визуализация матриц).
* Более сложная обработка ошибок и валидация данных.
