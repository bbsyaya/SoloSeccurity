package com.solo.security.data.whitelistsource;

import android.content.Context;

import com.google.common.base.Preconditions;
import com.solo.security.SecurityApplication;
import com.solo.security.contansts.SecurityConstants;
import com.solo.security.data.Security;
import com.solo.security.prefs.SecurityPreference;
import com.solo.security.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Messi on 16-11-4.
 */

public enum WhiteListDataImpl implements WhiteListData {

    INSTANCE;

    @Override
    public void loadMemoryWhiteList(WhiteListDataCallback callback) {
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        Set<String> memoryPkgs = SecurityPreference.getStringSet(context, SecurityConstants.PREFS_KEY_MEMORY_WHITE_LIST);
        if (memoryPkgs == null || memoryPkgs.isEmpty()) {
            callback.onEmptyMemoryList();
        } else {
            List<Security> securities = new ArrayList<>();
            for (String pkg : memoryPkgs) {
                Security security = new Security();
                security.setPackageName(pkg);
                security.setIcon(AppUtils.getApplicationIcon(context, pkg));
                security.setLabel((String) AppUtils.getApplicationLabel(context, pkg));

                securities.add(security);
            }
            callback.onMemoryListLoaded(securities);
        }
    }

    @Override
    public void loadSafeWhiteList(WhiteListDataCallback callback) {
        Context context = Preconditions.checkNotNull(SecurityApplication.getContext());
        Set<String> safePkgs = SecurityPreference.getStringSet(context, SecurityConstants.PREFS_KEY_SAFE_WHITE_LIST);
        if (safePkgs == null || safePkgs.isEmpty()) {
            callback.onEmptySafeList();
        } else {
            List<Security> securities = new ArrayList<>();
            for (String pkg : safePkgs) {
                Security security = new Security();
                security.setPackageName(pkg);
                security.setIcon(AppUtils.getApplicationIcon(context, pkg));
                security.setLabel((String) AppUtils.getApplicationLabel(context, pkg));

                securities.add(security);
            }
            callback.onSafeListLoaded(securities);
        }
    }
}
