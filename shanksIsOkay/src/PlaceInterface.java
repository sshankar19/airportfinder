import java.rmi.Remote;
import java.rmi.RemoteException;
import PlaceData.PlaceDataProto.Place;

public interface PlaceInterface extends Remote {
	public Place getPlace(String city, String state);
}
