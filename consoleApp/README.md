# Консольная версия проекта
## Тема
Левосторонняя куча
## Основной фукнционал
1. Добавление числа в кучу
2. Извлечение минимального числа из кучи
3. Считывание команд 1 и 2 из файла
4. Поддержка пользовательских тестов из файла
## Синтаксис команд
| Команда                                            | Описание                                                                                 |
| ---------------------------------------------------| -------------------------------------------------|
| `-e, --extract=<extract>`                          | Выводит минимальное число из кучи                |
| `-f, --file=<fileParse>`                           | Считывает из файла команды и запускает их        |
| `-h, --help`                                       | Выводит описание всех команд                     |
| `-i, --insert=<insert>[,<insert>...]`              | Добавляет число в кучу                           |
| `-t, --test=<customTest>[,<customTest>...]`        | Запускает пользовательские тесты из файла        |
## Примеры использования
- insert & extract                                                         
```
-i 1 -i 313 -e min -e min -e min 
Inserting 1
Inserting 313
Min number is 1
Min number is 313
Heap is empty!
```
- file

{path}/input.txt
```
-i 41 -i 31 -e min -e min -i 1 
```

```
-f {path/input.txt}
Inserting 41
Inserting 31
Min number is 31
Min number is 41
Inserting 1
```
```
-f {path/input.txt} -i 22 -e min -e min
Inserting 41
Inserting 31
Min number is 31
Min number is 41
Inserting 1
Inserting 22 
Min number is 1
Min number is 22
```
- test

__Запрещается использовать вместе с другими командами!__ 

{path}/test.txt
```
-e min
-e min
-i 3
-e min
```
```
-t {path}/test.txt
OK - Solution passed
```

## Сообщения об ошибках

| Сообщение                                                 | Описание                                                           |
|-----------------------------------------------------------|--------------------------------------------------------------------|
| Missing parameters in some methods. Use -h for help.      | В одной или более командах не хватает параметров                   |
| Unknown command                                           | Неизвестная команда                                                |
| Heap is empty!                                            | Возникает при команде `-e min` в случае, если куча пустая          |
| Min number is [min number]                                | Результат работы команды `-e min`. Выводит наименьшее число в куче |
| Inserting [number]                                        | Результат работы команды `-i [number]`. Выводит вводимое число     |
| File not found                                            | Выводится в случае, если по указаному пути файла не существует     |
| Can't resolve symbols                                     | Ошибка чтения файла                                                | 
| Option should be specified only once                      | Команда должна быть ипользована лишь единожды (для тестов и файлов)|
| Wrong parameter                                           | Некорректный аргумент команды                                      |
| There are no commands                                     | Выводится в случае отсутствия команд                               |
| Incorrect number of parameters                            | Некорректное число аргументов команды (для тестов)                 |

## Тесты

| OK - Solution passed||
| IE - Invalid input format||
| WA - Wrong answer||
| RE - Error reading input file||
| RE - Error reading answer file||

