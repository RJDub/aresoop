package colonists;

public class Advocate extends ProfessionalColonist{
	Advocacy project;

	public Advocate(int input) {
		super(input);
		// TODO Auto-generated constructor stub
		project = Advocacy.None;
	}
	
	public Advocacy getProject(){
		return project;
	}
	
	public void updateProject(Advocacy choice){
		project = choice;
	}

}
