import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import AirportData.AirportDataProto.Airport;

public interface AirportInterface extends Remote {
	public ArrayList<String> getAirports(Double lat, Double lon) throws RemoteException;
}
