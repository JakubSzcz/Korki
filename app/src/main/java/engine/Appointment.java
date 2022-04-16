package engine;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Appointment {
    /////////////////////////////////////////////////////
    //                   variables                     //
    /////////////////////////////////////////////////////

    // student
    private Student student;

    // title
    private String title;

    // price
    private double price;

    // is paid
    private boolean isPaid;

    // start date of appointment
    private LocalDateTime dateStart;

    // end date of appointment
    private LocalDateTime dateEnd;

    // periodic id
    private int periodicId;

    /////////////////////////////////////////////////////
    //                   functions                     //
    /////////////////////////////////////////////////////

    // constructor
    public Appointment(Student student, String title, double price, LocalDateTime dateStart,
                       LocalDateTime dateEnd, int periodicId) {
        this.student = student;
        this.title = title;
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.periodicId = periodicId;
        this.isPaid = false;
    }

    // check if is collision with another appointment
    public boolean isCollision(Appointment appointment){
        if (appointment.getDateStart().isBefore(this.dateStart)){
            return !appointment.getDateEnd().isBefore(this.dateStart);
        }else{
            return !appointment.getDateStart().isAfter(this.dateEnd);
        }
    }

    // equals, mainly for testing purposes
    public boolean equals(Appointment appointment){
        if (this.student != appointment.getStudent()){
            return false;
        }else if (!this.title.equals(appointment.getTitle())){
            return false;
        }else if(!this.dateEnd.isEqual(appointment.getDateEnd())){
            return false;
        }else if (!this.dateStart.isEqual(appointment.getDateStart())){
            return false;
        }else{
            return true;
        }
    }

    // get duration
    public double getDuration(){
        return ChronoUnit.MINUTES.between(dateStart, dateEnd);
    }

    /////////////////////////////////////////////////////
    //                    getters                      //
    /////////////////////////////////////////////////////

    public Student getStudent() {
        return student;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public int getPeriodicId() {
        return periodicId;
    }
}
