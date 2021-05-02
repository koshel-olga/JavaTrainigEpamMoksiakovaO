package com.epamlearning.moksiakova.lessons.lesson10.TaskTwo;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link TaskTwoWithStream}.
 */
@ExtendWith(MockitoExtension.class)
class TaskTwoWithStreamTest {

    /**
     * Mock for FileHandler.
     */
    @Mock
    private FileHandler fileHandler;

    @InjectMocks
    private TaskTwoWithStream testable;

    @BeforeEach
    public void setUp() {
        testable = Mockito.spy(new TaskTwoWithStream());
    }

    @Test
    void testRun() {
        String[] strings = new String[1];
        strings[0] =  "dHlwZT0n0JTQsNGC0YHQutCw0Y8nLCB3ZWlnaHQ9MTYwNSwgY29zdD01NTYz";
        List<String> listString = Arrays.stream(strings).collect(Collectors.toList());
        doReturn(listString).when(this.testable).readAllStringsFromFile();

        this.testable.run();

        verify(testable).createObjectFromString(anyString());
    }
}