package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

import com.epamlearning.moksiakova.lessons.lesson10.TaskOne.TaskOneBase;
import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link TaskTwoBase}.
 */
@ExtendWith(MockitoExtension.class)
class TaskTwoBaseTest {

    @InjectMocks
    private TaskTwoBase testable;

    @Mock
    private FileHandler fileHandler;

    @Before
    void setUp() {
        this.testable = new TaskTwoBase();
    }

    @Test
    void testReadAllStringsFromFile() {
        String[] strings = new String[1];
        strings[0] =  "type='Докторская', weight=1134, cost=9934";
        List<String> listString = Arrays.stream(strings).collect(Collectors.toList());
        Optional<List<String>> readedLines = Optional.of(listString);
        when(fileHandler.readAllFile(anyString())).thenReturn(readedLines);

        List<String> actual = this.testable.readAllStringsFromFile();
        assertEquals(actual,listString);

        verify(fileHandler).readAllFile(anyString());
    }

    @Test
    void testCreateObjectFromStringReturnObject() {
        String stringDecode = "type='Докторская', weight=1134, cost=9934";
        Ham expected = new Ham("Докторская", 1134, 9934);

        Ham actual = this.testable.createObjectFromString(stringDecode).get();

        assertTrue(actual.equals(expected));
    }

    @Test
    void testCreateObjectFromStringReturnEmpty() {
        String stringDecode = "";
        Optional<Ham> actual = this.testable.createObjectFromString(stringDecode);
        assertTrue(actual.isEmpty());
    }
}