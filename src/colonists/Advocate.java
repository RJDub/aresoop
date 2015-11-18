package colonists;

import enums.Advocacy;

public class Advocate extends ProfessionalColonist{
	public Advocate(String input, int xIn, int yIn) {
		super(input, xIn, yIn);
		// TODO Auto-generated constructor stub
	}

	Advocacy project;

//	public Advocate(String input) {
//		super(input);
//		// TODO Auto-generated constructor stub
//		project = Advocacy.None;
//	}
	
	public Advocacy getProject(){
		return project;
	}
	
	public void updateProject(Advocacy choice){
		project = choice;
	}

}
