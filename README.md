# spring-boot-assignment
A Spring Boot application for retrieving and transforming cryptocurrency data using CoinDesk's API &amp; H2 database.


## Tasks
### 1. 呼叫coindesk的API，並進行資料轉換，組成新API
回傳的資料與範例如下
```json
{
    "time": {
        "updated": "2024/01/31 10:11:56"
    },
    "chartName": "Bitcoin",
    "bpi": {
        "USD": {
            "code": "USD",
            "zh_code": "美元",
            "rate_float": 42681.207
        },
        "GBP": {
            "code": "GBP",
            "zh_code": "英鎊",
            "rate_float": 33628.734
        },
        "EUR": {
            "code": "EUR",
            "zh_code": "歐元",
            "rate_float": 39449.258
        }
    }
}
```

### 2. 建立一張幣別與其對應中文名稱的資料表（需附建立SQL語法），並提供 查詢 / 新增 / 修改 / 刪除 功能API
請見 `src/main/resources/schema.sql`，以及初始化資料 `src/main/resources/data.sql`

## API Endpoints

The application provides the following RESTful endpoints:

### List All Currencies

- **GET** `/api/v1/currency`
- Retrieves a list of all currencies.

### Get a Specific Currency

- **GET** `/api/v1/currency/{code}`
- Retrieves a specific currency by its code.
- Path Variable: `code` - The code of the currency.

### Create a New Currency

- **POST** `/api/v1/currency`
- Creates a new currency.
- Request Body: `CurrencyDto` object.

### Update a Currency (Full Update)

- **PUT** `/api/v1/currency/{code}`
- Fully updates an existing currency.
- Path Variable: `code` - The code of the currency to update.
- Request Body: `CurrencyDto` object with updated data.

### Delete a Currency

- **DELETE** `/api/v1/currency/{code}`
- Deletes a currency by its code.
- Path Variable: `code` - The code of the currency to delete.

### Get Current Price (Bitcoin)

- **GET** `/api/v2/current-price`
- Retrieves the current cryptocurrency price data transformed from CoinDesk's API.

## Running the Application

- Ensure you have Java and Maven installed.
- Clone the repository and navigate to the project directory.
- Run the application using `mvn spring-boot:run`.
- The application will be accessible at `http://localhost:8080`.
