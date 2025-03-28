package application;


public class DisplayAnimalData {

private String name;

private int DisplayAnimalDataId;

private String slides;



public DisplayAnimalData( int DisplayAnimalDataId, String name, String slides) {

this.DisplayAnimalDataId = DisplayAnimalDataId;
	
this.name = name;

this.slides = slides;



}

public String getName() {

return name;

}

public void setName(String name) {

this.name = name;

}

public int getDisplayAnimalDataId() {

return DisplayAnimalDataId;

}

public void setDisplayAnimalDataId(int DisplayAnimalDataId) {

this.DisplayAnimalDataId = DisplayAnimalDataId;

}

public String getSlides() {

return slides;

}

public void setSlides(String slides) {

this.slides = slides;

}


}