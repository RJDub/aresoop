package colonists;

import enums.Design;

public class Engineer extends ProfessionalColonist{

	Design project;
	public Engineer(String input) {
		super(input);
		// TODO Auto-generated constructor stub
		project = Design.None;
	}

	public Design getProject(){
		return project;
	}
	
	public void updateProject(Design decision){
		project = decision;
	}
}
