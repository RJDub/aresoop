package colonists;

public class Engineer extends ProfessionalColonist{

	Design project;
	public Engineer(int input) {
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
