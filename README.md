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
Quy tắc đặt tên :
- tên thư mục : viết thường , sử dụng gạch nối ví dụ : backend-spring
- tên file : viết hoa chữ cái đầu , còn lại viết thường ví dụ : AppParking.java
- tên biến : viết hoa chữ cái đầu ( riêng chữ đầu tiên ko cần viết ) ví dụ : appParking.java
