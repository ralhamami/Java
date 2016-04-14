package rajiv.mancharamo.program9;

import rajiv.mancharamo.program9.R;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private GoogleMap map;
	
	private final LatLng NUCcoord = new LatLng(35.655302, -97.471480);
	private final LatLng Housecoord = new LatLng(35.596563, -97.627423);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.addMarker(new MarkerOptions().position(NUCcoord).title("NUC"));
		map.addMarker(new MarkerOptions().position(Housecoord).title("Rajiv's House"));

		Button Housebutton=(Button) findViewById(R.id.button_house);
		Housebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(Housecoord, 19);
				map.animateCamera(update);				
			}
		});
		
		Button NUCbutton=(Button) findViewById(R.id.button_nuc);
		NUCbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(NUCcoord, 18);
				map.animateCamera(update);				
			}
		});
		
	}

}
