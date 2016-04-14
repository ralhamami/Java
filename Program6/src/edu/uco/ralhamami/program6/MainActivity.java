package edu.uco.ralhamami.program6;

import edu.uco.ralhamami.program6.DepartmentDialogFragment.DepartmentListener;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements DepartmentListener{
	//Notification id to have stackable notifications
	private int notificationId;

	//To Store Web Address
	private String uri;
	
	//Notification Text Array
	private final CharSequence notifyText[] = {"Biology","Chemistry","Computer " +
			"Science","Engineering","Funeral Services","Mathematics & Statistics",
			"Nursing"};
	
	//Pending Intent for the notification actions (Opening Website)
	private Intent notificationIntent;
	private PendingIntent contentIntent;
	
	//Sound Information
	private Uri soundURI = Uri.parse("android.resource://edu.uco.ralhamami.program6/"
			+ R.raw.notify);
	
	//Vibration Pattern
	private long[] vibratePattern = { 0, 200, 200, 300 };
	
	RemoteViews contentView = new RemoteViews("edu.uco.ralhamami.program6",
		R.layout.notif_custom);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("CMS Directory");
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DepartmentDialogFragment d = new DepartmentDialogFragment();
				d.show(getFragmentManager(), "Department");
			}
		});
	}
	
	@Override
	//Series of conditional statements to determine notification information to send
	public void onDepartmentClick(int Index, DialogFragment dialog) {
		String department = getResources().getStringArray(R.array.departments)[Index];
		
		if(department.equals("Biology")){
			contentView.setTextViewText(R.id.text, notifyText[0]);
			notificationId = 1;
			setUri("http://biology.uco.edu/");
			sendNotification();
		}
		else if(department.equals("Chemistry")){
			contentView.setTextViewText(R.id.text, notifyText[1]);
			notificationId = 2;
			setUri("http://www.uco.edu/cms/chemistry/");
			sendNotification();
		}
		else if(department.equals("Computer Science")){
			contentView.setTextViewText(R.id.text, notifyText[2]);
			notificationId = 3;
			setUri("http://cs.uco.edu");
			sendNotification();
		}
		else if(department.equals("Engineering")){
			contentView.setTextViewText(R.id.text, notifyText[3]);
			notificationId = 4;
			setUri("http://www.uco.edu/cms/engineering/index.asp");
			sendNotification();
		}
		else if(department.equals("Funeral Services")){
			contentView.setTextViewText(R.id.text, notifyText[4]);
			notificationId = 5;
			setUri("http://www.uco.edu/cms/funeral/index.asp");
			sendNotification();
		}
		else if(department.equals("Mathematics and Statistics")){
			contentView.setTextViewText(R.id.text, notifyText[5]);
			notificationId = 6;
			setUri("http://www.math.uco.edu/");
			sendNotification();
		}
		else if(department.equals("Nursing")){
			contentView.setTextViewText(R.id.text, notifyText[6]);
			notificationId = 7;
			setUri("http://www.uco.edu/cms/nursing/");
			sendNotification();
		}
	}
	
	//Build Notification
	private void sendNotification(){
		Notification.Builder notificationBuilder = new Notification.Builder(
			getApplicationContext()).setContentIntent(contentIntent)
			.setSmallIcon(R.drawable.launcher)
			.setAutoCancel(true)
			.setSound(soundURI).setVibrate(vibratePattern)
			.setContent(contentView);
		NotificationManager notMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notMgr.notify(notificationId,notificationBuilder.build());
	}
	
	//Set web address to intent before notification
	public void setUri(String uri){
		notificationIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(uri));
		contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
				notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
	}
}
