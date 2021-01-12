Лабораторная работа №1. Реализация запуска приложения с Docker + балансировка с исользованием Nginx.
=====================
Для реализации данной работы было разработано приложение на Java с использованием Spring Boot. 
Данное приложение при обращении к нему возвращает ответ типа count = 1 (количество обращений к приложению).

Далее был создан Dockerfile, который содержит параметры для упаковки приложения в котейнер. 
Для запуска приложения из контейнера необходимо ввести команду "docker run -p 8080:8080 5b22ba1705d8",находясь в директиве с файлом (~IdeaProjects/docker-test).

Для запуска нескольких приложений был создан файл "docker-compose.yml", в котором находится описание трех контейнеров с приложением demo. 
Чтобы запустить этот файл необходимо,находясь в директиве с файлом, в командной строке написать "docker-compose up".

Для реализации балансировки с использованием Nginx в файл "docker-compose.yml" было добавлено описание для nginx.

Лабораторная работа №2. Реализация разделяемого хранилища данных с использованием Redis
=====================
В ходе данной работы был установлен Redis на Ubuntu. После чего подключаем клиент jedis для работы из веб-приложения из 1 лабораторной работы.

Jedis jedis = new Jedis("localhost", 6379);

Далее идет проверка есть ли ключ "count" в БД, если нет, то создаем запись "jedis.set("count", "0");", если есть, то запрашиваем счетчик, инкрементируем его "Long c = jedis.incr("count");" и выводим его на форму Long c = jedis.incr("count");

Для проверки запускаем два приложения на портах 8081 и 8082, обновляем страницы на обоих приложениях счетчик увеличивается.

Лабораторная работа №3. Реализация балансировки нагрузки с использованием очередей Kafka
=====================
Для реализации данной работы был использован Docker-compose файл c Kafka. Так же было создано приложение, в котором реализованы следующие элементы:

Producer - с неравномернй скоростью посылает сообщения в очередь;
Consumer - читает из очереди сообщения с определенными промежутками времени.
Необходимо было запустить docker-compose файл с Кафкой.

Лабораторная работа №4. Работа с партициями.
=====================
Для выполнения данной работы была создана таблица measurement. После этого были созданы партиции с помощью команды: 
CREATE TABLE measurement_y2006m10 PARTITION OF measurement
    FOR VALUES FROM ('2006-10-01') TO ('2006-11-01');
Далее были выполнены различные команды такие, как: INSERT, DELETE, SELECT для проверки работы партиций.
