# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

1- Input Space Partitioning Table 

| Method        | Characteristics                      | Description                                                                                 |
|---------------|--------------------------------------|---------------------------------------------------------------------------------------------|
| **pop()**     | Non-empty heap                       | Returns and removes the minimum element, maintaining the heap property.                    |
|               | Empty heap                           | Throws `NoSuchElementException`.                                                            |
| **peek()**    | Non-empty heap                       | Returns the minimum element without removing it, ensuring the heap structure remains unchanged. |
|               | Empty heap                           | Throws `NoSuchElementException`.                                                            |
| **push(T)**   | Valid value (positive and negative) | Adds the given element to the heap, ensuring that the heap property is maintained.         |
|               | Duplicate element                    | Correctly handles duplicates and maintains the heap property.                               |
|               | Multiple elements                    | Efficiently adds multiple elements to the heap. |
| **count()**   | Empty heap                  | Returns 0, confirming the absence of elements.                               |
|               |  Non-empty heap                                    | Returns the correct count of elements, reflecting the current size of the heap. |

**Common Characteristics :**

- **State of the Heap**: Each method appropriately handles scenarios for both empty and populated heaps while respecting binary heap properties.
- **Order Maintenance**: All methods maintain the binary heap order. After every push(), the heap reorganizes to uphold its properties. Both pop() and peek() ensure the next smallest element is correctly identified and returned while maintaining order.

2- 
In our project, we set out to design a suite of tests for the `BinaryHeap` class to cover a variety of scenarios.
Here’s the tests we created:

1. **For `pop()`**:
   - **`testPopOnEmptyHeap()`**: This checks if the method throws an exception when trying to pop from an empty heap.
   - **`testPop()`**: This test ensures that when the heap has elements, it correctly returns and removes the minimum element.

2. **For `peek()`**:
   - **`testPeekOnEmptyHeap()`**: Similar to `pop()`, this test checks that it throws an exception when the heap is empty.
   - **`testPeek()`**: This checks that we can retrieve the minimum element without removing it, ensuring that the heap structure is unchanged.

3. **For `count()`**:
   - **`testEmptyCount()`**: This test confirms that the count is zero when the heap is empty.
   - **`testCount()`**: This verifies that the method returns the correct count of elements when the heap is populated.

4. **For `push()`**:
   - **`testPushSingleElement()`**: Tests the addition of a single element to the heap.
   - **`testDuplicates()`**: Ensure that the heap handles duplicates correctly.
   - **`testOrder_MultipleInserts()`**: Verify that the heap maintains the correct order when multiple elements are pushed.

Additionally, we included tests for:
- **`testMaxMinValues()`**: This checks how the heap handles extreme values, like `Integer.MAX_VALUE` and `Integer.MIN_VALUE`.
- **`testMixedNumbers()`**: This test validates the heap’s behavior when handling a mix of negative and positive numbers.
- **`testOrder()`**: This ensures that the order of elements is preserved when popping from the heap.

3- Based on the results from the mutation testing using PIT, here are the statistics:

<img width="523" alt="pit3" src="https://github.com/user-attachments/assets/374759ad-490e-43e9-b83a-c0d28ddb7dfd">

Line Coverage: 43/43 (100%): This indicates that every line in our class has been executed by the tests, achieving full line coverage. This ensures that all logical branches and paths in the code are tested.

Generated Mutations: 33 mutations were created during the testing process, out of which 29 were killed, resulting in a kill rate of 88%. This is a solid score for mutation testing.

In the end, these results show that the test suite for the BinaryHeap class is comprehensive and robust, effectively covering all lines of code and detecting the majority of introduced faults.
