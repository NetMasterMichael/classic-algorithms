import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.netmastermichael.algorithmsuitex.algorithms.classic.sorting.BubbleSort;
import com.github.netmastermichael.algorithmsuitex.algorithms.classic.sorting.ManualSorter;
import com.github.netmastermichael.algorithmsuitex.algorithms.classic.sorting.SelectionSort;
import com.github.netmastermichael.algorithmsuitex.algorithms.classic.sorting.SortingAlgorithmOperation;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

class TestManualSorter {

  final String className = "TestManualSorter";

  @Test
  void testCreateManualSorter() {
    String testName = "testCreateManualSorter";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertTrue(testManualSorter instanceof ManualSorter,
          "Test that a new instance of ManualSorter is created successfully");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing creating an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testGetArray() {
    String testName = "testGetArray";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertTrue(Arrays.equals(new int[] {1, 2, 3}, testManualSorter.getArray()),
          "Test that getArray() returns the correct array from a ManualSorter instance");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing getArray() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testEnqueueOperation() {
    String testName = "testEnqueueOperation";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 0, 1);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 1);
      assertTrue(true, "Test that enqueueOperation() executes successfully with no exceptions on "
          + "a ManualSorter instance");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing enqueueOperation() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testSwapOperation() {
    String testName = "testSwapOperation";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertTrue(Arrays.equals(new int[] {1, 2}, testManualSorter.getArray()),
          "Test that array in ManualSorter is the original array before the swap operation");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 1);
      testManualSorter.step();
      assertTrue(Arrays.equals(new int[] {2, 1}, testManualSorter.getArray()),
          "Test that array in ManualSorter is modified after the swap operation");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing a swap operation with an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testIsSorted() {
    String testName = "testIsSorted";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3, 5, 4, 6, 7},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 3, 4);
      assertFalse(testManualSorter.isSorted(),
          "Test that isSorted returns false when array is not sorted");
      testManualSorter.step();
      assertTrue(testManualSorter.isSorted(),
          "Test that isSorted returns true when array is sorted");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing the isSorted() method on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testIsSortable() {
    String testName = "testIsSortable";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      int[] array = new int[] {12, 23, 15, 22, 17, 6, 19, 8, 1, 3, 16, 24, 10, 7, 5, 25, 18, 13, 14,
          9, 4, 20, 2, 11, 21};
      BubbleSort bubbleSorter = new BubbleSort(array);


      ManualSorter testManualSorter = bubbleSorter.preComputeManualSort();
      assertEquals(454, testManualSorter.isSortable(-1),
          "Test that ManualSorter.isSortable() can verify if an array is "
              + "sortable using operations generated by a BubbleSort object");
      assertEquals(-1, testManualSorter.isSortable(10),
          "Test that ManualSorter.isSortable() will return -1 when the "
              + "operations limit provided is too small");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 6);
      assertEquals(454, testManualSorter.isSortable(-1),
          "Test that ManualSorter.isSortable() returns the correct number "
              + "of operations required to sort an array, excluding unnecessary operations");

      SelectionSort selectionSorter = new SelectionSort(array);
      testManualSorter = selectionSorter.preComputeManualSort();
      assertEquals(318, testManualSorter.isSortable(-1),
          "Test that ManualSorter.isSortable() can verify if an array is "
              + "sortable using operations generated by a BubbleSort object");
      assertEquals(-1, testManualSorter.isSortable(10),
          "Test that ManualSorter.isSortable() will return -1 when the "
              + "operations limit provided is too small");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 6);
      assertEquals(318, testManualSorter.isSortable(-1),
          "Test that ManualSorter.isSortable() returns the correct number "
              + "of operations required to sort an array, excluding unnecessary operations");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing isSortable() on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetCurrentIndexA() {
    String testName = "testGetCurrentIndexA";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(-1, testManualSorter.getCurrentIndexA(),
          "Test that currentIndexA in a ManualSorter instance starts at -1");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 1, 2);
      assertEquals(-1, testManualSorter.getCurrentIndexA(),
          "Test that currentIndexA in a ManualSorter instance is still -1 after "
              + "enqueueing an operation and before execution");
      testManualSorter.step();
      assertEquals(1, testManualSorter.getCurrentIndexA(),
          "Test that currentIndexA in a ManualSorter instance is now 1 after execution");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing testGetCurrentIndexA() on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetCurrentIndexB() {
    String testName = "testGetCurrentIndexB";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(-1, testManualSorter.getCurrentIndexB(),
          "Test that currentIndexB in a ManualSorter instance starts at -1");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 1, 2);
      assertEquals(-1, testManualSorter.getCurrentIndexB(),
          "Test that currentIndexB in a ManualSorter instance is still -1 after "
              + "enqueueing an operation and before execution");
      testManualSorter.step();
      assertEquals(2, testManualSorter.getCurrentIndexB(),
          "Test that currentIndexB in a ManualSorter instance is now 2 after execution");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing testGetCurrentIndexB() on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetCurrentOperationType() {
    String testName = "testGetCurrentOperationType";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(null, testManualSorter.getCurrentOperationType(),
          "Test that currentOperationType in a ManualSorter instance starts as null");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 1, 2);
      assertEquals(null, testManualSorter.getCurrentOperationType(),
          "Test that currentOperationType in a ManualSorter instance is still null "
              + "after enqueueing an operation and before execution");
      testManualSorter.step();
      assertEquals(SortingAlgorithmOperation.SWAP, testManualSorter.getCurrentOperationType(),
          "Test that currentOperationType in a ManualSorter instance is now SWAP after execution");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing testGetCurrentOperationType() on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testStepOnEmptyDequeReturnsFalse() {
    String testName = "testStepOnEmptyDequeReturnsFalse";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertFalse(testManualSorter.step(),
          "Test that stepping on a ManualSorter instance with no queued operations returns false");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 1);
      assertTrue(testManualSorter.step(),
          "Test that stepping on a ManualSorter instance after enqueueing an "
              + "operation returns true");
      assertFalse(testManualSorter.step(),
          "Test that stepping on a ManualSorter instance with no more queued "
              + "operations returns false");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing testStepOnEmptyDequeReturnsFalse() on a ManualSorter instance; "
          + e.getMessage());
    }
  }

  @Test
  void testMoveLiteralOperation() {
    String testName = "testMoveLiteralOperation";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertTrue(
          Arrays.equals(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, testManualSorter.getArray()),
          "Test that array in ManualSorter is the original array before the "
              + "move literal operation");
      for (int i = 0; i < 10; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, i + 1, i);
      }
      for (int i = 0; i < 10; i++) {
        testManualSorter.step();
      }
      assertTrue(
          Arrays.equals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, testManualSorter.getArray()),
          "Test that array in ManualSorter matches the expected array after the "
              + "move literal operations");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing the move literal operation with an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testGetUsingTemporaryArraysStatus() {
    String testName = "testGetUsingTemporaryArraysStatus";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertFalse(testManualSorter.getUsingTemporaryArraysStatus(),
          "Test that testGetUsingTemporaryArraysStatus() returns false upon the "
              + "creation of a new ManualSorter instance");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing getUsingTemporaryArraysStatus() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testSetUsingTemporaryArraysStatus() {
    String testName = "testSetUsingTemporaryArraysStatus";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertFalse(testManualSorter.getUsingTemporaryArraysStatus(),
          "Test that testGetUsingTemporaryArraysStatus() returns false upon the "
              + "creation of a new ManualSorter instance");
      testManualSorter.setUsingTemporaryArraysStatus(true);
      assertTrue(testManualSorter.getUsingTemporaryArraysStatus(),
          "Test that testGetUsingTemporaryArraysStatus() returns true after calling "
              + "setUsingTemporaryArraysStatus(true)");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing setUsingTemporaryArraysStatus() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testGetPrimarySelectedArray_Key() {
    String testName = "testGetPrimarySelectedArray_Key";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(-1, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getPrimarySelectedArray_Key() returns -1 in a newly created "
              + "ManualSorter instance");
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 15, 4);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 0, 1, 4);
      assertTrue(testManualSorter.step(),
          "Test that testManualSorter steps and creates an array successfully");
      assertTrue(testManualSorter.step(),
          "Test that testManualSorter steps and loads a compare operation successfully");
      assertEquals(4, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getPrimarySelectedArray_Key() returns 4 after comparing elements in array 4");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing getPrimarySelectedArray_Key() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testGetPrimarySelectedArray_Array() {
    String testName = "testGetPrimarySelectedArray_Array";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(null, testManualSorter.getPrimarySelectedArray_Array(),
          "Test that getPrimarySelectedArray_Array() returns null in a newly "
              + "created ManualSorter instance");
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 4);
      for (int i = 0; i < 10; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, i + 1, i, 4);
      }
      for (int i = 0; i < 11; i++) {
        assertTrue(testManualSorter.step(), "Test that the step was executed successfully");
      }
      assertTrue(
          Arrays.equals(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that testGetPrimarySelectedArray_Array() returns the correct "
              + "array after comparing elements in array 4");
      assertTrue(Arrays.equals(new int[] {1, 2, 3}, testManualSorter.getArray()),
          "Test that the original array in testManualSorter remains unmodified");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing testGetPrimarySelectedArray_Array() on an "
          + "instance of ManualSorter; " + e.getMessage());
    }
  }

  @Test
  void testMultiArrayOperation_Create_Array() {
    String testName = "testMultiArrayOperation_Create_Array";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 0);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 1);
      assertFalse(testManualSorter.step(),
          "Test that a ManualSorter instance will not overwrite the primary array");
      assertTrue(testManualSorter.step(),
          "Test that a ManualSorter will create a new array of size 10 at key 1");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing a CREATE_ARRAY operation on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testMultiArrayOperation_Move_Literal() {
    String testName = "testMultiArrayOperation_Move_Literal";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3, 4, 5},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 1);
      for (int i = 0; i < 10; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 10, i,
            1);
      }
      for (int i = 0; i < 11; i++) {
        assertTrue(testManualSorter.step(), "Test that the step is executed successfully");
      }
      assertTrue(
          Arrays.equals(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that the temporary array is populated as expected");
      assertTrue(Arrays.equals(new int[] {1, 2, 3, 4, 5}, testManualSorter.getArray()),
          "Test that the original array has not been modified");
      for (int i = 0; i < 5; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 50, i,
            0);
      }
      for (int i = 0; i < 5; i++) {
        assertTrue(testManualSorter.step(), "Test that the step is executed successfully");
      }
      assertTrue(Arrays.equals(new int[] {50, 100, 150, 200, 250}, testManualSorter.getArray()),
          "Test that the original array is updated as expected");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing a MOVE_LITERAL operation on "
          + "an instance of ManualSorter; " + e.getMessage());
    }
  }

  @Test
  void testMultiArrayOperation_Compare() {
    String testName = "testMultiArrayOperation_Compare";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3, 4, 5},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 4, 1);
      for (int i = 0; i < 4; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 10, i,
            1);
      }
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 3, 4, 0);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 0, 1, 1);
      for (int i = 0; i < 6; i++) {
        // All CREATE_ARRAY and MOVE_LITERAL operations are executed, as well as the
        // first COMPARE operation
        assertTrue(testManualSorter.step(), "Test that the step is executed successfully");
      }
      assertEquals(3, testManualSorter.getCurrentIndexA(), "Test that getCurrentIndexA returns 3");
      assertEquals(4, testManualSorter.getCurrentIndexB(), "Test that getCurrentIndexB returns 4");
      assertEquals(0, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getCurrentSelectedArray_Key returns 3");
      assertTrue(testManualSorter.step(), "Test that the next step is executed successfully");
      assertEquals(0, testManualSorter.getCurrentIndexA(), "Test that getCurrentIndexA returns 0");
      assertEquals(1, testManualSorter.getCurrentIndexB(), "Test that getCurrentIndexB returns 1");
      assertEquals(1, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getCurrentSelectedArray_Key returns 1");
      assertFalse(testManualSorter.step(),
          "Test that there are no operations remaining and step() returns false");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing testMultiArrayOperation_COMPARE() on an "
          + "instance of ManualSorter; " + e.getMessage());
    }
  }

  @Test
  void testMultiArrayOperation_Swap() {
    String testName = "testMultiArrayOperation_Swap";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3, 4, 5},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 4, 1);
      for (int i = 0; i < 4; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 10, i,
            1);
      }
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 3, 4, 0);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 1, 1);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.SWAP, 0, 0, 0, 1);
      for (int i = 0; i < 6; i++) {
        // All CREATE_ARRAY and MOVE_LITERAL operations are executed, as well as the
        // first SWAP operation
        assertTrue(testManualSorter.step(), "Test that the step is executed successfully");
      }
      assertTrue(Arrays.equals(new int[] {1, 2, 3, 5, 4}, testManualSorter.getArray()),
          "Test that the SWAP operation was executed on the original array "
              + "successfully in multi-array mode");
      assertTrue(
          Arrays.equals(new int[] {1, 2, 3, 5, 4},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that the SWAP operation was executed on the original array "
              + "successfully in multi-array mode");
      assertEquals(3, testManualSorter.getCurrentIndexA(), "Test that getCurrentIndexA returns 3");
      assertEquals(4, testManualSorter.getCurrentIndexB(), "Test that getCurrentIndexB returns 4");
      assertEquals(0, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getPrimarySelectedArray_Key returns 3");
      assertTrue(testManualSorter.step(), "Test that the next step is executed successfully");
      assertTrue(Arrays.equals(new int[] {1, 2, 3, 5, 4}, testManualSorter.getArray()),
          "Test that the original array has not been touched");
      assertTrue(
          Arrays.equals(new int[] {20, 10, 30, 40},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that the SWAP operation was executed on the temporary array "
              + "successfully in multi-array mode");
      assertEquals(0, testManualSorter.getCurrentIndexA(), "Test that getCurrentIndexA returns 0");
      assertEquals(1, testManualSorter.getCurrentIndexB(), "Test that getCurrentIndexB returns 1");
      assertEquals(1, testManualSorter.getPrimarySelectedArray_Key(),
          "Test that getPrimarySelectedArray_Key returns 1");
      assertTrue(testManualSorter.step(), "Test that the next step is executed successfully");
      assertTrue(
          Arrays.equals(new int[] {20, 2, 3, 5, 4},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that the SWAP operation between two different arrays correctly "
              + "modified the original array");
      assertTrue(
          Arrays.equals(new int[] {1, 10, 30, 40},
              testManualSorter.getSecondarySelectedArray_Array()),
          "Test that the SWAP operation between two different arrays correctly "
              + "modified the temporary array");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing a SWAP operation on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testMultiArrayOperation_Delete_Array() {
    String testName = "testMultiArrayOperation_Delete_Array";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3, 4, 5},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 4, 1);
      for (int i = 0; i < 4; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 10, i,
            1);
      }
      for (int i = 0; i < 5; i++) {
        // All CREATE_ARRAY and MOVE_LITERAL operations are executed
        assertTrue(testManualSorter.step(), "Test that the step is executed successfully");
      }
      assertTrue(
          Arrays.equals(new int[] {10, 20, 30, 40},
              testManualSorter.getPrimarySelectedArray_Array()),
          "Test that getCurrentSelectedArray_Array() returns the populated "
              + "array after creating it");
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.DELETE_ARRAY, 1, 0);
      assertTrue(testManualSorter.step(),
          "Test that the DELETE_ARRAY operation is executed successfully");
      assertEquals(null, testManualSorter.getPrimarySelectedArray_Array(),
          "Test that getCurrentSelectedArray_Array() returns null after "
              + "deleting the temporary array");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing a DELETE_ARRAY operation "
          + "on an instance of ManualSorter; " + e.getMessage());
    }
  }

  @Test
  void testGetSecondarySelectedArray_Key() {
    String testName = "testGetSecondarySelectedArray_Key";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(-1, testManualSorter.getSecondarySelectedArray_Key(),
          "Test that getSecondarySelectedArray_Key() returns -1 in a newly "
              + "created ManualSorter instance");
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 15, 4);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 15, 6);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 0, 1, 4, 6);
      assertTrue(testManualSorter.step(),
          "Test that testManualSorter steps and creates an array successfully");
      assertTrue(testManualSorter.step(),
          "Test that testManualSorter steps and creates an array successfully");
      assertTrue(testManualSorter.step(),
          "Test that testManualSorter steps and loads a compare operation successfully");
      assertEquals(6, testManualSorter.getSecondarySelectedArray_Key(),
          "Test that getSecondarySelectedArray_Key() returns 6 after comparing "
              + "elements in array 4");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e
          + " thrown while testing getSecondarySelectedArray_Key() on an instance of ManualSorter; "
          + e.getMessage());
    }
  }

  @Test
  void testGetSecondarySelectedArray_Array() {
    String testName = "testGetSecondarySelectedArray_Array";
    AuxiliaryTestMethods.logMessage(className, testName + " started");
    try {
      ManualSorter testManualSorter = new ManualSorter(new int[] {1, 2, 3},
          new LinkedList<SortingAlgorithmOperation>(), new LinkedList<Integer>());
      assertEquals(null, testManualSorter.getSecondarySelectedArray_Array(),
          "Test that getSecondarySelectedArray_Array() returns null in a newly "
              + "created ManualSorter instance");
      testManualSorter.setUsingTemporaryArraysStatus(true);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 4);
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.CREATE_ARRAY, 10, 7);
      for (int i = 0; i < 10; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, i + 1, i, 4);
      }
      for (int i = 0; i < 10; i++) {
        testManualSorter.enqueueOperation(SortingAlgorithmOperation.MOVE_LITERAL, (i + 1) * 10, i,
            7);
      }
      testManualSorter.enqueueOperation(SortingAlgorithmOperation.COMPARE, 0, 0, 4, 7);
      for (int i = 0; i < 23; i++) {
        assertTrue(testManualSorter.step(), "Test that the step was executed successfully");
      }
      assertTrue(
          Arrays.equals(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100},
              testManualSorter.getSecondarySelectedArray_Array()),
          "Test that getSecondarySelectedArray_Array() returns the correct array "
              + "after comparing elements in array 4 and 7");
      assertTrue(Arrays.equals(new int[] {1, 2, 3}, testManualSorter.getArray()),
          "Test that the original array in testManualSorter remains unmodified");
      AuxiliaryTestMethods.logPass(className, testName);
    } catch (Exception e) {
      AuxiliaryTestMethods.logFail(className, testName);
      fail("Exception " + e + " thrown while testing getSecondarySelectedArray_Array() on an "
          + "instance of ManualSorter; " + e.getMessage());
    }
  }
}