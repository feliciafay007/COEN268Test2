package test1.ui.android.felicia.preferenceactivity2;

import android.os.Bundle;
import android.preference.PreferenceFragment;
/**
 * Created by feliciafay on 6/4/15.
 */
public class MyPreferenceFragment2 extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference2);
    }
}
