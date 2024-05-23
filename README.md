# Online Banking System

## Design Patterns Used

### 1. Command Pattern

**Purpose**: 
The Command Pattern is used to encapsulate a request as an object, thereby allowing for parameterization of clients with different requests, queuing of requests, and providing functionalities such as logging and undoing operations.

**Usage in Your Project**:
- **Command Interface**: Defines a standard interface with an `execute` method that all command classes implement.
- **Concrete Commands**: These are specific implementations of the command interface, such as `CreateAccountCommand`, `DepositCommand`, `WithdrawCommand`, etc. Each concrete command class encapsulates the action to be performed and the necessary parameters.
- **Invoker**: The client application (`BankClient`) acts as the invoker. It constructs and sends command objects to be executed.
- **Receiver**: The `BankFacade` class acts as the receiver, containing the actual business logic that needs to be executed when a command is invoked.
- **Command Factory**: Although not a core part of the Command Pattern, the factory is used to create command objects. This simplifies the client code by encapsulating the logic of which command to instantiate based on input.

### 2. Facade Pattern

**Purpose**: 
The Facade Pattern provides a simplified interface to a complex subsystem. It makes the subsystem easier to use by hiding the complexities and providing a unified interface.

**Usage in Your Project**:
- **BankFacade**: This class acts as the facade. It provides simple methods for creating accounts, making deposits, withdrawing money, displaying account details, and changing account states (e.g., suspending, activating, closing accounts). The client interacts with this simplified interface instead of dealing with the complex internal workings of account management directly.

### 3. State Pattern

**Purpose**: 
The State Pattern allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

**Usage in Your Project**:
- **AccountState Interface**: Defines the interface for state-specific behavior.
- **Concrete States**: `ActiveState`, `SuspendedState`, and `ClosedState` are concrete implementations of the `AccountState` interface. Each state class encapsulates behavior specific to that state.
- **Context (Account)**: The `Account` class acts as the context. It maintains an instance of a state object and delegates state-specific behavior to the current state object. The state of the account can change dynamically at runtime, altering its behavior.

### 4. Decorator Pattern

**Purpose**: 
The Decorator Pattern allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class.

**Usage in Your Project**:
- **AccountDecorator**: An abstract class that extends `Account` and contains a reference to another `Account` object. It delegates the calls to the wrapped account object, allowing additional behavior to be added.
- **Concrete Decorators**: `PremiumAccount` and `RewardsAccount` are concrete decorators that extend `AccountDecorator`. They add new functionalities (e.g., premium services, reward points) to the `Account` objects dynamically.

### 5. Factory Method Pattern

**Purpose**: 
The Factory Method Pattern defines an interface for creating an object but allows subclasses to alter the type of objects that will be created.

**Usage in Your Project**:
- **TransactionFactory**: This class encapsulates the creation logic for different types of transactions. Instead of creating transaction objects directly in the client code, the `TransactionFactory` provides a method to create transactions, promoting loose coupling and adherence to the single responsibility principle.

### Summary of Patterns

- **Command Pattern**: Encapsulates requests as objects, allowing for parameterization, queuing, and logging.
- **Facade Pattern**: Simplifies the interface to a complex subsystem, making it easier to use.
- **State Pattern**: Allows an object to change its behavior when its state changes.
- **Decorator Pattern**: Dynamically adds behavior to objects without affecting others.
- **Factory Method Pattern**: Defines an interface for creating objects, with subclasses determining the actual object type.

These patterns collectively improve the modularity, flexibility, and maintainability of the system, making it easier to extend and manage over time.
