package com.mahdizareei.fileexplorer;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.File;
import java.util.ArrayList;

public class MZFileExplorer extends RelativeLayout {

    private ListView listView;
    private Button button;
    private String base = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    private String previous_url = "";

    public MZFileExplorer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUpDefaultListOfFile(final Context context, String direction) {
        View view = inflate(context, R.layout.mz_file_explorer, this);
        initView(view);
        initMZFileExplorer(context, direction);
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.lstView);
        button = view.findViewById(R.id.btnBack);
    }

    private void initMZFileExplorer(Context context, String direction) {
        previous_url = direction;
        ArrayList<String> list = getFilesList(direction);
        notifyListView(context, listView, list);
        onItemListClicked(context, list);
        onBackClicked(context, list);
    }

    private void onItemListClicked(final Context context, final ArrayList<String> list) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notifyListView(context, listView, getFilesList(base + list.get(position)));
                //previous_url = list.get(position);
            }
        });
    }

    private void onBackClicked(final Context context, final ArrayList<String> list) {
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = getFilesList(previous_url);
                notifyListView(context, listView, list);
            }
        });
    }

    private void notifyListView(Context context, ListView listView, ArrayList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<String> getFilesList(String direction) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File file = new File(direction);
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    list.add(f.getName());
                }
            }
        } catch (NullPointerException e) {
            Log.e("MZFileExplorer", "NullPointerException");
        } catch (Exception e) {
            Log.e("MZFileExplorer", "NullPointerException");
        }
        return list;
    }
}

