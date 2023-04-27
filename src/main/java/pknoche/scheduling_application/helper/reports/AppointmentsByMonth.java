package pknoche.scheduling_application.helper.reports;

public class AppointmentsByMonth {
    String month;
    int newClient;
    int planningSession;
    int statusUpdate;
    int debriefing;

    public AppointmentsByMonth(String month, int newClient, int planningSession, int statusUpdate, int debriefing) {
        this.month = month;
        this.newClient = newClient;
        this.planningSession = planningSession;
        this.statusUpdate = statusUpdate;
        this.debriefing = debriefing;
    }

    public String getMonth() {
        return month;
    }

    public int getNewClient() {
        return newClient;
    }

    public int getPlanningSession() {
        return planningSession;
    }

    public int getStatusUpdate() {
        return statusUpdate;
    }

    public int getDebriefing() {
        return debriefing;
    }
}
