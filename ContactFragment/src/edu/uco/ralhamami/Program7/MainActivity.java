package edu.uco.ralhamami.Program7;

import java.util.Arrays;

import android.R.color;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import edu.uco.ralhamami.Program7.NameFragment.ListSelectionListener;

public class MainActivity extends Activity implements
		ListSelectionListener {

	public static String[] mNameArray = new String[20];
	public static String[] mDetailsArray;

	public static Contact[] contacts = new Contact[20];
	private final DetailFragment mDetailsFragment = new DetailFragment();
	private FragmentManager mFragmentManager;
	private FrameLayout mNameFrameLayout, mDetailsFrameLayout;

	private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initContacts();
		for (int i=0; i<20; i++){
			mNameArray[i] = contacts[i].getlName();
		}
		Arrays.sort(mNameArray);
		Arrays.sort(contacts,Contact.ContactLastNameComparator);
		mDetailsArray = getResources().getStringArray(R.array.Details);

		setContentView(R.layout.main);
		setTitle("Contact Viewer");
		
		mNameFrameLayout = (FrameLayout) findViewById(R.id.names_fragment_container);
		mDetailsFrameLayout = (FrameLayout) findViewById(R.id.details_fragment_container);

		mFragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.names_fragment_container,
				new NameFragment());
		fragmentTransaction.commit();

		mFragmentManager
				.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {
						setLayout();
					}
				});
	}

	private void setLayout() {
		if (!mDetailsFragment.isAdded()) {
			mNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
					MATCH_PARENT, MATCH_PARENT)); // width, height
			mDetailsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT));
		} else {
			mNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 1f)); // width, height, weight
			mDetailsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 2f));
		}
	}

	@Override
	public void onListSelection(int index) {
		if (!mDetailsFragment.isAdded()) {
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.details_fragment_container,
					mDetailsFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			mFragmentManager.executePendingTransactions();
		}
		if (mDetailsFragment.getShownIndex() != index) {
			mDetailsFragment.showIndex(index);
		}
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}
	
	public void initContacts(){
		contacts[0]=new Contact();
		contacts[0].setfName("Rayan");
		contacts[0].setlName("Al-Hammami");
		contacts[0].setEmail("ralhamami@gmail.com");
		contacts[0].setPhoneNum("4053141663");
		contacts[0].setWeb("www.yahoo.com");
		
		contacts[1]=new Contact();
		contacts[1].setfName("Rajiv");
		contacts[1].setlName("Mancharamo");
		contacts[1].setEmail("rmancharamoo@uco.edu");
		contacts[1].setPhoneNum("4053151632");
		contacts[1].setWeb("www.google.com");
		
		contacts[2]=new Contact();
		contacts[2].setfName("Mosa");
		contacts[2].setlName("Al-Obaid");
		contacts[2].setEmail("malobaid@gmail.com");
		contacts[2].setPhoneNum("4052141669");
		contacts[2].setWeb("www.facebook.com");
		
		contacts[3]=new Contact();
		contacts[3].setfName("Bill");
		contacts[3].setlName("Clinton");
		contacts[3].setEmail("bclinton@gmail.com");
		contacts[3].setPhoneNum("2123141889");
		contacts[3].setWeb("www.whitehouse.gov");
		
		contacts[4]=new Contact();
		contacts[4].setfName("Barack");
		contacts[4].setlName("Obama");
		contacts[4].setEmail("boabama@gmail.com");
		contacts[4].setPhoneNum("2124508547");
		contacts[4].setWeb("www.cia.gov");
		
		contacts[5]=new Contact();
		contacts[5].setfName("Mark");
		contacts[5].setlName("Zuckerburg");
		contacts[5].setEmail("mzuckerburg@gmail.com");
		contacts[5].setPhoneNum("3415852991");
		contacts[5].setWeb("www.myspace.com");
		
		contacts[6]=new Contact();
		contacts[6].setfName("Bill");
		contacts[6].setlName("Gates");
		contacts[6].setEmail("bgates@gmail.com");
		contacts[6].setPhoneNum("8124567801");
		contacts[6].setWeb("www.microsoft.com");
		
		contacts[7]=new Contact();
		contacts[7].setfName("Clint");
		contacts[7].setlName("Eastwood");
		contacts[7].setEmail("ceastwood@gmail.com");
		contacts[7].setPhoneNum("4234568725");
		contacts[7].setWeb("www.netflix.com");
		
		contacts[8]=new Contact();
		contacts[8].setfName("Izzy");
		contacts[8].setlName("Mancho");
		contacts[8].setEmail("imancho@gmail.com");
		contacts[8].setPhoneNum("2458764623");
		contacts[8].setWeb("www.gmail.com");
		
		contacts[9]=new Contact();
		contacts[9].setfName("Pepper");
		contacts[9].setlName("Roni");
		contacts[9].setEmail("proni@gmail.com");
		contacts[9].setPhoneNum("4235467897");
		contacts[9].setWeb("www.pizzahut.com");
		
		contacts[10]=new Contact();
		contacts[10].setfName("Sana");
		contacts[10].setlName("Mumallah");
		contacts[10].setEmail("smuma@gmail.com");
		contacts[10].setPhoneNum("9184567235");
		contacts[10].setWeb("www.sephora.com");
		
		contacts[11]=new Contact();
		contacts[11].setfName("Nelson");
		contacts[11].setlName("Nelson");
		contacts[11].setEmail("nnelson@gmail.com");
		contacts[11].setPhoneNum("3245267894");
		contacts[11].setWeb("www.hotmail.com");
		
		contacts[12]=new Contact();
		contacts[12].setfName("Jack");
		contacts[12].setlName("Daniels");
		contacts[12].setEmail("jdaniels@gmail.com");
		contacts[12].setPhoneNum("2345678405");
		contacts[12].setWeb("www.jackdaniels.com");
		
		contacts[13]=new Contact();
		contacts[13].setfName("Harrison");
		contacts[13].setlName("Ford");
		contacts[13].setEmail("hford@gmail.com");
		contacts[13].setPhoneNum("5467890123");
		contacts[13].setWeb("www.ford.com");
		
		contacts[14]=new Contact();
		contacts[14].setfName("Ralph");
		contacts[14].setlName("Davies");
		contacts[14].setEmail("rdavies@gmail.com");
		contacts[14].setPhoneNum("9184563214");
		contacts[14].setWeb("www.youtube.com");
		
		contacts[15]=new Contact();
		contacts[15].setfName("Tim");
		contacts[15].setlName("Tom");
		contacts[15].setEmail("ttom@gmail.com");
		contacts[15].setPhoneNum("5815467625");
		contacts[15].setWeb("www.tomtom.com");
		
		contacts[16]=new Contact();
		contacts[16].setfName("Goo");
		contacts[16].setlName("Gle");
		contacts[16].setEmail("ggle@gmail.com");
		contacts[16].setPhoneNum("6545852588");
		contacts[16].setWeb("www.cnn.com");
		
		contacts[17]=new Contact();
		contacts[17].setfName("Al");
		contacts[17].setlName("Jazeera");
		contacts[17].setEmail("ajazeera@gmail.com");
		contacts[17].setPhoneNum("8457568654");
		contacts[17].setWeb("www.aljazeera.com");
		
		contacts[18]=new Contact();
		contacts[18].setfName("Ken");
		contacts[18].setlName("Jones");
		contacts[18].setEmail("kjones@gmail.com");
		contacts[18].setPhoneNum("4053214785");
		contacts[18].setWeb("www.kirby.com");
		
		contacts[19]=new Contact();
		contacts[19].setfName("Poke");
		contacts[19].setlName("Mon");
		contacts[19].setEmail("pmon@gmail.com");
		contacts[19].setPhoneNum("4058545152");
		contacts[19].setWeb("www.pokemon.com");
	}
}