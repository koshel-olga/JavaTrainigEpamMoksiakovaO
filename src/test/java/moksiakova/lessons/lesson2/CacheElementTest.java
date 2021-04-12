package moksiakova.lessons.lesson2;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CacheElementTest {

    @Test
    public void createCacheElementAssertThrow() {
        Integer index = -1;
        String expectedExceptionMessage = "Index "+index+" is illegal.";
        Throwable expectedException = Assert.assertThrows(IllegalCacheArgumentException.class,
                () -> new CacheElement(1,index));
        Assert.assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void getElementTest() {
        Integer expectedResult = 1;
        CacheElement<Integer> cacheElement = new CacheElement(expectedResult,5);

        Integer actualResult = cacheElement.getElement();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getIndexTest() {
        Integer expectedResult = 1;
        CacheElement<Integer> cacheElement = new CacheElement(5,expectedResult);

        Integer actualResult = cacheElement.getIndex();

        Assert.assertEquals(expectedResult, actualResult);
    }

}