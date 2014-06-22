package ch.jh_bd_rb_lebenslauf_app.listener;

import ch.jh_bd_rb_lebenslauf_app.daten.SendItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @author jherzig
 * 
 */

public class SendListener implements OnClickListener {
	private Activity finishActivity;
	private SendItem sendItem;

	public SendListener(Activity finishActivity, SendItem sendItem) {
		this.finishActivity = finishActivity;
		this.sendItem = sendItem;
	}

	@Override
	public void onClick(View arg0) {
		Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
		sendIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + sendItem.getPath()));
		sendIntent.setType("*/*");
		finishActivity.startActivity(sendIntent);
	}

}
