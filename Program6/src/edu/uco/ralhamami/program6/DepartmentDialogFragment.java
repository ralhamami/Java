package edu.uco.ralhamami.program6;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DepartmentDialogFragment extends DialogFragment {

	public interface DepartmentListener {
		public void onDepartmentClick(int Index, DialogFragment dialog);
	}

	DepartmentListener listener;
	ArrayList<Integer> Items;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {

			listener = (DepartmentListener) activity;
		} catch (ClassCastException e) {

			throw new ClassCastException(activity.toString()
					+ " must implement DepartmentListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Please Select a Department:").setItems(
				R.array.departments,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						listener.onDepartmentClick(which,
								DepartmentDialogFragment.this);
						
//builder.setSingleChoiceItems(items, checkedItem, listener)
					}
					
				});
		return builder.create();
	}
}
