package uoc.edu.dsmantenimiento.model;

public class JwtResponse {

	private String token;
	
	private Long id;
	
	private String email;
	
	private String name;
	
	private String type;

	public JwtResponse(String token, Long id, String email, String name, String type) {
		super();
		this.token = token;
		this.id = id;
		this.email = email;
		this.name = name;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
