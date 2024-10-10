# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

We chose the JUnit4SuitesShouldUseSuiteAnnotation rule to identify test smells in the Apache Commons Collections project. This rule helps to catch **outdated test suite definitions**, using the old suite() method from JUnit 3 instead of annotations introduced in JUnit 4.

To apply this rule, we used the following PMD command:

```bash
pmd check -d "..\commons-collections-master" -R category/java/bestpractices.xml/JUnit4SuitesShouldUseSuiteAnnotation -f text -r "...\pmd_results\result_rule.txt"
```

The command generated a report that flagged the following issue:

```
..\commons-collections-master\src\test\java\org\apache\commons\collections4\GuavaTestlibTest.java:55:
JUnit4SuitesShouldUseSuiteAnnotation: JUnit 4 indicates test suites via annotations, not the suite method.
```

This error highlights that in `GuavaTestlibTest.java`, the `suite()` method is being used, which is a JUnit 3 practice. However, JUnit 4 introduced a **cleaner way** to define test suites using annotations like `@RunWith(Suite.class)` and `@Suite.SuiteClasses`, making the `suite()` method unnecessary.

Here’s what happened in the `GuavaTestlibTest.java` file:

```java
public class GuavaTestlibTest {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new SomeTest());  
        return suite;
    }
}
```
-> While still functional, this method is outdated in JUnit 4.

To remove this test smell, the `suite()` method should be replaced with JUnit 4’s simpler, **annotation-based approach**. We no longer need the `suite()` method, we can specify test classes directly using `@RunWith(Suite.class)` and `@Suite.SuiteClasses`.

Here’s how to refactor the `GuavaTestlibTest.java` file using the modern JUnit 4 approach:

``` java
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SomeTest.class,
    AnotherTest.class
})
public class GuavaTestlibTest {}
```

In conclusion, by using JUnit 4's suite annotations, we modernize the test suite, improve readability, and make future maintenance of the test code simpler.
