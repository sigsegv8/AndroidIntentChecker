package com.taydavid.android.intentchecker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;

import com.taydavid.android.intentchecker.ListenerList.FireHandler;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents a simple file dialog in Android to navigate folders and select a file
 *
 */
public class FileDialog {
    private static final String PARENT_DIR = "..";
    private final String TAG = getClass().getName();
    private String[] mFileList;
    private File mCurrentPath;

    private ListenerList<FileSelectedListener> mFileListenerList = new ListenerList<FileDialog.FileSelectedListener>();
    private ListenerList<DirectorySelectedListener> mDirListenerList = new ListenerList<FileDialog.DirectorySelectedListener>();
    private final Activity mActivity;
    private boolean mSelectDirectoryOption;
    private String mFileMatches;

    public interface FileSelectedListener {
        void fileSelected(File file);
    }

    public interface DirectorySelectedListener {
        void directorySelected(File directory);
    }

    /**
     * @param activity
     * @param initialPath
     */
    public FileDialog(Activity activity, File path) {
        this.mActivity = activity;
        if (!path.exists())
            path = Environment.getExternalStorageDirectory();
        loadFileList(path);
    }

    /**
     * Create the file dialog
     * 
     * @return dialog
     */
    public Dialog createFileDialog() {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        builder.setTitle(mCurrentPath.getPath());
        if (mSelectDirectoryOption) {
            builder.setPositiveButton("Select directory", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, mCurrentPath.getPath());
                    fireDirectorySelectedEvent(mCurrentPath);
                }
            });
        }

        builder.setItems(mFileList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String fileChosen = mFileList[which];
                File chosenFile = getChosenFile(fileChosen);
                if (chosenFile.isDirectory()) {
                    loadFileList(chosenFile);
                    dialog.cancel();
                    dialog.dismiss();
                    showDialog();
                } else
                    fireFileSelectedEvent(chosenFile);
            }
        });

        dialog = builder.show();
        return dialog;
    }

    public void addFileListener(FileSelectedListener listener) {
        mFileListenerList.add(listener);
    }

    public void removeFileListener(FileSelectedListener listener) {
        mFileListenerList.remove(listener);
    }

    public void setSelectDirectoryOption(boolean selectDirectoryOption) {
        this.mSelectDirectoryOption = selectDirectoryOption;
    }

    public void addDirectoryListener(DirectorySelectedListener listener) {
        mDirListenerList.add(listener);
    }

    public void removeDirectoryListener(DirectorySelectedListener listener) {
        mDirListenerList.remove(listener);
    }

    /**
     * Show file dialog
     */
    public void showDialog() {
        createFileDialog().show();
    }

    private void fireFileSelectedEvent(final File file) {
        mFileListenerList.fireEvent(new FireHandler<FileDialog.FileSelectedListener>() {
            public void fireEvent(FileSelectedListener listener) {
                listener.fileSelected(file);
            }
        });
    }

    private void fireDirectorySelectedEvent(final File directory) {
        mDirListenerList.fireEvent(new FireHandler<FileDialog.DirectorySelectedListener>() {
            public void fireEvent(DirectorySelectedListener listener) {
                listener.directorySelected(directory);
            }
        });
    }

    private void loadFileList(File path) {
        this.mCurrentPath = path;
        List<String> r = new ArrayList<String>();
        if (path.exists()) {
            if (path.getParentFile() != null)
                r.add(PARENT_DIR);
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    File sel = new File(dir, filename);
                    if (!sel.canRead())
                        return false;
                    if (mSelectDirectoryOption)
                        return sel.isDirectory();
                    else {
                        boolean matches = mFileMatches != null ? filename.toLowerCase().matches(
                                mFileMatches) : true;
                        return matches || sel.isDirectory();
                    }
                }
            };
            String[] fileList1 = path.list(filter);
            for (String file : fileList1) {
                r.add(file);
            }
        }
        mFileList = (String[]) r.toArray(new String[] {});
    }

    private File getChosenFile(String fileChosen) {
        if (fileChosen.equals(PARENT_DIR))
            return mCurrentPath.getParentFile();
        else
            return new File(mCurrentPath, fileChosen);
    }

    public void setFileMatches(String fileMatches) {
        this.mFileMatches = fileMatches != null ? fileMatches.toLowerCase() : fileMatches;
    }
}

class ListenerList<L> {
    private List<L> listenerList = new ArrayList<L>();

    public interface FireHandler<L> {
        void fireEvent(L listener);
    }

    public void add(L listener) {
        listenerList.add(listener);
    }

    public void fireEvent(FireHandler<L> fireHandler) {
        List<L> copy = new ArrayList<L>(listenerList);
        for (L l : copy) {
            fireHandler.fireEvent(l);
        }
    }

    public void remove(L listener) {
        listenerList.remove(listener);
    }

    public List<L> getListenerList() {
        return listenerList;
    }
}
