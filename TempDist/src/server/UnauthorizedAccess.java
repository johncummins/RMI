package server;

public class UnauthorizedAccess extends Exception {

	private static final long serialVersionUID = 100000;

	public UnauthorizedAccess(String reason) {
		super(reason);
	}
}

