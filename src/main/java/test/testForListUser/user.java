package test.testForListUser ; 
public class user {
	
	private String name ; 
	private String lastMsg ; 
	private String hourlast ;
	
	public user(String name, String lastMsg, String hourlast) {
		this.name = name ; 
		this.lastMsg = lastMsg ; 
		this.hourlast = hourlast ; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastMsg() {
		return lastMsg;
	}

	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	public String getHourlast() {
		return hourlast;
	}

	public void setHourlast(String hourlast) {
		this.hourlast = hourlast;
	}

	@Override
	public String toString() {
	    return this.name ; 
	}
	
}
