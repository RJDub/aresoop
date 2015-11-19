package colonists;

import enums.Research;

public class Scientist extends ProfessionalColonist{

	public Scientist(String input, int xIn, int yIn) {
		super(input, xIn, yIn);
		// TODO Auto-generated constructor stub
	}

	Research project;
	
//	public Scientist(String input) {
//		super(input);
//		// TODO Auto-generated constructor stub
//		project = Research.None;
//	}
	
	public Research getProject(){
		return project;
	}
	
	public void updateProject(Research decision){
		project = decision;
	}

}
