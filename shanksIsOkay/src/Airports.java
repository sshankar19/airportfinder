import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import AirportData.AirportDataProto.Airport;
import AirportData.AirportDataProto.AirportList;
import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

// this is the class with remote methods

public class Airports extends UnicastRemoteObject implements AirportInterface {

	private int a;
	static AirportList airportList;

	public Airports() throws Exception {
		System.out.println("xxx");
		airportList = AirportList.parseFrom(new FileInputStream(
				"src/data/airports-proto.bin"));

		System.out.println("New instance of Airport created");
		a = 1;
	}

	public ArrayList<String> getAirports(Double lat, Double lon) {

		ArrayList<Double> distances = new ArrayList<Double>();
		HashMap<Double, Airport> airports = new HashMap<Double, Airport>();
		double dist = 0;
		for (Airport a : airportList.getAirportList()) {
			dist = Math.abs(distance(lat, lon, a.getLat(), a.getLon()));
			distances.add(dist);
			airports.put(dist, a);
		}
		Collections.sort(distances);
		ArrayList<String> fiveGuys = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {

			Airport a = airports.get(distances.get(i));
			fiveGuys.add("code=" + a.getCode() + ", name=" + a.getName() + ", state=" + a.getState() + " distance: " + (int)Math.round(distances.get(i)));
			
		}

		return fiveGuys;
	}
	
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	public double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
