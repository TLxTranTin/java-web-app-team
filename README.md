module/

├── adapter/

│   ├── in/

│   │   └── web/

│   │       └── ModuleController.java

│   │

│   └── out/

│       └── persistence/

│           ├── ModuleEntity.java

│           ├── ModuleJpaRepository.java

│           └── ModulePersistenceAdapter.java

│

├── application/

│   ├── port/

│   │   ├── in/

│   │   │   ├── CreateModuleUseCase.java

│   │   │   ├── GetModuleUseCase.java

│   │   │   ├── UpdateModuleUseCase.java

│   │   │   └── DeleteModuleUseCase.java

│   │   │

│   │   └── out/

│   │       └── ModuleRepositoryPort.java

│   │

│   └── service/

│       └── ModuleService.java

│
├── domain/

│   └── model/

│       ├── Module.java

│       └── ModuleStatus.java

│

└── dto/

    ├── CreateModuleRequest.java
    
    ├── UpdateModuleRequest.java
    
    └── ModuleResponse.java
