package spo.workforcemanager.model;

import java.util.List;

/**
 * This model class is used to store the input json from the REST Client
 * The Input contains array of structure sizes (in no. of rooms) as well as work capacities of Junior and Senior Cleaners
 * @author Karthikeyan Varadarajan
 */
@SuppressWarnings("unused")
public class Contract {

    /**
     * List of no of rooms in each structure
     */
    private List<Integer> rooms;

    /**
     * No of available senior workers
     */
    private int senior;

    /**
     * No of available junior workers
     */
    private int junior;

    /**
     * Default constructor
     */
    public Contract() {
    }

    /**
     * Contructor to initialize the member variables
     * @param rooms List of no of rooms in each structure
     * @param senior No of available senior workers
     * @param junior No of available junior workers
     */
    public Contract(List<Integer> rooms, int senior, int junior) {
        this.rooms = rooms;
        this.senior = senior;
        this.junior = junior;
    }

    /**
     * Getter for List of no of rooms
     * @return List of no of rooms in each structure
     */
    public List<Integer> getRooms() {
        return rooms;
    }

    /**
     * Setter for List of no of rooms
     * @param rooms List of no of rooms in each structure
     */
    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }

    /**
     * Getter for no of available seniors
     * @return No of available senior workers
     */
    public int getSenior() {
        return senior;
    }

    /**
     * Setter for no of available seniors
     * @param cleaners No of available senior workers
     */
    public void setSenior(int cleaners) {
        this.senior = cleaners;
    }

    /**
     * Getter for no of available juniors
     * @return No of available junior workers
     */
    public int getJunior() {
        return junior;
    }

    /**
     * Setter for no of available juniors
     * @param junior No of available junior workers
     */
    public void setJunior(int junior) {
        this.junior = junior;
    }
}
