# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion assertTrue(3 * 0.4 == 1.2) fails due to **floating-point precision errors**. Since floating-point numbers are stored in binary, certain decimal values, like 0.4, cannot be represented exactly. As a result, calculations such as 3 * 0.4 may yield a result slightly different from 1.2, like 1.2000000000000002, causing the assertion to fail.
When comparing floating-point numbers, it's better **to check if they are approximately equal within a small tolerance (epsilon)** rather than expecting exact equality.

2.
assertEquals and assertSame are two methods **used to compare values**, but :
- assertEquals: Compares the **values** of two objects. It checks whether two objects are equivalent.
- assertSame: Compares the **references** of two objects. It checks whether two references point to the exact same object.
  
### Scenarios where **they produce the same result** :

``` java
Person p1 = new Person("xxx", 30);
Person p2 = p1; 
assertEquals(p1, p2);
assertSame(p1, p2);
```
In this case, both assertEquals(p1, p2) and assertSame(p1, p2) would pass because both references (person1 and person2) point to the exact same object in memory.

### Scenarios where **they do not produce the same result** :

``` java
Person p1 = new Person("xxx", 30);
Person p2 = new Person("xxx", 30);
assertEquals(p1, p2);
assertSame(p1, p2);
```
In this case, assertEquals(p1, p2) passes because, although p1 and p2 are different instances, they are considered equal based on their content.
However, assertSame(p1, p2) fails because p1 and p2 are two different objects in memory, despite having the same content.

3. Uses of fail in Unit Testing : [source](https://www.baeldung.com/junit-fail)

- **Incomplete Test** : We can fail a test when it is incomplete or not yet implemented.
``` java
@Test
public void testFeature() {
    fail("Test not implemented");
}
```

- **Expected or Unexpected Exception** : Use fail when expected conditions are not met.
``` java
@Test
public void testExceptionThrown() {
    try {
        methodThrowExpectedException();  
        fail("Exception expected but not thrown"); 
    } catch (ExpectedException e) {

    }
}
```

- **Testing Condition** : We can call fail when a result doesn’t meet some desired condition.
``` java
@Test
public void testCondition() {
    String username = getUserFromDatabase();
    if (username == null || username.isEmpty()) {
        fail("Username should not be null or empty");
    }
}

```
4.
In JUnit 4, exceptions were handled using the `@Test()` annotation, but this approach had limitations:
- It only checked if an exception was thrown anywhere in the test, without specifying where.
- It allowed testing for only one exception per test method, making it harder to handle multiple cases.

JUnit 5 introduced `assertThrows` to address these issues.
Its advantages include:
- It makes tests clearer by wrapping only the specific code expected to throw an exception.
- It allows more precise testing of multiple exceptions within the same test.
  
