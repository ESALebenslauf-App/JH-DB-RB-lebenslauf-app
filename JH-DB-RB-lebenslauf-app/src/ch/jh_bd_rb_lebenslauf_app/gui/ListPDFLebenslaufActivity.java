package ch.jh_bd_rb_lebenslauf_app.gui;

import java.io.File;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.resource.FileConst;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author j.herzig
 * 
 */
public class ListPDFLebenslaufActivity extends ListActivity {
	private File[] files;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO èberabeiten
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_pdf_lebenslauf);

		File file = new File(FileConst.getPdfPath());
		files = file.listFiles();

		ArrayAdapter<File> mPdfAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, files);
		setListAdapter(mPdfAdapter);

		lv = getListView();

		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				toast(arg1, arg2, arg3);
				return false;
			}
		});
	}

	private void toast(View arg1, int arg2, long arg3) {
		Toast toast = Toast.makeText(this, arg1.toString() + "//" + "//" + arg2 + "//" + arg3, Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
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

}
