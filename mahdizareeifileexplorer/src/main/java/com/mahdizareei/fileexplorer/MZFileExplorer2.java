package com.mahdizareei.fileexplorer;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MZFileExplorer2 extends ListView {
    //just definition this in a xml and findViewBy it and call method setUpDefaultListOfFile
    //it automatically setUpListView

    public MZFileExplorer2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUpDefaultListOfFile(final Context context, String direction) {
        final MZFileExplorer2 mzFileExplorer = this;
        final ArrayList<String> list = getFilesList(direction);
        initListView(context, mzFileExplorer, list);
        mzFileExplorer.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initListView(context, mzFileExplorer, getFilesList(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+list.get(position)));
            }
        });
    }

    private void initListView(Context context, MZFileExplorer2 mzFileExplorer, ArrayList<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
        mzFileExplorer.setAdapter(adapter);
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
        }catch (NullPointerException e){
            Log.e("MZFileExplorer", "NullPointerException");
        }catch (Exception e){
            Log.e("MZFileExplorer", "NullPointerException");
        }
        return list;
    }
}

