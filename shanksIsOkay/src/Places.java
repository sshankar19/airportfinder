import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;

import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

// this is the class with remote methods

public class Places extends UnicastRemoteObject implements PlaceInterface {

	private int a;
	static PlaceList placeList;

	public Places() throws Exception {
		System.out.println("xxx");
		placeList = PlaceList.parseFrom(new FileInputStream(
				"src/data/places-proto.bin"));

		System.out.println("New instance of Sample created");
		a = 1;
	}

	public Place getPlace(String city, String state) {
		for (Place p : placeList.getPlaceList()) {
			if (p.getState().compareToIgnoreCase(state) == 0) {
				if (p.getName().regionMatches(true, 0, city, 0, city.length())) {
					return p;
				}
			}
		}
		return null;
	}
}
