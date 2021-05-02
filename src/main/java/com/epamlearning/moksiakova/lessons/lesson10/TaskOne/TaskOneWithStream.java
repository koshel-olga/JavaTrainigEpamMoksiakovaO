package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for demonstration the task1 with for.
 */
@Slf4j
public class TaskOneWithStream extends TaskOneBase {

    /**
     * Init method.
     */
    public void run() {
        super.run();
        List<UUID> uuidList = this.generateUUID();
        this.writeToFileCollection(uuidList);
        List<String> countUUIDArrays = this.readAllUuidFromFiles().stream()
                .filter( uuid -> this.sumAllNumberInUuid(uuid) > 100)
                .collect(Collectors.toList());
        log.info("Number of UUID where sum number more then 100 : {}", countUUIDArrays.size());
        String dayOff = this.dayOff(countUUIDArrays.size());
        log.info("day off is : {}", dayOff);
    }

    /**
     * Method generate UUID.
     * @return collection of UUID.
     */
    private List<UUID> generateUUID() {
        List<UUID> uuidList = Stream.generate(UUID::randomUUID)
                .limit(10000)
                .collect(Collectors.toList());
        log.info("Generate collection of UUID.");
        return uuidList;
    }

    /**
     * Method write collection of UUID to file.
     * @param uuids collection of UUID.
     */
    private void writeToFileCollection(Collection<UUID> uuids) {
        uuids.forEach(uuid -> this.fileHandler.fileWriteToEnd(pathToFile, uuid.toString()));
        log.info("Write to {} collection of UUID.", pathToFile);
    }

    /**
     * Method count sum of numbers in UUID.
     * @param uuid
     * @return sum of numbers.
     */
    private Integer sumAllNumberInUuid(String uuid) {
        Optional<Integer> sum = Arrays.stream(uuid.split(""))
                .filter(this::isNumeric)
                .map(Integer::valueOf)
                .reduce(Integer::sum);
        return sum.orElse(0);
    }
}
