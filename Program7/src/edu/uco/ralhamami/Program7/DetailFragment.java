package edu.uco.ralhamami.Program7;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DetailFragment extends Fragment implements OnClickListener{

	private static final String TAG = "DetailFragment";
	private TextView fName = null;
	private TextView lName = null;
	private TextView phoneNum = null;
	private TextView email = null;
	private TextView web = null;
	private int mCurrIdx = -1;
	private int mQuoteArrLen = 20;

	int getShownIndex() {
		return mCurrIdx;
	}

	void showIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		fName.setText(MainActivity.contacts[mCurrIdx].getfName());
		lName.setText(MainActivity.contacts[mCurrIdx].getlName());
		phoneNum.setText(MainActivity.contacts[mCurrIdx].getPhoneNum());
		email.setText(MainActivity.contacts[mCurrIdx].getEmail());
		web.setText(MainActivity.contacts[mCurrIdx].getWeb());
		web.setOnClickListener(this);
	}

	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

		return inflater.inflate(R.layout.detail_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		fName = (TextView) getActivity().findViewById(R.id.fName);
		lName = (TextView) getActivity().findViewById(R.id.lName);
		phoneNum = (TextView) getActivity().findViewById(R.id.phoneNum);
		email = (TextView) getActivity().findViewById(R.id.email);
		web = (TextView) getActivity().findViewById(R.id.web);
	}

	@Override
	public void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

	@Override
	public void onDetach() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
		super.onDestroyView();
	}

	@Override
	public void onClick(View arg0) {
		String uri = ((TextView)arg0).getText().toString();
		Intent web = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+uri));
		startActivity(web);
	}

}
