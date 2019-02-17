package spo.workforcemanager.model;

/**
 * This model class is used to store the output json for the REST Client
 * The output contains the optimal numbers of Juniors and Seniors for every structure.
 * @author Karthikeyan Varadarajan
 */
@SuppressWarnings("unused")
public class Workforce {

    /**
     * No of seniors for the given structure
     */
    private int senior;

    /**
     * No of juniors for the given structure
     */
    private int junior;

    /**
     * Default constructor
     */
    public Workforce() {
    }

    /**
     * Parameteried constructor
     * @param senior No of seniors for the given structure
     * @param junior No of juniors for the given structure
     */
    public Workforce(int senior, int junior) {
        this.senior = senior;
        this.junior = junior;
    }

    /**
     * Getter for seniors
     * @return No of seniors for the given structure
     */
    public int getSenior() {
        return senior;
    }

    /**
     * Setter for seniors
     * @param senior No of seniors for the given structure
     */
    public void setSenior(int senior) {
        this.senior = senior;
    }

    /**
     * Getter for juniors
     * @return No of juniors for the given structure
     */
    public int getJunior() {
        return junior;
    }

    /**
     * Setter for juniors
     * @param junior No of juniors for the given structure
     */
    public void setJunior(int junior) {
        this.junior = junior;
    }
}