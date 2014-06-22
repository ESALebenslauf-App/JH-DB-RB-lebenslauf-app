package ch.jh_bd_rb_lebenslauf_app.gui;

import java.io.File;
import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import ch.jh_bd_rb_lebenslauf_app.resource.StringConst;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author j.herzig
 * 
 */
public class ListPDFLebenslaufActivity extends ListActivity {
	private File[] files;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_pdf_lebenslauf);

		File file = new File(FileConst.getPdfPath());
		files = file.listFiles();

		setListAdapter(files);
	}

	private void setListAdapter(File[] files) {
		ArrayList<String> namen = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			namen.add(files[i].getName());
		}
		ArrayAdapter<String> mPdfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namen);
		setListAdapter(mPdfAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListPDFLebenslaufActivity.this);

		alertDialogBuilder.setTitle(this.getTitle() + StringConst.SKILLSDATEN);
		alertDialogBuilder.setMessage(StringConst.DIALOGOPTIONWAELEN);

		alertDialogBuilder.setPositiveButton(StringConst.SENDEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				String path = null;
				for (int i = 0; i < files.length; i++) {
					if (i == position) {
						path = files[i].getPath();
					}
				}
				if (path != null) {
					Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
					sendIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + path));
					sendIntent.setType("*/*");
					startActivity(sendIntent);
				}
			}
		});

		alertDialogBuilder.setNegativeButton(StringConst.LOESCHEN, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int idDialog) {

				for (int i = 0; i < files.length; i++) {
					if (i == position) {
						File file = files[i];
						file.delete();
					}
				}

				final Intent intent = new Intent(ListPDFLebenslaufActivity.this, FinishActivity.class);

				startActivity(intent);
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

}
