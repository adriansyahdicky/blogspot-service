ini merupakan service Restful API blogspot <br/>
Technology yang digunakan

1. Java 17
2. Spring version 3.2.5
3. Database H2
4. Flyway sebagai migration
5. JWT sebagai Authentication

untuk menjalankan aplikasi ini 
tinggal menjalankan "mvn clean spring-boot:run"
disini akan otomatis tergenerate table users dan blog <br/>

jika ingin melihat db h2 cukup mengakses link ini : http://localhost:8282/h2-console <br/>
username : sa <br/>
password : password <br/>
![image](https://github.com/adriansyahdicky/blogspot-service/assets/54537198/bff64c73-1ca5-4aa7-bfc8-e88543746dbc)
<br/>
Langkah - Langkah step menggunakan aplikasi ini :
1. Run Postman Endpoint Create User <br/>
ini curlnya <br/>
curl --location 'http://localhost:8282/api/auth/create' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1B0E752F732CA9C85247286A933B24C1' \
--data '{
    "username":"dicky.adriansyah",
    "password":"dicky123"
}'
![image](https://github.com/adriansyahdicky/blogspot-service/assets/54537198/00f0cfe1-ae39-4f4e-8d66-7572feca6452)
<br/>
2. Run Postman Endpoint Login <br/>
ini curlnya <br/>
curl --location 'http://localhost:8282/api/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1B0E752F732CA9C85247286A933B24C1' \
--data '{
    "username": "dicky.adriansyah",
    "password": "dicky123"
}'
disini kita akan mendapatkan token <br/>
![image](https://github.com/adriansyahdicky/blogspot-service/assets/54537198/36635bba-8611-4470-b68e-a2a425935d92)

