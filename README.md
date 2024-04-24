ini merupakan service Restful API blogspot
Technology yang digunakan

1. Java 17
2. Spring version 3.2.5
3. Database H2
4. Flyway sebagai migration
5. JWT sebagai Authentication

untuk menjalankan aplikasi ini 
tinggal menjalankan "mvn clean spring-boot:run"
disini akan otomatis tergenerate table users dan blog

Langkah - Langkah step menggunakan aplikasi ini :
1. Run Postman Endpoint Create User
ini curlnya
curl --location 'http://localhost:8282/api/auth/create' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1B0E752F732CA9C85247286A933B24C1' \
--data '{
    "username":"dicky.adriansyah",
    "password":"dicky123"
}'
![image](https://github.com/adriansyahdicky/blogspot-service/assets/54537198/00f0cfe1-ae39-4f4e-8d66-7572feca6452)
