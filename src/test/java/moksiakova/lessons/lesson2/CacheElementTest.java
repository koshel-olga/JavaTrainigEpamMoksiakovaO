package moksiakova.lessons.lesson2;

import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CacheElementTest {

    @Test
    public void testCreateCacheElementWithConstructorExpectException() {
        int index = -1;
        String expectedExceptionMessage = String.format("Index %d is illegal.",index);
        Throwable expectedException = assertThrows(IllegalCacheArgumentException.class,
                () -> new CacheElement(1,index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void testGetElementMethod() {
        Integer expectedResult = 1;
        CacheElement<Integer> cacheElement = new CacheElement(expectedResult,5);

        Integer actualResult = cacheElement.getElement();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetIndexMethod() {
        Integer expectedResult = 1;
        CacheElement<Integer> cacheElement = new CacheElement(5,expectedResult);

        Integer actualResult = cacheElement.getIndex();

        assertEquals(expectedResult, actualResult);
    }

}