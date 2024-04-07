Приложение для сортировки изображений

Описание приложения: 
Консольное приложение обрабатывает изображения, читая их метаданные, переименовывая и сортируя их в соответствии с этими данными.

Установка и запуск
1. Скачайте исходный код:
Скачайте ZIP-архив с исходным кодом вашего приложения из репозитория GitHub.
2. Настройте среду разработки:
Убедитесь, что у вас установлена среда разработки Java, например IntelliJ IDEA или Eclipse.
3. Откройте проект:
Распакуйте ZIP-архив с исходным кодом.
Откройте проект в своей среде разработки.

Использование
Приложение предоставляет простой интерфейс командной строки для обработки изображений.

Вот основные шаги использования:
1. Запуск приложения:
Запустите приложение, запустив класс Main.
2. Ввод каталога:
Приложение запросит вас ввести путь к каталогу с изображениями.
3. Выбор удаления файлов:
Приложение спросит, хотите ли вы удалить обработанные файлы.
Введите "yes" для удаления или "no" для оставления файлов.
4. Обработка изображений:
После завершения ввода приложение обработает изображения в соответствии с указаниями и отсортирует по папкам.

Описание кода
Main.java: Главный класс приложения, который содержит точку входа и основную логику.
ImageProcessor.java: Класс, отвечающий за обработку изображений, включая чтение метаданных, переименование и сортировку.
MetaDataReader.java: Класс для чтения метаданных изображения из файла.
ImageMetadata.java: Класс для хранения метаданных изображения.
FileProcessor.java: Класс для обработки файлов и ввода информации о каталоге.
ImageRenamer.java: Класс генерирует новое имя файла на основе метаданных.
ImageSorter.java: Класс сортирует изображения по году и месяцу.
