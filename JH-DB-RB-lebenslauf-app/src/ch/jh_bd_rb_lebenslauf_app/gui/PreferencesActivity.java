package ch.jh_bd_rb_lebenslauf_app.gui;

import ch.jh_bd_rb_lebenslauf_app.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

/**
 * @author kvg
 */
//PreferencesActivity zeigt lediglich die Preferenzeneinstellungen
public class PreferencesActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new AppPreferencesFragment())
				.commit();
	}

	public static class AppPreferencesFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.app_preferences);

		}
	}

}