workspace "Factory Pattern Example - Triptop Adapter Creation" "Illustrates the Factory pattern for creating Adapters." {

    model {
        // External system
      

        // Main system
        factorySystem = softwareSystem "Adapter Factory System" {
            // Client using the Factory (e.g., a Facade)
            adapterClient = container "Adapter Client" "Needs instances of specific adapters (Products)" "Java / Spring Component" {
                tags "Client"
            }

            // Factory Component
            adapterFactory = container "AdapterFactory" "Responsible for providing instances of Adapters (Products)" "Java / Spring Component" {
                tags "Factory"
            }

            // Product Interface
            adapterInterface = container "IAdapter" "Standard interface for adapters" "Java Interface" {
                tags "Product Interface"
            }

            // Concrete Products
            concreteAdapter1 = container "ConcreteAdapter1" "e.g., SkyscannerAdapter or StripePaymentAdapter" "Java / Spring Component" {
                tags "Concrete Product"
            }
            
            concreteAdapter2 = container "ConcreteAdapter2" "e.g., KLMApiAdapter or PaypalPaymentAdapter" "Java / Spring Component" {
                tags "Concrete Product"
            }
        }

        // Relationships
        adapterClient -> adapterFactory "Uses" "getAvailableAdapters()"
        adapterFactory -> adapterInterface "Creates/Provides" "(Instances)"
        concreteAdapter1 -> adapterInterface "Implements"
        concreteAdapter2 -> adapterInterface "Implements"
        
    }

    views {
        container factorySystem "FactoryPatternDetail" "Component diagram showing the Factory pattern for Adapter creation." {
            include *
            autoLayout
        }

        styles {
            element "Container" {
                background #1168bd
                color #ffffff
            }
            element "Client" {
                background #1168bd
                color #ffffff
            }
            element "Factory" {
                background #1168bd
                color #ffffff
                icon "https://static.structurizr.com/icons/pattern-factory-method-16.png"
            }
            element "Product Interface" {
                background #6c96d6
                color #ffffff
            }
            element "Concrete Product" {
                background #1168bd
                color #ffffff
            }
            element "External Framework" {
                background #dddddd
                color #000000
            }
        }
    }
}