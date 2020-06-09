package server;

public class NoMatchingAssessment extends Exception {

	private static final long serialVersionUID = 100000;

	public NoMatchingAssessment(String reason) {
		super(reason);
	}
}

