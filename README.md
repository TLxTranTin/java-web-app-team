# java-web-app-team
module/
├── adapter/
│   ├── in/web                 // Controller nhận request từ FE
│   └── out/persistence         // Entity, JpaRepository, PersistenceAdapter làm với database
│
├── application/
│   ├── port/in                 // UseCase interface: bên ngoài gọi vào app
│   ├── port/out                // RepositoryPort interface: app gọi ra ngoài
│   └── service                 // Logic nghiệp vụ
│
├── domain/model                // Domain model thuần nghiệp vụ
└── dto                         // Request/Response DTO giao tiếp FE/BE



=============================================================
Quy tắc đặt tên :
- tên thư mục : viết thường , sử dụng gạch nối ví dụ : backend-spring
- tên file : viết hoa chữ cái đầu , còn lại viết thường ví dụ : AppParking.java
- tên biến : viết hoa chữ cái đầu ( riêng chữ đầu tiên ko cần viết ) ví dụ : appParking.java


=============================================================
Để Chạy Được Cần cài:

1. JDK 21
2. VS Code
3. Extension Pack for Java
4. Spring Boot Extension Pack
5. Thunder Client để test API
6. DBeaver để xem database
7. PostgreSQL nếu project dùng database thật
8. Thunder Client


Nếu có Maven Wrapper thì chạy:
.\mvnw.cmd spring-boot:run

Nếu dùng Maven máy thì chạy:
mvn spring-boot:run


==========================================
Mọi người tự làm rõ các Use Case nếu cần thiết , như thêm chức năng j đó cho module mình làm nếu hợp lí
