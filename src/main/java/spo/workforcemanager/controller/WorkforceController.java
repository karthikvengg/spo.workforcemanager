package spo.workforcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spo.workforcemanager.model.Contract;
import spo.workforcemanager.model.Workforce;
import spo.workforcemanager.service.WorkforceService;

import java.util.List;

/**
 * This Class implements the REST resources for input and output of Workforce manager
 *
 * @author Karthikeyan Varadarajan
 */

@SuppressWarnings("unused")
@RestController
public class WorkforceController {

    /**
     * Service class instance
     */
    @Autowired
    private WorkforceService workforceService;

    /**
     * This method creates the optimal workforce needed for each structure of their cleaning partner
     *
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
        return workforceService.getWorkforce(contract,rooms);
    }
}