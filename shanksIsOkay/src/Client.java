import java.rmi.Naming;
import java.util.ArrayList;

import AirportData.AirportDataProto.Airport;
import PlaceData.PlaceDataProto.Place;

public class Client {
	public static void main(String args[]) {
		try {
			/*if (args.length < 3) {
				System.err
						.println("usage: java SampleClient host port string... \n");
				System.exit(1);
			}*/

			//int port = Integer.parseInt(args[1]);
			String urlPlace = "";
			String urlAirport = "";
			
			if(args.length < 3) {
				urlPlace = "//" + "localhost" + ":" + "1099" + "/Places";
				urlAirport = "//" + "localhost" + ":" + "1099" + "/Airports";
			}else{
				
			}
			
			//url = "//" + args[0] + ":" + port + "/RemotePlace";
			System.out.println("looking up " + urlPlace);
			System.out.println("looking up " + urlAirport);
			
			PlaceInterface place = (PlaceInterface) Naming.lookup(urlPlace);
			AirportInterface airport = (AirportInterface) Naming.lookup(urlAirport);
			//System.out.println("what");
			// args[2] onward are the strings we want to reverse
			// for (int i=2; i < args.length; ++i)
			// call the remote method and print the return
			Place p = place.getPlace(args[0], args[1]);
			ArrayList<String> airportList = airport.getAirports(p.getLat(), p.getLon());
			System.out.println(p.getName() + ", " + p.getState() +": " + p.getLat() + ", " + p.getLon());
			for(String s : airportList) {
				System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println("SampleClient exception: " + e);
		}
	}
}
