package uoc.edu.dsmantenimiento.model;

public class JwtResponse {

	private String token;
	
	private String type;
	
	private Long id;
	
	private String email;

	public JwtResponse(String token, Long id, String email) {
		super();
		this.token = token;
		this.id = id;
		this.email = email;
	}
	
}
