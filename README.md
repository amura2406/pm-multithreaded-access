# Multithreaded Access Problem

This repo demonstrates how to safely access an object from multiple threads.
Each thread will call different method on the same object but each method may have
dependencies before it can be called. The dependencies may be changed by another thread.

Example:

Suppose a class called Foo:

```java
class Foo {
    Foo() {...}
    void first() {...}
    void second() {...}
    void third() {...}
  }
```

There are 3 threads:

* Thread A call `first()`
* Thread B call `second()`
* Thread C call `third()`

But, `first()` must be called before `second()`
and `second()` must be called before `third()`