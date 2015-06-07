package test1.ui.android.felicia.fileio;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//path = Environment.getExternalStoragePublicDirectory(
//        Environment.DIRECTORY_MOVIES);
//        File file = new File(path, "/" + fname);

public class FileOperations {
    private File myDir ;
    public FileOperations(final Context context) {
        myDir = context.getFilesDir();

    }
    public Boolean external_write(String fname, String fcontent){
        try {
            File secondFile = new File(myDir + "/text/", fname);
            if (secondFile.getParentFile().mkdirs()) {
                secondFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(secondFile);

                fos.write(fcontent.getBytes());
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public String external_read(String fname){
        StringBuilder total = new StringBuilder();
        try {
            File secondInputFile = new File(myDir + "/text/", fname);
            InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
            BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            secondInputStream.close();
            Log.d("File", "File contents: " + total);
            return total.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}

/*
//错误原因： open failed: EROFS (R, 建议修改方式，强制使用external storage, 然后在manifest中写明permission
public class FileOperations {
    public FileOperations() {}
    public Boolean write(String fname, String fcontent){
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            fos.write(fcontent.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public String read(String fname){
        try {
            FileInputStream inputStream = new FileInputStream(new File(fname));
            //FileInputStream inputStream = new FileInputStream(OpenFi);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();
            Log.d("File", "File contents: " + total);
            return total.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
*/


/*
public class FileOperations {
    public FileOperations() {}
    public Boolean write(String fname, String fcontent){ try {
        //String fpath = "/sdcard/"+fname+".txt";
        String fpath = fname+".txt";
        File file = new File(fpath);
// If file does not exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(fcontent);
        bw.close();
        Log.d("Suceess", "Sucess");
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
    public String read(String fname){
        BufferedReader br = null;
        String response = null;
        try {
            StringBuffer output = new StringBuffer();
            //String fpath = "/sdcard/"+fname+".txt";
            String fpath = fname+".txt";
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"\n");
            }
            response = output.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}
*/