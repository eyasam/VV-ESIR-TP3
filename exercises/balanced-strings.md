# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1- We identified the characteristics of the input string by analyzing different possible forms of inputs and their impact on the string's balance. Each characteristic of the input string leads to distinct partition blocks that represent different scenarios :
1. Whether the input contains grouping symbols or not.
2. Input contains only one (odd number) unmatched symbol, either opening or closing.
3. Whether the input consists of only one type of symbol or multiple types.
4. The symbols are either correctly paired and nested or incorrectly paired or missing matches.
5. Some inputs may contain a combination of non-symbol characters and symbols.

Based on these characteristics, we defined the following partition blocks to cover all possible scenarios of balanced and unbalanced strings:

| Partition Block                                   | Examples                                           | Expected Result |
|---------------------------------------------------|----------------------------------------------------|------------------|
| Empty string without symbols| `""`, `"abc"`                                     | true             |
| **Balanced strings**|                                                    |                  |
| String with a single type of symbol | `"()"`, `"[]"`, `"{}"`                            | true             |
| String with multiple types of symbols (nested balanced) | `"{[()]}"`, `"{{[]}}"`, `"{()}"`                  | true             |
| String containing mixed characters and symbols     | `"{abc[def](ghi)}"`                               | true             |
| **Unbalanced strings**  |                                                    |                  |
| Opening without closing | `"("`, `"[["`, `"{}{"`              | false            |
| Closing without opening | `"}"`, `"))"`, `"][]"`      | false            |
| Mixed symbols and characters | `"{[(])}"`, `"{[}]}"`, `"([)]"`, `"{abc[def}(ghi)]"`, `"{(text)[]}"` | false            |

2- After writing the isBalanced() method, we created initial test cases. During coverage evaluation, we found that certain conditions were not fully covered, particularly when the stack was empty. To increase coverage, we added new test cases to address these scenarios.

Here’s our test cases to ensure complete statement coverage:

- `testEmptyString()`:
This case ensures that an empty string is considered balanced.

- `testNoSymbols()`:
This case ensures that a string with no (), [], or {} is considered balanced.

- `testBalancedSimpleSymbols()`:
Test that simple balanced symbols (parentheses, brackets, braces) are recognized.

- `testBalancedMultipleSymbols()`:
Test that multiple nested balanced symbols are correctly recognized.

-`testUnmatchedOpeningSymbols()` and `testUnmatchedClosingSymbols()`:
These tests cover cases where there's a mismatch between opening and closing symbols.

- `testMismatchedSymbols()`:
Test that mismatched symbols are considered unbalanced.

-`testMixedContent()`:
Test strings with mixed content (symbols and other characters) to ensure correct balancing

3- In the `isBalanced()` method, the key predicate that involves more than two boolean operators is found in the following condition:
``` java
if ((ch == ')' && sommet != '(') || (ch == '}' && sommet != '{') || (ch == ']' && sommet != '[')) {
    return false;
}
```
This predicate checks **three separate conditions** using both **&&** and **||** operators. To satisfy Base Choice Coverage, each of these conditions needs to be evaluated individually in different test cases. However, we have already focused on them in the test cases.

Example for Condition: `ch == ')' && sommet != '('`
- Covered by the test case `testMismatchedSymbols()`:
    ```java
    assertFalse(isBalanced("([)]"));
    ```
4- We used PIT to generate 20 mutations, all of which were killed, resulting in **100% mutation coverage**. There are no live mutants, indicating that our test cases effectively detect all introduced changes.

The line coverage is **92%** (12/13 lines covered), meaning one line is not triggered by the current tests.

Test strength is **100%**, with an average of **1.75 tests per mutation**, showing that a variety of scenarios were tested. This confirms the robustness of the test suite, though we can add a test to cover the missing line.

As shown in the picture below:

<img width="592" alt="pit" src="https://github.com/user-attachments/assets/dec83b8c-9677-4b5e-bdbf-9aedb438c224">
