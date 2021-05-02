package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import com.epamlearning.moksiakova.lessons.lesson10.WorkWithFile.FileHandler;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

/**
 * Test class for class TaskOneWithFor.
 */
@ExtendWith(MockitoExtension.class)
class TaskOneWithForTest {

    /**
     * Mock for FileHandler.
     */
    @Mock
    private FileHandler fileHandler;

    @InjectMocks
    private TaskOneWithFor testable;

    @Before
    public void setUp() {
        testable = new TaskOneWithFor();
    }

    @Test
    void testRun() {
        doNothing().when(fileHandler).fileWriteToEnd(anyString(), anyString());
        List<String> listString = Stream.generate(UUID::randomUUID)
                .map(UUID::toString)
                .limit(1)
                .collect(Collectors.toList());
        Optional<List<String>> readedLines = Optional.of(listString);
        when(fileHandler.readAllFile(anyString())).thenReturn(readedLines);

        this.testable.run();

        verify(testable.fileHandler, times(10000)).fileWriteToEnd(anyString(), anyString());
        verify(testable.fileHandler).readAllFile(anyString());
    }
}