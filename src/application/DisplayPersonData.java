package application;


public class DisplayPersonData {

private String name;

private int DisplayPersonDataId;

private String slides;



public DisplayPersonData( int DisplayPersonDataId, String name, String slides) {

this.DisplayPersonDataId = DisplayPersonDataId;
	
this.name = name;

this.slides = slides;



}

public String getName() {

return name;

}

public void setName(String name) {

this.name = name;

}

public int getDisplayPersonDataId() {

return DisplayPersonDataId;

}

public void setDisplayPersonDataId(int DisplayPersonDataId) {

this.DisplayPersonDataId = DisplayPersonDataId;

}

public String getSlides() {

return slides;

}

public void setSlides(String slides) {

this.slides = slides;

}


}