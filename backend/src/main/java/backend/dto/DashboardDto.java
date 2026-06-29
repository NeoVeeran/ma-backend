package backend.dto;

public class DashboardDto {

    private long totalStudents;
    private long presentToday;
    private long absentToday;
    private double feesCollected;
    private double feesPending;

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(long presentToday) {
        this.presentToday = presentToday;
    }

    public long getAbsentToday() {
        return absentToday;
    }

    public void setAbsentToday(long absentToday) {
        this.absentToday = absentToday;
    }

    public double getFeesCollected() {
        return feesCollected;
    }

    public void setFeesCollected(double feesCollected) {
        this.feesCollected = feesCollected;
    }

    public double getFeesPending() {
        return feesPending;
    }

    public void setFeesPending(double feesPending) {
        this.feesPending = feesPending;
    }
}