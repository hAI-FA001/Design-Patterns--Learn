# Design Patterns

Learning and keeping a list of design patterns by implementing them, in different programming languages.

## Summary/Notes

- Factory
  - only 1 method exposed to client
  - instantiation logic is in 1 place
- Builder
  - eliminates the need of ctors for all combinations of values
    - tho, we can do this using default parameters if language supports it
  - can orchestrate the steps or change their order
- Abstract Factory
- Singleton
  - expose 1 global instance
  - eager/lazy init
  - need to handle the case of multiple threads accessing the singleton for the first time

<br>

- Decorator
  - Add functionality on top of a class
  - Might be we can't modify the class (no source code), we don't want to (prevent breaking the class) or it's a minor thing only for our code
- Proxy
  - Wrap our interface around another for controlled access or other logic such as validation
  - e.g. add security checks or moderation
- Facade
  - Wrap our interface around another to simplify interaction by hiding complex details
  - like when we make our own wrappers around some library to simplify function calls
- Adapter
  - wrap a class so it can be used with another interface
- Composite
  - model a tree-like hierarchy
- Flyweight
  - reduce memory footprint by moving intrinsic data into its own object (so this instance can be shared)
  - e.g. in game dev, we have tons of objects
- Bridge
  - Move logic into its own interface

<br>

- Strategy
  - Provide the way/function to perform something
  - When have multiple ways to do something
  - e.g. different ways for payment
- Memento
  - Save/load state w/o exposing internals
- Observer
  - notify when something happens
  - Pub/Sub
- Iterator
  - uniform interface to iterate over objects
- Mediator
  - Objects talk to each other through Mediator rather than directly
  - mediator stores the objects
- Command
  - turn actions into classes, so we can parametrize them & reduce code duplication
  - e.g. Copy command/action can be done via buttons, context menu, Ctrl+C, etc
- Interpreter
  - Evaluate expression based on provided context
  - e.g. grammar in Theory of Automata
- State
  - Alter behavior based on state object
  - state obj has functions, so different states can do different things when we call their functions
- Chain of Responsibility
  - Pass a message internally among the chain of objects
  - relevant objs will process it
- Template Method
  - Provide a fixed workflow/common steps while providing flexibility of how to perform each step (or additional steps)
- Visitor
  - Add new operation w/o modifying the class
  - Same class can accept multiple Visitors
