package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link TaskOneBase}.
 */
@ExtendWith(MockitoExtension.class)
class TaskOneBaseTest {

    @InjectMocks
    private TaskOneBase testable;

    @Mock
    private FileHandler fileHandler;

    @Before
    void setUp() {
        this.testable = new TaskOneBase();
    }


    @Test
    public void testIsNumericWhereStringIsNull() {
        String strNum = null;
        Boolean actualResult = this.testable.isNumeric(strNum);
        assertFalse(actualResult);
    }

    @Test
    public void testIsNumericWhereStringIsNumeric() {
        String strNum = "9";
        Boolean actualResult = this.testable.isNumeric(strNum);
        assertTrue(actualResult);
    }

    @Test
    public void testIsNumericWhereStringIsNotNumeric() {
        String strNum = "oooooo";
        Boolean actualResult = this.testable.isNumeric(strNum);
        assertFalse(actualResult);
    }

    @Test
    public void testDayOff() {
        int param = 1;
        int paramMonth = 0;
        int paramDay = 1;
        LocalDateTime dayOff = LocalDateTime.now().plusMonths(paramMonth).plusDays(paramDay);
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dayOff, zoneId);
        LocalDate expectedResult = LocalDate.parse(zonedDateTime.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME ), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        LocalDate actualResult = LocalDate.parse(this.testable.dayOff(param),DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadAllUuidFromFiles() {
        List<String> listString = Stream.generate(UUID::randomUUID)
                .map(UUID::toString)
                .limit(1)
                .collect(Collectors.toList());
        Optional<List<String>> readedLines = Optional.of(listString);
        when(fileHandler.readAllFile(anyString())).thenReturn(readedLines);

        List<String> actual = this.testable.readAllUuidFromFiles();
        assertEquals(actual,listString);

        verify(fileHandler).readAllFile(anyString());
    }


}