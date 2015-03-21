package es.examen.friender.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import es.examen.friender.R;
import es.examen.friender.ui.fragments.DetailFragment;


public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }




}
