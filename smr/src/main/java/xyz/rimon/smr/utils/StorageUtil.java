package xyz.rimon.smr.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xyz.rimon.ael.commons.utils.PermissionUtil;
import xyz.rimon.ael.domains.Event;

/**
 * Created by SAyEM on 4/12/17.
 */

public class StorageUtil {
    private static String uniqueID = UUID.randomUUID().toString();
    public static String FILE_NAME = "temp.events";

    private StorageUtil() {
    }

    public static void writeObjects(Context context, String fileName, List<Event> objectList) {
        if(!PermissionUtil.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            PermissionUtil.askForPermission((Activity)context, "android.permission.WRITE_EXTERNAL_STORAGE");
        }

        Log.i("EVENT_LIST_WRITE", objectList.size() + "");
        File rootPath = Environment.getExternalStorageDirectory();
        File objectDir = new File(rootPath.getAbsolutePath() + "/" + context.getPackageName() + "/ael");
        if(!objectDir.exists()) {
            objectDir.mkdirs();
        }

        File objectsFile = new File(objectDir, fileName);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(objectsFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(objectList);
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            try {
                if(oos != null) {
                    oos.close();
                }

                if(fos != null) {
                    fos.close();
                }
            } catch (IOException var16) {
                var16.printStackTrace();
            }

        }

    }

    public static List<Event> readObjects(Context context, String fileName) {
        if(!PermissionUtil.hasPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            PermissionUtil.askForPermission((Activity)context, "android.permission.READ_EXTERNAL_STORAGE");
        }

        List<Event> objectList = new ArrayList();
        File rootPath = Environment.getExternalStorageDirectory();
        File cmedDir = new File(rootPath.getAbsolutePath() + "/" + context.getPackageName() + "/ael");
        if(!cmedDir.exists()) {
            cmedDir.mkdirs();
        }

        File objectsFile = new File(cmedDir, fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(objectsFile);
            ois = new ObjectInputStream(fis);
            objectList = (List)ois.readObject();
        } catch (ClassNotFoundException | IOException var17) {
            Log.e("CAN'T READ OBJECTS ", var17.toString());
        } finally {
            try {
                if(ois != null) {
                    ois.close();
                }

                if(fis != null) {
                    fis.close();
                }
            } catch (IOException var16) {
                var16.printStackTrace();
            }

        }

        Log.i("EVENT_LIST_READ", ((List)objectList).size() + "");
        return (List)objectList;
    }

    public static void writeObject(Context context, String fileName, Event event) {
        List<Event> objectList = readObjects(context, fileName);
        if(objectList == null) {
            objectList = new ArrayList();
        }

        ((List)objectList).add(event);
        writeObjects(context, fileName, (List)objectList);
        Log.i("OFFLINE_EVENTS", String.valueOf(((List)objectList).size()));
    }
}