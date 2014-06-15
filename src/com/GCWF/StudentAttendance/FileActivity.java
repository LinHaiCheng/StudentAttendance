package com.GCWF.StudentAttendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.GCWF.Model.Student;
import com.GCWF.Utils.ExcelUtil;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 程海林 on 2014/6/7 | 2014/6/8.
 */
public class FileActivity extends Activity {
    private static final String ROOT_PATH = "/"; //the root of file
    private List<String> fileNames; //for save file's name
    private List<String> paths; //for save file's path
    private ListView list;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);

        list = (ListView) this.findViewById(R.id.list);
        showFileDir(ROOT_PATH);
    }
    private void showFileDir(String path) {
        fileNames = new ArrayList<String>();
        paths = new ArrayList<String>();
        File file = new File(path);
        File [] files = file.listFiles();
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        //the file is not the root
        if (!ROOT_PATH.equals(path)) {
            fileNames.add("@1");
            paths.add(ROOT_PATH);
            Map<String, Object> listItem1 = new HashMap<String, Object>();
            listItem1.put("icon", R.drawable.folder);
            listItem1.put("text", "根目录");
            listItems.add(listItem1);
            fileNames.add("@2");
            paths.add(file.getParent());
            Map<String, Object> listItem2 = new HashMap<String, Object>();
            listItem2.put("icon", R.drawable.folder);
            listItem2.put("text", "上级根目录");
            listItems.add(listItem2);
        }
        for (File f : files) {
            fileNames.add(f.getName());
            paths.add(f.getPath());
            Map<String, Object> listItem = new HashMap<String, Object>();
            if (f.isDirectory()) {
                listItem.put("icon", R.drawable.folder);
            } else {
                listItem.put("icon", R.drawable.file);
            }

            listItem.put("text", f.getName());
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems, R.layout.file_listview_item,
                new String[] {"icon", "text"},
                new int[] {R.id.icon, R.id.text}
        );
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = paths.get(position);
                final File file = new File(path);
                if (file.exists() && file.canRead()) {
                    if (file.isDirectory()) {
                        showFileDir(path);
                    } else {
                        String prefix=file.getName().substring(file.getName().lastIndexOf(".")+1);
                        if (prefix != null && !"".equals(prefix)) {
                            if ("xls".equals(prefix) || "xlsx".equals(prefix)) {
                                new AlertDialog.Builder(FileActivity.this)
                                        .setMessage("是否导入文件")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ExcelUtil excelUtil = new ExcelUtil();
                                                if (excelUtil.exportStudent(file)) {
                                                    //导入成功，转到显示班级的页面
                                                    new AlertDialog.Builder(FileActivity.this)
                                                            .setMessage("导入成功！")
                                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Intent classIntent = new Intent();
                                                                    Bundle classBundle = new Bundle();
                                                                    classBundle.putString("op", "classes");
                                                                    classIntent.putExtras(classBundle);
                                                                    classIntent.setClass(FileActivity.this, ClassesActivity.class);
                                                                    startActivity(classIntent);
                                                                    finish();
                                                                }
                                                            }).show();
                                                } else {
                                                    new AlertDialog.Builder(FileActivity.this)
                                                            .setMessage("导入失败!表格不规范或班级已存在！")
                                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Intent intent = new Intent(FileActivity.this, ClassesActivity.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }
                                                            }).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .show();
                            } else {
                                Toast.makeText(FileActivity.this, "请选择Excel文件", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(FileActivity.this, "请选择Excel文件", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}