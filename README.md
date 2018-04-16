## Тестовое задание БКС

### Run with Spring Boot Maven Plugin:
```
bks_task\app>mvn spring-boot:run
```
### Open the following URL:
```
http://localhost:8080/
```

## Описание API

Все данные, получаемые по API, упакованы в следующий объект :
```javascript
{
	"success": true/false,			- успешность обработки запроса
    "data": JsonObject/JsonArray,	- возвращаемые данные
    "error": String/null			- Описание ошибки, в случае ее возникновения
}
```
### Общая информация о сервисе
#### Request

URL: **/**

Method: **GET**

#### Response
```javascript
{
	"success":true,
    "data":
    	{
        	"appName":"Test Application for BCS",
            "apiVersion":"1.0"
        },
    "error":null
}
```
### Все продукты которые поддерживает сервис
#### Request

URL: **/products**

Method: **GET**

#### Response
```javascript
{
	"success":true,
    "data":
    	[
        	{
            	"productId":"p-1q2w1",		- внешний ID продукта, используется при вызове методов API, генерируется автоматически
                "name":"Кредит Мини",		- наименование продукта
                "maxSum":200000.0,			- максимальная сумма
                "maxTerm":1,				- макисмальный срок (в годах)
                "rate":6.0					- процентная ставка
            },{
            	"productId":"p-1q2w2",
                "name":"Кредит Миди",
                "maxSum":1000000.0,
                "maxTerm":5,
                "rate":12.0
            },{
            	"productId":"p-1q2w3",
                "name":"Кредит Макси",
                "maxSum":5000000.0,
                "maxTerm":3,
                "rate":10.5
            },{
            	"productId":"p-1q2w4",
                "name":"Кредит Безлимитный",
                "maxSum":0.0,				- 0 означает отсутствие максимальной суммы
                "maxTerm":0,				- 0 означает отсутствие макисмального срока
                "rate":15.0
            }
        ],
    "error":null
}
```
### Правила применимости продукта к заемщику
#### Request

URL: **/products/{product ID}/rules**

Method: **GET**

#### Response
```javascript
{
	"success":true,
    "data":
    	[
        	{
            	"ruleId":"r-1a2s1",			- внешний ID правила, используется при вызове методов API, генерируется автоматически
                "salary":40000.0,			- минимальный размер зарплаты заемщика
                "isNoDebtsRule":true		- Включено ли правило отсутствия у заемщика текущей задолженности
            },{
            	"ruleId":"r-1a2s2",
                "salary":60000.0,
                "isNoDebtsRule":false
            }
        ],
    "error":null
}
```
### Добавляет правило к продукту
#### Request

URL: **/products/{product ID}/rules**

Method: **POST**

Content-type: application/json

RequestBody:
```javascript
{
	"salary": 60000,			- минимальный размер зарплаты заемщика
	"isNoDebtsRule": true		- Вкл/откл правило отсутствия у заемщика текущей задолженности
}
```

#### Response
```javascript
{
	"success":true,
    "data":null,
    "error":null
}
```
### Удаляет правило у продукта
#### Request

URL: **/products/{product ID}/rules/{rule ID}**

Method: **DELETE**

#### Response
```javascript
{
	"success":true,
    "data":null,
    "error":null
}
```
### Отдает продукты применимые к переданой информации о заемщике
#### Request

URL: **/products/apply**

Method: **POST**

Content-type: application/json

RequestBody:
```javascript
{
	"claim": 100000,
	"salary": 50000,
	"is_debtor": true
}
```

#### Response
```javascript
{
	"success":true,
    "data":
    	[
        	{
            	"productId": "p-1q2w3",
            	"name": "Кредит Макси",
            	"maxSum": 5000000,
            	"maxTerm": 3,
            	"rate": 10.5
        	},{
            	"productId": "p-1q2w4",
            	"name": "Кредит Безлимитный",
            	"maxSum": 0,
            	"maxTerm": 0,
            	"rate": 15
        	}
    ],
    "error":null
}
```