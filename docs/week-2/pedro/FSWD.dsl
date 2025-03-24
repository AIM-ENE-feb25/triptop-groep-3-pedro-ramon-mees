workspace "ExpenseTracker" "Context, Container, and Component diagrams for the Expense Tracker application" {
    !identifiers hierarchical
    model {
        // Users
        user = person "User" {
            description "A user who tracks and manages their expenses."
        }
        
        // External systems
        database = softwareSystem "Database" {
            description "Stores user information, authentication tokens, and expense data."
            tags "Database"
        }
        
        // ExpenseTracker System
        expenseTracker = softwareSystem "Expense Tracker" {
            description "An application that allows users to track and manage their expenses."
            
            // Containers
            frontEnd = container "Frontend Web Application" {
                description "The UI that allows users to view and manage their expenses."
                technology "React, JavaScript, Vite, Bootstrap"
            }
            
            backEnd = container "Backend API" {
                description "Provides expense management functionality via a JSON/HTTPS API."
                technology "Java Spring Boot, Spring Data JPA"
                
                // Components (Backend only)
                webConfig = component "Web Configuration" {
                    description "Configures web-related settings like CORS."
                    technology "Spring @Configuration"
                }
                
                dbConfig = component "Database Configuration" {
                    description "Configures database connection and settings."
                    technology "Spring @Configuration"
                }
                
                authController = component "Authentication Controller" {
                    description "Handles user authentication requests."
                    technology "Spring @RestController"
                }
                
                expenseController = component "Expense Controller" {
                    description "Handles expense-related requests."
                    technology "Spring @RestController"
                }
                
                authService = component "Authentication Service" {
                    description "Manages user authentication logic."
                    technology "Spring @Service"
                }
                
                userService = component "User Service" {
                    description "Manages user-related operations."
                    technology "Spring @Service"
                }
                
                expenseService = component "Expense Service" {
                    description "Manages expense-related operations."
                    technology "Spring @Service"
                }
                
                userTokenService = component "User Token Service" {
                    description "Manages user authentication tokens."
                    technology "Spring @Service"
                }
                
                userRepository = component "User Repository" {
                    description "Provides access to user data."
                    technology "Spring Data JPA"
                }
                
                expenseRepository = component "Expense Repository" {
                    description "Provides access to expense data."
                    technology "Spring Data JPA"
                }
                
                userTokenRepository = component "User Token Repository" {
                    description "Provides access to user token data."
                    technology "Spring Data JPA"
                }
                
                securityComponent = component "Security Component" {
                    description "Handles password encryption and security."
                    technology "Spring Security Crypto"
                }
                
                exceptionHandler = component "Global Exception Handler" {
                    description "Centralized exception handling for the API."
                    technology "Spring @ControllerAdvice"
                }
            }
            
        }
        
        // Relationships - Context Level
        user -> expenseTracker "Uses to track and manage expenses"
        expenseTracker -> database "Stores and retrieves data in" "JDBC"
        
        // Relationships - Container Level
        user -> expenseTracker.frontEnd "Views and manages expenses via" "HTTPS"
        expenseTracker.frontEnd -> expenseTracker.backEnd "Makes API calls to" "JSON/HTTPS"
        expenseTracker.backEnd -> database "Reads from and writes to" "JDBC"
        
        // Relationships - Component Level (Backend)
        // Controller to Service relationships
        expenseTracker.backEnd.authController -> expenseTracker.backEnd.authService "Delegates authentication logic to"
        expenseTracker.backEnd.authController -> expenseTracker.backEnd.userTokenService "Requests token generation from"
        expenseTracker.backEnd.expenseController -> expenseTracker.backEnd.expenseService "Delegates expense operations to"
        
        // Service to Repository relationships
        expenseTracker.backEnd.authService -> expenseTracker.backEnd.userRepository "Retrieves and stores user data through"
        expenseTracker.backEnd.authService -> expenseTracker.backEnd.securityComponent "Verifies passwords using"
        expenseTracker.backEnd.userService -> expenseTracker.backEnd.userRepository "Manages user data through"
        expenseTracker.backEnd.expenseService -> expenseTracker.backEnd.expenseRepository "Manages expense data through"
        expenseTracker.backEnd.userTokenService -> expenseTracker.backEnd.userTokenRepository "Stores and retrieves tokens through"
        
        // Configuration relationships
        expenseTracker.backEnd.webConfig -> expenseTracker.backEnd.authController "Provides web configuration settings to"
        expenseTracker.backEnd.webConfig -> expenseTracker.backEnd.expenseController "Provides web configuration settings to"
        expenseTracker.backEnd.dbConfig -> database "Provides database connection settings to"
        
        // Database configuration to repositories
        expenseTracker.backEnd.dbConfig -> expenseTracker.backEnd.userRepository "Provides database connection configuration to"
        expenseTracker.backEnd.dbConfig -> expenseTracker.backEnd.expenseRepository "Provides database connection configuration to"
        expenseTracker.backEnd.dbConfig -> expenseTracker.backEnd.userTokenRepository "Provides database connection configuration to"
        
        // Exception handling
        expenseTracker.backEnd.exceptionHandler -> expenseTracker.backEnd.authController "Intercepts and processes exceptions from"
        expenseTracker.backEnd.exceptionHandler -> expenseTracker.backEnd.expenseController "Intercepts and processes exceptions from"
    }
    
    views {
        systemContext expenseTracker "ExpenseTrackerContextDiagram" {
            include *
            autolayout tb
            description "Shows the Expense Tracker system and its users."
        }
    
        container expenseTracker "ExpenseTrackerContainerDiagram" {
            include *
            autolayout tb
            description "Shows the main components of the Expense Tracker system."
        }
        
        component expenseTracker.backEnd "ExpenseTrackerBackendComponentDiagram" {
            include *
            autolayout tb
            description "Shows the components within the Expense Tracker backend."
        }
        
        theme default
    }
}
