package com.epamlearning.moksiakova.lessons.lesson10.TaskOne;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Class for demonstration the task1 with for.
 */
@Slf4j
public class TaskOneWithFor extends TaskOneBase {

    /**
     * Init method.
     */
    public void run() {
        super.run();
        List<UUID> uuidList = this.generateUUID();
        this.writeToFileCollection(uuidList);
        List<String> uuidsFromFile = this.readAllUuidFromFiles();
        int countUUID = 0;
        for (String uuidFromFile : uuidsFromFile) {
            if (this.sumAllNumberInUuid(uuidFromFile) > 100) {
                countUUID++;
            }
        }
        log.info("Number of UUID where sum number more then 100 : {}", countUUID);
        String dayOff = this.dayOff(countUUID);
        log.info("day off is : {}",dayOff);
    }

    /**
     * Method generate UUID.
     * @return collection of UUID.
     */
    private ArrayList<UUID> generateUUID() {
        ArrayList<UUID> uuidList = new ArrayList<>();
        for(int i=0; i<10000; i++) {
            uuidList.add(UUID.randomUUID());
        }
        log.info("Generate collection of UUID.");
        return uuidList;
    }

    /**
     * Method write collection of UUID to file.
     * @param uuids collection of UUID.
     */
    private void writeToFileCollection(Collection<UUID> uuids) {
        for (UUID uuid: uuids) {
            this.fileHandler.fileWriteToEnd(pathToFile, uuid.toString());
        }
        log.info("Write to {} collection of UUID.", pathToFile);
    }

    /**
     * Method count sum of numbers in UUID.
     * @param uuid
     * @return sum of numbers.
     */
    private int sumAllNumberInUuid(String uuid) {
        int sum = 0;
        String[] charsUUID = uuid.split("");
        for (String charUUID : charsUUID) {
            if (this.isNumeric(charUUID)) {
                sum += Integer.parseInt(charUUID);
            }
        }
        return sum;
    }
}
