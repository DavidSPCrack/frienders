package es.examen.friender.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import es.examen.friender.R;
import es.examen.friender.model.Person;
import es.examen.friender.utils.Constants;


public class AddPersonActivity extends Activity {

    private static final String TAG = AddPersonActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }


    public void saveChorba(View view){
        EditText name = (EditText) findViewById(R.id.name);
        EditText address = (EditText)  findViewById(R.id.address);
        EditText telephone = (EditText)  findViewById(R.id.telephone);
        RatingBar rating = (RatingBar)  findViewById(R.id.ratingBar);

        try {
            Person person = new
                    Person(name.getText().toString(),
                    address.getText().toString(),
                    telephone.getText().toString(),
                    rating.getProgress());
            Intent intent = new Intent();
            intent.putExtra(Constants.KEY_PERSON,person);
            setResult(RESULT_OK,intent);

        }catch(Exception e){
            Log.e(TAG,"Error getting fields",e);
            setResult(-1);
        }

        finish();

    }
}
