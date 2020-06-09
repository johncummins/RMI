package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExamServer extends Remote {

	// Return an access token that allows access to the server for some time period
	public int login(int studentid, String password) throws 
		UnauthorizedAccess, RemoteException;

	// Return an Assessment object associated with a particular course code
	public Assessment getAssessment(int token, int studentid, String courseCode) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Submit a completed assessment
	public String submitAssessment(int token, int studentid, Assessment completed) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

}

