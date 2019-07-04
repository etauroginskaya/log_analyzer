**Инструмент для анализа логов**
Анализатор логов сканирует каталог на наличие файлов, читает и фильтрует записи, которые соответствуют пользовательскому вводу, и производить следующий вывод: 
Входные параметры:
● имя пользователя
● период времени
● шаблон для пользовательского сообщения
Выходные параметры:
● один файл со всеми отфильтрованными записями и совокупной статистикой;
● вывод в консоли совокупной статистики; 
Параметры совокупной статистики:
● параметр группировки (имя пользователя, кдиница времени);
● единица времени(1 день, 1 месяц, 1 год); 
● количество потоков используемое для обработки файлов;
● путь и имя результирующего файла;
Каждая запись обрабатываемых файлов имеет следующий формат: 2019-04-02|INFO|Alena|test message 
Настраиваемые параметры:
С помощь файла конфигурации "app.properties" осуществляется настройка. Существуют обязательные и необязательные параметры.
В случае отсутствия обязательных параметров (директория для считывания файлов и тип файлов) выполнение будет завершено. 
Необязательные параметры (количество потоков для многопоточного вычисления, имя и директория результирующего файла) имеют значения по умолчанию (указаны в скобках), при указании значения вручную оно будет применено.
# ===============================
# REQUIRED PROPERTY
# ===============================
# Path to the directory of processed files (example: C:\\test)
input.path=C:\\opt\\petProjects\\analyzer\\src\\main\\resources\\test
# File extension (example: .log)
filter.file.extension=.log
# ===============================
# OPTIONAL PROPERTY
# ===============================
# Additional count of threads used to process files (default=1 main thread)
threads.count=
# Filename to output file (example: file.txt; default=)
output.filename=
# Path to output file (example: C:\\test; default User home directory)
output.path=
Для сборки используется Maven.


** Log Analysis Tool **
The log analyzer scans the directory for files, reads and filters records that correspond to user input, and produces the following output:
Input parameters:
● username
● time period
● template for custom message
Output Parameters:
● one file with all filtered records and aggregate statistics;
● console output of aggregate statistics;
Parameters of aggregate statistics:
● grouping parameter (user name, time-based);
● time unit (1 day, 1 month, 1 year);
● the number of threads used to process files;
● path and name of the resulting file;
Each record of processed files has the following format: 2019-04-02|INFO|Alena|test message
Adjustable parameters:
With the help of the configuration file "app.properties" configuration is carried out. There are required and optional parameters.
If there are no required parameters (file reading directory and file type), the execution will be completed.
Optional parameters (the number of threads for multi-threaded calculations, the name and directory of the resulting file) have default values ​​(shown in parentheses), if you specify the value manually, it will be applied.
Used to build maven.
 
