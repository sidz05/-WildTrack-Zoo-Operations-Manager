package application;

public class DisplaySalaryData {
	private String name;

	private int DisplaySalaryDataId;

	private String slides;



	public DisplaySalaryData( int DisplaySalaryDataId, String name, String slides) {

	this.DisplaySalaryDataId = DisplaySalaryDataId;
		
	this.name = name;

	this.slides = slides;



	}

	public String getName() {

	return name;

	}

	public void setName(String name) {

	this.name = name;

	}

	public int getDisplaySalaryDataId() {

	return DisplaySalaryDataId;

	}

	public void setDisplaySalaryDataId(int DisplaySalaryDataId) {

	this.DisplaySalaryDataId = DisplaySalaryDataId;

	}

	public String getSlides() {

	return slides;

	}

	public void setSlides(String slides) {

	this.slides = slides;

	}
}
