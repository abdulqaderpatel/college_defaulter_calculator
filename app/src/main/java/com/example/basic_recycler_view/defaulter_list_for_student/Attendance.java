package com.example.basic_recycler_view.defaulter_list_for_student;

public class Attendance {
    double present,absent;

    public Attendance()
    {

    }

    public Attendance(double present, double absent) {
        this.present = present;
        this.absent = absent;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }

    public double getAbsent() {
        return absent;
    }

    public void setAbsent(double absent) {
        this.absent = absent;
    }
}
