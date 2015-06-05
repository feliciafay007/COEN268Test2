package test1.ui.android.felicia.sharepreferencedemo;

import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class HeadersActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headers);
    }

    @Override
    public void onBuildHeaders (List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

}
