# Billboard
Concepts of programing with Kotlin to Desktop environment.

Accessing data in mongoDb Atlas
## Step 1 -  Storage, Domain and UI (cli mode)
### Design principles
* [Type-Driven Development](https://blog.ploeh.dk/2015/08/10/type-driven-development/): 
   * [Design Smell: Primitive obsession ](https://blog.ploeh.dk/2011/05/25/DesignSmellPrimitiveObsession/)
* Simplicity ([KISS](https://en.wikipedia.org/wiki/KISS_principle))
* [DRY - Don't Repeat Yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
* [SOLID](https://www.educative.io/answers/what-are-the-solid-principles-in-java):
  * [Single Responsibility Principle](https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html) - Every class should have a single job to do.
  * [Open-Closed Principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle) - open for an extension, but closed for modification.
  * Liskov Substitution Principle - the reference to the Base class can be replaced with a Derived class
  * Interface Segregation Principle -clients should not be forced to depend upon interface members they do not use.
  * Dependency Inversion Principle- depend on abstractions (interfaces and abstract classes)
  instead of concrete implementations (classes).
* Testability

### Design patterns
* [The Decorator design pattern](https://en.wikipedia.org/wiki/Decorator_pattern)
* [The Command design pattern](https://en.wikipedia.org/wiki/Command_pattern)

### Mongo DB

To use mongodb  with this project you need to:
1. Create a MongoDB Cloud account.
2. Create a MongoDB Atlas cluster.
3. Configure network access and create a cluster user.
4. Connect to the cluster.
5. Get the connection string to use in the application,

On the desktop you need to create two environment variables:
* MONGO_DB_NAME - with the database name
* MONGO_DB_CONNECTION - with the connection string.

Resources:
* [Getting started with Mongo DB Atlas](https://www.mongodb.com/basics/mongodb-atlas-tutorial)
* [Using KMongo](https://litote.org/kmongo/quick-start/#with-the-sync-driver)

