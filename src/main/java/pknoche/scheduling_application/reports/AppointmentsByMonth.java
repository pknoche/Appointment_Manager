package pknoche.scheduling_application.reports;

/**
 * Object model for storing data needed for generating the AppointmentsByMonth report.
 */
public class AppointmentsByMonth {
    /**
     * Month of the appointments.
     */
    String month;
    /**
     * Number of new client appointments in the month.
     */
    int newClient;
    /**
     * Number of planning session appointments in the month.
     */
    int planningSession;
    /**
     * Number of status update appointments in the month.
     */
    int statusUpdate;
    /**
     * Number of debriefing appointments in the month.
     */
    int debriefing;

    public AppointmentsByMonth(String month, int newClient, int planningSession, int statusUpdate, int debriefing) {
        this.month = month;
        this.newClient = newClient;
        this.planningSession = planningSession;
        this.statusUpdate = statusUpdate;
        this.debriefing = debriefing;
    }

    /**
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @return number of new client appointments in month
     */
    public int getNewClient() {
        return newClient;
    }

    /**
     * @return number of planning session appointments in month
     */
    public int getPlanningSession() {
        return planningSession;
    }

    /**
     * @return number of status update appointments in month
     */
    public int getStatusUpdate() {
        return statusUpdate;
    }

    /**
     * @return number of debriefing appointments in month
     */
    public int getDebriefing() {
        return debriefing;
    }
}
