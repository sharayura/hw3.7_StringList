package org.skypro.stringList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.exceptions.IllegalIndexException;
import org.skypro.exceptions.IllegalInputException;
import org.skypro.exceptions.NullInputException;

public class StringListImplTest {

    private StringListImpl stringList;

    @BeforeEach
    public void setUp() {
        stringList = new StringListImpl(4);
    }

    public void listFill(StringListImpl stringListTest, boolean full) {
        int size = full ? stringListTest.getInitLength() : stringListTest.getInitLength() / 2;
        for (int i = 0; i < size; i++) {
            stringListTest.add("Test" + i);
        }
    }

    @Test
    public void shouldThrowNullInputExceptionAddTest() {
        Assertions.assertThrows(NullInputException.class, () -> stringList.add(null));
    }

    @Test
    public void shouldDoubleLengthAddTest() {
        listFill(stringList, true);
        int initLengthExpected = 2 * stringList.getInitLength();
        stringList.add("Test");
        Assertions.assertEquals(initLengthExpected, stringList.getInitLength());
    }

    @Test
    public void shouldThrowIllegalIndexExceptionAddTest() {
        listFill(stringList, false);
        Assertions.assertThrows(IllegalIndexException.class, () -> stringList.add(stringList.size() + 1, "Test" ));
    }

    @Test
    public void shouldSetItemTest() {
        listFill(stringList, false);
        stringList.set(1, "Test");
        Assertions.assertEquals("Test", stringList.get(1));
    }

    @Test
    public void shouldThrowIllegalInputExceptionRemoveTest() {
        listFill(stringList, false);
        Assertions.assertThrows(IllegalInputException.class, () -> stringList.remove("Test" ));
    }

    @Test
    public void shouldRemoveItemTest() {
        listFill(stringList, true);
        stringList.remove("Test1");
        Assertions.assertEquals("Test2", stringList.get(1));
        Assertions.assertEquals("Test3", stringList.get(2));
    }

    @Test
    public void shouldReturnIndexOfTest() {
        listFill(stringList, true);
        Assertions.assertEquals(2, stringList.indexOf("Test2"));
    }

    @Test
    public void equalsTest() {
        listFill(stringList, true);
        StringListImpl stringList1 = new StringListImpl(4);
        listFill(stringList1, true);
        Assertions.assertTrue(stringList.equals(stringList1));
    }
}
