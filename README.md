<h1 align="center">Food Delivery</h1>

### <div align="center"> Сервис заказов и доставки блюд</div>


## Содержание
- [Вступление](#Вступление)
- [Возможности](#Возможности)
- [Стэк Технологий](#-стэк-технологий)
- [Создатели](#создатели)


## Вступление
Food Delivery - сервис заказов и доставки блюд основанный на микросервисной архитектуре с использованием <b>Spring Boot</b>

## Возможности
- [Account Service](#account-service)
- [Order Service](#order-service)
- [Kitchen Service](#kitchen-service)
- [Delivery Service](#delivery-service)

### Account Service
Микросервис, выступающий в качестве сервиса авторизации(Authorization Service) для всех остальных сервисов. Реализован с помощью Role-Based JWT-токенов. Также, позволяет гибко управлять учетными записями пользователей, а также картами лояльности покупателей.

### Order Service
Главный сервис, обрабатывающий все события, происходящие с заказом, по мере его прохождения вдоль всего жизненного пути. Отвечает за принятие, обработку заказов и передачу их в соответствующие микросервисы по AMQP-протоколу в зависимости от текущего статуса. Кроме того, принимает и обрабатывает от других сервисов сообщения об измемении статуса заказа.

### Kitchen Service
Позволяет сотрудникам кухни получать информацию о заказах, поступивших в обработку, а также изменять их состояние путем отправки соответствующих HTTP-запросов

### Delivery Service
Сервис доставки готовых блюд и продуктов до конечного потребителя, реализующий систему выдачи заказов свободным курьерам. Подобно сервису кухни, позволяет просматривать список всех заказов, поступивших в доставку и доставляемых в данный момент.


## 👨‍💻 Стэк технологий
- Java ☕
- Spring Boot 
- PostgreSQL 
- Redis 
- RabbitMQ 
- JSON Web Token (JWT)


## Создатели
- <b>Месилов Андрей Денисович</b> — [Sosmunk](https://github.com/Sosmunk)
- <b>Фер Андрей Вадимович</b> — [AndrewWhiteZ](https://github.com/AndrewWhiteZ)

<br>

---

<br>

## P.S. Небольшое послесловие и благодарности создателям курса 

💚 Хочется выразить огромную благодарность преподавателям курса по <b>Spring Boot</b> из <b>СКБ ЛАБ</b>
за полученные в ходе обучения знания.</br>
Отдельное спасибо преподавателю <b>Евгению Калугину</b> - <i>[darkitself](https://github.com/darkitself)</i>
за помощь с домашними заданиями, ответами на вопросы по проекту 😊 🧡
