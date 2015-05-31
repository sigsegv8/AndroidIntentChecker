package com.taydavid.android.intentchecker;

import android.content.Intent;
import android.net.Uri;

public class IntentCheckerUtils {

    private IntentCheckerUtils() {
        // Utils class
    }

    /**
     * Build an Android intent
     * 
     * @param action
     * @param uriString
     * @param type
     * @param category
     * @return
     */
    static Intent buildIntent(final String action, final String uriString, final String type,
            final String category) {
        Intent intent = new Intent(action);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (uriString != null) {
            if (type != null) {
                intent.setDataAndType(Uri.parse(uriString), type);
            } else {
                intent.setData(Uri.parse(uriString));
            }
        }
        if (category != null) {
            intent.addCategory(category);
        }
        return intent;
    }

}
