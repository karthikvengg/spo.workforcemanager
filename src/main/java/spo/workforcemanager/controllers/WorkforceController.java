package spo.workforcemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spo.workforcemanager.model.Contract;
import spo.workforcemanager.model.Workforce;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class implements the REST resources for input and output of Workforce manager
 * @author Karthikeyan Varadarajan
 */

@SuppressWarnings("unused")
@RestController
public class WorkforceController {

    private static final int SENIOR_MAX_CAPACITY = 10;
    private static final int JUNIOR_MAX_CAPACITY = 5;
    private List<Workforce> workforces;

    /**
     * This method creates the optimal workforce needed for each structure of their cleaning partner
     * @param contract Input json object that contains the below input values:
     *                 - array of rooms (int) for every structure
     *                 - cleaning capacity Junior Cleaner (int)
     *                 - cleaning capacity Senior Cleaner (int)
     * @return Returns the json object with the list of pairs which include the optimal number of
     * Juniors and Seniors for every structure
     */
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Workforce> createWorkforce(@RequestBody Contract contract) {
        List<Integer> rooms = contract.getRooms();
        workforces = new ArrayList<>();
        rooms.forEach(roomsInEachStructure -> assignWorkers(contract, roomsInEachStructure));
        return workforces;
    }

    /**
     * This is an internal helper method that provides the logic to find optimal workforce for a given structure
     * from the available workers
     * @param contract Input json object that contains the below input values:
     *                 - array of rooms (int) for every structure
     *                 - cleaning capacity Junior Cleaner (int)
     *                 - cleaning capacity Senior Cleaner (int)
     * @param rooms No of rooms in each structure
     */
    private void assignWorkers(@RequestBody Contract contract, Integer rooms) {
        int assignedSeniorWorkers = 0;
        int assignedJuniorWorkers = 0;
        int availableSeniorWorkers = contract.getSenior();
        int availableJuniorWorkers = contract.getJunior();

        //By default at least 1 senior worker is to be assigned
        if (rooms > 0 && availableSeniorWorkers>0) {
            rooms -= SENIOR_MAX_CAPACITY;
            assignedSeniorWorkers++;
            contract.setSenior(availableSeniorWorkers--);
        }

        for (int m = rooms; m > 0; ) {
            //Add seniors only if there are more than 12 rooms available for cleaning. Else 2 juniors are more efficient
            if (m > 12 && availableSeniorWorkers>0) {
                m -= SENIOR_MAX_CAPACITY;
                assignedSeniorWorkers++;
                contract.setSenior(availableSeniorWorkers--);
            }
            //Juniors can handle 1 or 2 extra rooms
            else if(availableJuniorWorkers>0 && m>3){
                m -= JUNIOR_MAX_CAPACITY;
                assignedJuniorWorkers++;
                contract.setJunior(availableJuniorWorkers--);
            }
            else {
                m=0;
            }
        }
        workforces.add(new Workforce(assignedSeniorWorkers, assignedJuniorWorkers));
    }
}