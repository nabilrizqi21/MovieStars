package id.sch.smktelkom_mlg.privateassignment.xirpl621.projectindividu;


import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {


    public static final String PLACE = "place";
    public static final int REQUEST_CODE_ADD = 88;
    public static final int REQUEST_CODE_EDIT = 99;
    public static DatabaseHandler mDb;
    Context context;
    String mQuery;
    RecommendAdapter adapter;
    ArrayList<RecommendListItem> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewMovie;
    //private RecyclerView.Adapter adaptera;

    public RecommendFragment() {

    }

    public static void initDB(Context context) {
        int dbVersion = 1;
        String dbName = "movies.db";
        mDb = new DatabaseHandler(context, dbName, dbVersion) {

            @Override
            protected void dropTables(SQLiteDatabase db) {
                db.execSQL(PlaceTable.getSQLDrop());
            }

            @Override
            protected void createTables(SQLiteDatabase db) {
                db.execSQL(PlaceTable.getSQLCreate());
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        // Inflate the layout for this fragment
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayoutManager layoutManagera = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mList = new ArrayList<>();
        recyclerViewMovie = (RecyclerView) view.findViewById(R.id.recyclerViewMovie);
        adapter = new RecommendAdapter(mList, getActivity().getApplicationContext());
        recyclerViewMovie.setAdapter(adapter);
        recyclerViewMovie.setLayoutManager(layoutManagera);
        recyclerViewMovie.setHasFixedSize(true);


        // loadRecyclerViewData();
        initDB(getActivity());
        if (PlaceTable.isEmpty(mDb))
            fillDataToDB();

        refreshData(null);

        return view;
    }

    private void fillDataToDB() {
        Resources resources = getResources();
        String[] arTitle = resources.getStringArray(R.array.tittle);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        String[] arFoto = new String[a.length()];
        for (int i = 0; i < arFoto.length; i++) {
            int id = a.getResourceId(i, 0);
            arFoto[i] = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + resources.getResourcePackageName(id) + '/'
                    + resources.getResourceTypeName(id) + '/'
                    + resources.getResourceEntryName(id);
        }
        a.recycle();
        for (int i = 0; i < arTitle.length; i++) {
            PlaceTable.add(mDb, new RecommendListItem(arTitle[i], arFoto[i]));
        }


    }

    private void refreshData(String query) {
        mList.clear();

        if (query == null || query.isEmpty())
            PlaceTable.getAll(mDb);
        else
            PlaceTable.getPlaceLike(mDb, query);

        mList.addAll(PlaceTable.ITEMS);
        adapter.notifyDataSetChanged();
    }


}

