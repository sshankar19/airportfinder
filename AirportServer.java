package main;

import AirportData.AirportDataProto.AirportList;
import AirportData.AirportDataProto.Airport;
import PlaceData.PlaceDataProto.Place;
import PlaceData.PlaceDataProto.PlaceList;

public class AirportServer {
	


	public void printList(AirportList list) {
		for (Airport a : list.getAirportList()) {
			System.out.println(a.getState() + " " + a.getName() + ":\t" + a.getLat() + "\t"
					+ a.getLon());
		}
	}
	
	//d = 60 cos-1( sin(lat1) sin(lat2) + cos(lat1) cos(lat2) cos(lon2-lon1))
	
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
	
	public String[] getFiveAirports(String city, String state, PlaceList list, AirportList list2) {
		String[] result = new String[4];
		int temp = 0;
		for (Place p : list.getPlaceList()) {
			if(p.getState().compareToIgnoreCase(state) == 0) {
				for(Airport a : list2.getAirportList()) {
					double shortest = distance(p.getLat(),p.getLon(), a.getLat(),a.getLon());
					for (int i = 0; i >= result.length; i++) {
						if(shortest > temp) {
							shortest = temp; 
						}
						
					}
					
				}
			}
		}
		return null;
	}
}
