package spo.workforcemanager.services;

import org.springframework.stereotype.Service;
import spo.workforcemanager.model.Contract;
import spo.workforcemanager.model.Workforce;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkforceService {

    /**
     * The maximum number of room a senior can clean in a structure
     */
    private static final int SENIOR_MAX_CAPACITY = 10;

    /**
     * The maximum number of room a junior can clean in a structure
     */
    private static final int JUNIOR_MAX_CAPACITY = 5;

    /**
     * The final workforces assigned for the given structures
     */
    private List<Workforce> workforces;

    /**
     * This is a service method containing business logic to find the workforces needed for the given structures
     *
     * @param contract Input json object that contains the below input values:
     *                 - array of rooms (int) for every structure
     *                 - cleaning capacity Junior Cleaner (int)
     *                 - cleaning capacity Senior Cleaner (int)
     * @param rooms The List of rooms to be cleaned for each structure
     * @return List of workforces assigned for the given structures
     */
    public List<Workforce> getWorkforce(Contract contract, List<Integer> rooms) {
        workforces = new ArrayList<>();
        rooms.forEach(roomsInEachStructure -> assignWorkers(contract, roomsInEachStructure));
        return workforces;
    }


    /**
     * This is an internal helper method that provides the logic to find optimal workforce for a given structure
     * from the available workers
     *
     * @param contract Input json object that contains the below input values:
     *                 - array of rooms (int) for every structure
     *                 - cleaning capacity Junior Cleaner (int)
     *                 - cleaning capacity Senior Cleaner (int)
     * @param rooms No of rooms in each structure
     */
    private void assignWorkers(Contract contract, Integer rooms) {
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
