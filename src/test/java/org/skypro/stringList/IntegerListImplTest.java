package org.skypro.stringList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.exceptions.IllegalIndexException;
import org.skypro.exceptions.IllegalInputException;
import org.skypro.exceptions.NullInputException;
import org.skypro.stringList.IntegerListImpl;

public class IntegerListImplTest {
    private IntegerListImpl integerList;

    @BeforeEach
    public void setUp() {
        integerList = new IntegerListImpl(4);
    }

    public void listFill(IntegerListImpl integerListTest, boolean full) {
        int size = full ? integerListTest.getInitLength() : integerListTest.getInitLength() / 2;
        for (int i = 0; i < size; i++) {
            integerListTest.add(i);
        }
    }

    @Test
    public void shouldThrowNullInputExceptionAddTest() {
        Assertions.assertThrows(NullInputException.class, () -> integerList.add(null));
    }

    @Test
    public void shouldDoubleLengthAddTest() {
        listFill(integerList, true);
        double initLengthExpected = 1.5 * integerList.getInitLength();
        integerList.add(10);
        Assertions.assertEquals((int) initLengthExpected, integerList.getInitLength());
    }

    @Test
    public void shouldThrowIllegalIndexExceptionAddTest() {
        listFill(integerList, false);
        Assertions.assertThrows(IllegalIndexException.class, () -> integerList.add(integerList.size() + 1, 10 ));
    }

    @Test
    public void shouldSetItemTest() {
        listFill(integerList, false);
        integerList.set(1, 10);
        Assertions.assertEquals(10, integerList.get(1));
    }

    @Test
    public void shouldThrowIllegalInputExceptionRemoveTest() {
        listFill(integerList, false);
        Assertions.assertThrows(IllegalInputException.class, () -> integerList.remove(10.0));
    }

    @Test
    public void shouldRemoveItemTest() {
        listFill(integerList, true);
        integerList.remove(1.0);
        Assertions.assertEquals(2, integerList.get(1));
        Assertions.assertEquals(3, integerList.get(2));
    }

    @Test
    public void shouldReturnIndexOfTest() {
        listFill(integerList, true);
        Assertions.assertEquals(2, integerList.indexOf(2));
    }

    @Test
    public void equalsTest() {
        listFill(integerList, true);
        IntegerListImpl integerList1 = new IntegerListImpl(4);
        listFill(integerList1, true);
        Assertions.assertTrue(integerList.equals(integerList1));
    }

    @Test
    public void shouldReturnTrueContains() {
        listFill(integerList, true);
        Assertions.assertTrue(integerList.contains(2));
    }
}
