# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
1- Test Design Using Input Space Partitioning

| **Method**               | **Characteristics**                                                              | **Blocks / Partitions**                                                                                          |
|--------------------------|----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `isValidDate`            | - Valid/Invalid Day<br> - Valid/Invalid Month<br> - Valid/Invalid Year<br> - Leap/Non-leap Year | - Day within month limits <br> - Day out of bounds <br> - Invalid month <br> - Invalid leap year date  |
| `isLeapYear`             | - Divisible by 4<br> - Divisible by 100<br> - Divisible by 400                    | - Leap year (`2020`)<br> - Non-leap year (`2019`)<br> - Divisible by 400 (leap year) (`2000`)<br> - Divisible by 100 but not leap year (`1900`)                  |
| `nextDate`               | - End of month<br> - End of year<br> - Leap/Non-leap Year                         | - Month transition <br> - Year transition <br> - February in leap year               |
| `previousDate`           | - Beginning of month<br> - Beginning of year<br> - Leap/Non-leap Year             | - Month transition <br> - Year transition <br> - February in leap year               |
| `compareTo`              | - Earlier Date<br> - Later Date<br> - Equal Date<br> - Comparison with `null`     | - Earlier date <br> - Equal date <br> - Later date <br> - Comparison with `null` (throws `NullPointerException`) |

## Common Characteristics

| **Common Characteristics**    | **Description**                                                       |
|---------------------------------|-----------------------------------------------------------------------|
| Valid/Invalid Day                      | All methods need to check if the day provided is valid.              |
| Valid/Invalid Month                   | Ensures that the month is within the valid range (1-12).            |
| Leap Year Handling                       | Leap year logic is needed to validate dates in February and for date transitions. |
| Year Boundaries                                       | Handling transitions at the end and beginning of a year.             |
| Comparison Logic                      | Requires date comparison logic to determine the validity and ordering of dates. |

2- After writing the `Date` class and partitioning the input space, we began crafting and refining the following test cases:
- **testValidDate :** Verifies valid dates (e.g., February 29 in a leap year).
- **testInvalidDate :** Checks various invalid date scenarios (e.g., February 30, month out of range).
- **testValidConstructor :** Confirms that the constructor does not throw an exception for valid dates.
-  **testInvalidConstructor :** ensures that an `IllegalArgumentException` is thrown for invalid dates.
- **testIsLeapYear :** Tests both leap years and non-leap years.
- **testPreviousDateMonth** : Assesses transition from the first day of a month to the last day of the previous month.
- **testPreviousDateDay :** Verifies the transition from one day to the previous day.
- **testPreviousDateYear :** Checks the transition from January 1 to December 31 of the previous year.
- **testPreviousDateLeapYear :** Validates transition from March 1 to February 29 in a leap year.
- **testNextDateYear :** Confirms transition from December 31 to January 1 of the next year.
- **testNextDateLeapYear :** Tests transition from February 28 to February 29 in a leap year.
- **testNextDateMonth :** Assesses transition from the last day of a month to the first day of the next month.
- **testCompareTo_Null :** Ensures `compareTo` throws a `NullPointerException` for null comparisons.
- **testCompareTo1 :** Validates comparison of two dates to confirm the earlier date.
- **testCompareTo_Equal :** Confirms that equal dates return zero in comparison.
- **testCompareTo2 :** Checks that a later month is identified correctly.
- **testCompareTo3 :** Validates that a later day in the same month is identified correctly.

By implementing these test cases, we have ensured comprehensive coverage of the `Date` class's functionality.

3- The `isLeapYear` method has the following logic:

```java
public static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}
```
This method contains three boolean expressions combined using logical operators:
- year % 4 == 0
- year % 100 != 0
- year % 400 == 0

Given these operators, we need to ensure that all possible outcomes of these conditions are covered by our test cases. 
Base Choice Coverage requires that each possible outcome of each boolean condition be tested at least once.

The existing test cases related to isLeapYear are:
- Year 2020 (leap year) → Covers year % 4 == 0 → true
- Year 2019 (not a leap year) → Covers the negation of year % 4 == 0 → false
- Year 2000 (leap year, divisible by 400) → Covers year % 400 == 0 → true
- Year 1900 (not a leap year, divisible by 100) → Covers year % 100 == 0 (but is not a leap year) → false

While the existing tests cover several scenarios, they do not fully cover the logical paths of the isLeapYear method:
We add those tests:
- A year that is divisible by 4 but not by 100 (1996).
- A year that is divisible by 100 but not by 400 (2100).
  
By adding these two new asserts, we ensure that all paths of the boolean logic in the isLeapYear method are tested.

4- After running the PIT mutation testing tool on the Date class test suite, we obtained the following statistics:

<img width="599" alt="gh" src="https://github.com/user-attachments/assets/d257c914-1e88-4598-9700-e9a7e2bef140">

- **Line Coverage**: Achieving 100% line coverage indicates that every line of code in the Date class was executed during the tests. This is **a strong indicator** that our test cases are thorough in terms of execution.

- **Mutation Score**: The mutation score of 86% signifies that the test suite was able to detect 86% of the mutations in the code. Which is is **a good score** suggesting that the majority of logical paths were effectively tested.



