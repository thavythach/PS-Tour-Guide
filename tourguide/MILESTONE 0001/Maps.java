// This class is just meant to be an example of
// ways that we can implement APIs that would be useful
class Maps
{

	public static void main(String[] args){
		  // add a new map marker and set it to the latLng position
		  Marter marker = mMap.addMarker(
		      new MarkerOptions()
		        .position(new Latlng(51, 27))); // NOTES: ONBOARD FOR THIS IDEA, BUT ZOOOOOOOOOOM IS NECESSARY FOR THE UPS MAP IN PARTICULAR
		  marker.remove();


		  // mutator methods for the marker class
		  marker.setAlpha(1.0f);
		  marker.setVisible(true);
		  marker.setPosition(new Latlng(51, 27));

		  // Toggling an initially invisible marker to visible
		  // is faster than making a new marker altogether.
		  // Therefore, we should initialize all of our mMarkers //  NOTES: ALL? that's excessive. We can load main ones and add filter loads for all or specific markers; meaning efficient/less cluster;essentials for the user.
		  // upon onCreate(), and set all of their .visibility
		  // properties to 'invisible'.
		  // When we want to reveal our markers, we can just
		  // toggle the .visibility properties to 'visible'


		  // to show a small amount of info on the map
		  // we can use snippets...
		  Marker marker = mMap.addMarker(
		      new MarkerOptions()
		        .position(new LatLng(51, 27))
		        .title("Thompson Hall") // NOTES: Onboard for snippets, but what is going on that is relative to your current position? mhmhmhmhmhm probably calculation of how they should get there or possibly a snippet map (multiple snippets) to get to thompson. (just an idea, but you can interpret how you want).
		        .snippet("The computer science dept. is located on the 4th floor"));

		  // to toggle the snippet on/off...
		  marker.showInfoWindow();
		  marker.hideInfoWindow();

		  // to add click listeners to our markers...
		  mMap.setOnMarkerClickListener(
		    new OnMarkerClickListener(){
		      @Override
		      public boolean onMarkerClick(Marker marker){
		        // do something with marker                       // NOTES: we can do like jumps: let's click on thompson marker -> options pop up -> history, study rooms, department locations, labs, hangouts, etc.
		        return false; // don't show the info window
		      }
		    });



		  // to show the user a separate view, possibly containing details about the marker location
		  mMarkers = new WeakHashMap<Marker, String>();
		  mMarkers.put(marker, hotelId);


		  mMap.setOnMarkerClickListener(
		    new OnMarkerClickListener(){
		      @Override
		      public boolean onMarkerClick(Marker marker){
		        String loationId = mMarkers.get(marker);
		        // show details of location based on the location Id
		        return false; // don't show the info-window
		      }
		    });


		    // In order to maintain compliance with the Google
		    // Maps terms of service, we must design our map
		    // so that the google logo in the bottom right of // NOTES: you're right and we also have to use someone's API key. 
		    // the screen is visible. 

		    // Using google places api getCurrentPlace() method
		    // we can determine the current location of the user. // NOTES: good to know

		    // developers.google.com/places/android/start

		    // You can add objects to the map to designate points,
		    // lines, areas, or collections of objects. The Google Maps // NOTES: we can use javascript/other languages with google maps api/android development using NDk for sure, but rather deep understanding is necessary
		    // JavaScript API calls these objects overlays. Overlays are
		    // tied to latitude/longitude coordinates, so they move when // NOTES: okay perfect API that'll be useful in doing a lot of data sharing on the frontend. good stuff
		    // you drag or zoom the map.

		    https://developers.google.com/maps/documentation/javascript/overlays
	}

	rating = 40/43
	public void comments(int rating){
		String[] { "Good stuff Alex", "comments are noted with // NOTES:", "overall thoughts: we're totally using google maps api so props to you for giving us some leg room to think about it. I almost forgot about it even though I've been working on a crap ton of multilocation at work.", "we'll be using lots of the code you've presented and I like it on a lot.", "Milestone 3 is preparation for a meeting between me you and jesse.", "Meeting date set to weekend possibly.", "I have to prepare a lot of stuff before/during/after my company retreat so to leave you with some thought on what to do next (not all of it, but a glimspe: Google Maps Activity is our base. That base extended into directions of our mission statement/goal to better overall experience at UPS. With that mind, could we implement our own map? or should we continue using google maps. Aside from that, how can we produce an app in a month and a half or make headway?) "cheers" }
	}
}

