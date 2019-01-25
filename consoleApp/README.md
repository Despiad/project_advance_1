# Консольная версия проекта
## Тема
Левосторонняя куча
## Содержание
1. Основной функционал
2. Синтаксис команд
3. Примеры использования
   - insert & extract
   - file
   - test
4. Сообщения об ошибках
5. Тесты
   - Формат ввода
   - Сообщения тестера
   - Примеры
     - Пройденный тест
     - Непройденный тест 
     - Неправильно указано расположение файла   
## Основной фукнционал
1. Добавление числа в кучу
2. Извлечение минимального числа из кучи
3. Считывание команд 1 и 2 из файла
4. Поддержка пользовательских тестов из файла
## Синтаксис команд
| Команда                                            | Описание                                                   |
| ---------------------------------------------------| -----------------------------------------------------------|
| `-e, --extract=<extract>`                          | Извлекает минимальное число из кучи и выводит его на экран |
| `-f, --file=<fileParse>`                           | Считывает из файла команды и запускает их                  |
| `-h, --help`                                       | Выводит описание всех команд                               |
| `-i, --insert=<insert>[,<insert>...]`              | Добавляет число в кучу                                     |
| `-t, --test=<inputFile>,<answerFie>`               | Запускает пользовательские тесты из файла                  |
## Примеры использования
### insert & extract                                                         
```
-i 1 -i 313 -e min -e min -e min 
Inserting 1
Inserting 313
Min number is 1
Min number is 313
Heap is empty!
```
### file

__{path}/input.txt__
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
### test

__Запрещается использовать вместе с другими командами!__ 

__{path}/test.txt__
```
1
2
3
min
extractMin
min
extractMin
min
extractMin
```

__{path}/answer.txt__
```
1
2
3
```
```
-t {path}/test.txt,{path}/answer.txt 
OK - Solution passed
```

## Сообщения об ошибках

| Сообщение                                                 | Описание                                                           |
|-----------------------------------------------------------|--------------------------------------------------------------------|
| Missing parameters in some methods. Use -h for help.      | В одной или более командах не хватает параметров                   |
| Unknown command                                           | Не команда                                                |
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
### Формат ввода
`min` - Выводится минимальное число, но __не удаляется__

`extractMin` - __Удаляется__ минимальное число

### Сообщения тестера
| Сообщение тестера              | Значение                                                  |
|--------------------------------|-----------------------------------------------------------|
| OK - Solution passed           | Программа верно работает на соответствующем наборе тестов |
| IE - Invalid input format      | Неверный формат вывода                                    |
| WA - Wrong answer              | Ответ неверен                                             |
| RE - Error reading input file  | Ошибка чтения файла ввода                                 |
| RE - Error reading answer file | Ошибка чтения файла с ответами                            |

### Примеры 
#### Пройденный тест

__{path}/corrertTest.txt__
```
1
2
3
min
extractMin
min
extractMin
min
extractMin
```

__{path}/answer.txt__
```
1
2
3
```
```
-t {path}/correctTest.txt,{path}/answer.txt 
OK - Solution passed
```

#### Непройденный тест

__{path}/incorrertTest.txt__
```
-1
1
2
min
extractMin
min
extractMin
min
extractMin
min
```

__{path}/answer.txt__
```
1
2
3
```
```
-t {path}/incorrectTest.txt,{path}/answer.txt 
WA - Wrong answer
```
#### Неправильно указано расположение файла

__{path}/validateTest.txt__
```
1
2
3
max
min
min
```

__{path}/answer.txt__
```
1
2
3
```
```
-t {path}/validateTest.txt,{path}/answer.txt 
IE - Invalid input format
```