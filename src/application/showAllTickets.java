package application;


public class showAllTickets {




private String name;

private int tno;

private String slides;

private String chlorination;

private String date;

private String startTime;

private int endTime;

public showAllTickets( int tno, String name, String slides, String chlorination, String date, String startTime, int endTime) {

this.tno = tno;
	
this.name = name;

this.slides = slides;

this.chlorination = chlorination;

this.date = date;

this.startTime = startTime;

this.endTime = endTime;

}

public int getTno() {

return tno;

}

public void setTno(int tno) {

this.tno = tno;

}

public String getName() {

return name;

}

public void setName(String name) {

this.name = name;

}



public String getSlides() {

return slides;

}

public void setSlides(String slides) {

this.slides = slides;

}

public String getChlorination() {

return chlorination;

}

public void setChlorination(String chlorination) {

this.chlorination = chlorination;

}

public String getDate() {

return date;

}

public void setDate(String date) {

this.date = date;

}

public String getStartTime() {

return startTime;

}

public void setStartTime(String startTime) {

this.startTime = startTime;

}

public int getEndTime() {

return endTime;

}

public void setEndTime(int endTime) {

this.endTime = endTime;

}

}

