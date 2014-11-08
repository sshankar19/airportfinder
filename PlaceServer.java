package main;

import java.io.FileInputStream;

import AirportData.AirportDataProto.Airport;
import AirportData.AirportDataProto.AirportList;
import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

public class PlaceServer {
	
	static PlaceList placeList;
	
	public static void main(String[] args) throws Exception {
	/*	int port = 1099;

		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}*/

		placeList = PlaceList.parseFrom(new FileInputStream(
				Data.BIN_PLACES));

		printList(placeList);

		Place p = getLocation("newark","nJ");
		if(p != null){
			System.out.println(p.getState() + " " + p.getName() + ":\t" + p.getLat() + "\t"
					+ p.getLon());
		} else {
			System.out.println("null");
		}
		
		
//		AirportList airportList = AirportList.parseFrom(new FileInputStream(Data.BIN_AIRPORTS));
//		printList(airportList);
	}
	public static void printList(AirportList list) {

		for (Airport a : list.getAirportList()) {
			System.out.println(a.getState() + " " + a.getName() + " " + a.getLat()+ " " + a.getLon());
		}
	}

	public static void printList(PlaceList list) {

		for (Place p : list.getPlaceList()) {
			System.out.println(p.getState() + " " + p.getName() + ":\t" + p.getLat() + "\t"
					+ p.getLon());
		}
	}
	
/*	public static void printNearestAirports(String lat, String lon){
//		d = 60 cos-1( sin(lat1) sin(lat2) + cos(lat1) cos(lat2) cos(lon2-lon1))
		int distance = 0;
		
	}*/
	

	
	public static Place getLocation(String place, String state){

		for(Place p: placeList.getPlaceList()){
			if(p.getState().compareToIgnoreCase(state) == 0){
				if(p.getName().regionMatches(true, 0, place, 0, place.length())){
					return p;
				}
			}
		}
		return null;
	}
}
