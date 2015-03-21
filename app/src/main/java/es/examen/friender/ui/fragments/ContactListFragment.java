package es.examen.friender.ui.fragments;

/**
 * Created by carlosfernandez on 04/03/15.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import es.examen.friender.R;
import es.examen.friender.model.Person;
import es.examen.friender.ui.activities.AddPersonActivity;
import es.examen.friender.ui.activities.DetailActivity;
import es.examen.friender.utils.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactListFragment extends ListFragment {

    private ArrayAdapter<Person> aPersonas;
    private ArrayList<Person> mPersonas;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ContactListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        int screenOrientation = getResources().getConfiguration().orientation;

        int layoutId = R.layout.fragment_list;
        if(screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutId = R.layout.fragment_list_landscape;
        }
        View rootView = inflater.inflate(layoutId, container, false);


        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), getResources().getString(R.string.refreshing), Toast.LENGTH_SHORT).show();
                loadList();
            }
        });

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        loadList();

        addSaveButton(rootView);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * Load the listView
     */
    private void loadList() {

        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }

        mPersonas = new ArrayList<>();
        mPersonas.add(new Person("Tiffany", "Bel-air 54", "34349523", 4));
        mPersonas.add(new Person("Penelope", "Bel-air 4", "43234234", 1));
        mPersonas.add(new Person("Maria", "Bel-air 52", "43242234", 5));
        mPersonas.add(new Person("Julia", "Bel-air 10", "43424343", 4));


        aPersonas = new ArrayAdapter<Person>(getActivity(), android.R.layout.simple_list_item_1,
                mPersonas);
        setListAdapter(aPersonas);

        progressBar.setVisibility(View.INVISIBLE);
    }


    public void addSaveButton(View rootView) {
        ImageButton saveButon = (ImageButton) rootView.findViewById(R.id.add_button);
        saveButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPersonActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE_ADD_PERSON);
            }
        });


    }

    @Override
    public void onActivityCreated(Bundle icicle) {
        super.onActivityCreated(icicle);
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = getActivity().getResources().getString(R.string.delete);
        String cancel = getActivity().getResources().getString(R.string.cancel);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
        menu.setHeaderTitle(mPersonas.get(position).toString());
        menu.add(0, v.getId(), 0, delete);
        menu.add(0, v.getId(), 0, cancel);
    }

    /**
     * Delete ContextMenu
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo element = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String delete = getActivity().getResources().getString(R.string.delete);
        String cancel = getActivity().getResources().getString(R.string.cancel);
        if (item.getTitle().equals(delete)) {
            // Delete a Contact
            mPersonas.remove(element.position);
            aPersonas.notifyDataSetChanged();


        } else if (item.getTitle().equals(cancel)) {
            // Do nothing
        }
        return super.onContextItemSelected(item);

    }

    /**
     * Long click Listener for deleting
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.KEY_PERSON, mPersonas.get(position));
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == Constants.REQUEST_CODE_ADD_PERSON) {
            if (resultCode == getActivity().RESULT_OK && data != null) {
                Person newPerson = (Person) data.getExtras().get(Constants.KEY_PERSON);
                aPersonas.add(newPerson);
                Resources res = getResources();

                Toast.makeText(getActivity(), String.format(res.getString(R.string.contact_added_message), newPerson.getName()), Toast.LENGTH_SHORT).show();

            }
        }
    }

}


