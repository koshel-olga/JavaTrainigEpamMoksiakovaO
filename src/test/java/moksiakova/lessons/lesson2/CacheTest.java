package moksiakova.lessons.lesson2;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CacheTest {
    Cache<String> cache;
    Integer capacity;
    CacheElement[] cacheElements;

    @BeforeAll
    public void setUp() {
        this.capacity = 3;
        this.cache = new Cache(capacity);
        this.cache.add("1",1);
        this.cache.add("2",2);
        this.cache.add("3",3);
    }

    @Test
    public void createCacheAssertThrows() {
        Integer capacity = -1;
        String expectedExceptionMessage = "Недопустимый аргумент capacity "+ capacity;
        Throwable expectedException = Assert.assertThrows(IllegalCacheArgumentException.class,
                () -> new Cache(capacity));
        Assert.assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void isPresentElementTrue() {
        boolean expectedResult = true;
        String element = "1";

        boolean actualResult = this.cache.isPresent(element);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentElementFalse() {
        boolean expectedResult = false;
        String element = "5";

        boolean actualResult = this.cache.isPresent(element);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentIndexTrue() {
        boolean expectedResult = true;
        Integer index = 1;

        boolean actualResult = this.cache.isPresent(index);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentIndexFalse() {
        boolean expectedResult = false;
        Integer index = 5;

        boolean actualResult = this.cache.isPresent(index);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addMethod() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("2",2);
        expectedCache.add("3",3);
        expectedCache.add("4",4);

        cache.add("4",4);

        for( int i=0; i<capacity; i++) {
            Assert.assertEquals(expectedCache.get(i), cache.get(i));
        }

    }

}