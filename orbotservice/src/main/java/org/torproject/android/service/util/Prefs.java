
package org.torproject.android.service.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.torproject.android.service.OrbotConstants;
import org.torproject.android.service.wrapper.orbotLocalConstants;

import java.util.Locale;

public class Prefs {

    private final static String PREF_BRIDGES_ENABLED = "pref_bridges_enabled";
    private final static String PREF_BRIDGES_LIST = "pref_bridges_list";
    private final static String PREF_DEFAULT_LOCALE = "pref_default_locale";
    private final static String PREF_ENABLE_LOGGING = "pref_enable_logging";
    private final static String PREF_EXPANDED_NOTIFICATIONS = "pref_expanded_notifications";
    private final static String PREF_PERSIST_NOTIFICATIONS = "pref_persistent_notifications";
    private final static String PREF_ALLOW_BACKGROUND_STARTS = "pref_allow_background_starts";
    private final static String PREF_OPEN_PROXY_ON_ALL_INTERFACES = "pref_open_proxy_on_all_interfaces";
    private final static String PREF_USE_VPN = "pref_vpn";

    private static SharedPreferences prefs;

    public static void setContext(Context context) {
        if (prefs == null)
            prefs = getSharedPrefs(context);
    }

    private static void validatePrefs(){
        if(prefs==null){
            prefs = getSharedPrefs(orbotLocalConstants.sHomeContext.get());
        }
    }

    private static void putBoolean(String key, boolean value) {
        validatePrefs();
        prefs.edit().putBoolean(key, value).apply();
    }

    private static void putString(String key, String value) {
        validatePrefs();
        prefs.edit().putString(key, value).apply();
    }

    public static boolean bridgesEnabled() {
        validatePrefs();
        boolean bridgesEnabledDefault = Locale.getDefault().getLanguage().equals("fa");
        return prefs.getBoolean(PREF_BRIDGES_ENABLED, bridgesEnabledDefault);
    }

    public static void putBridgesEnabled(boolean value) {
        validatePrefs();
        putBoolean(PREF_BRIDGES_ENABLED, value);
    }

    public static String getBridgesList() {
        validatePrefs();
        String defaultBridgeType = "obfs4";
        if (Locale.getDefault().getLanguage().equals("fa"))
            defaultBridgeType = "meek"; //if Farsi, use meek as the default bridge type
        return prefs.getString(PREF_BRIDGES_LIST, defaultBridgeType);
    }

    public static String getDefaultLocale() {
        validatePrefs();
        return prefs.getString(PREF_DEFAULT_LOCALE, Locale.getDefault().getLanguage());
    }

    public static void setDefaultLocale(String value) {
        putString(PREF_DEFAULT_LOCALE, value);
    }

    public static boolean expandedNotifications() {
        validatePrefs();
        return prefs.getBoolean(PREF_EXPANDED_NOTIFICATIONS, true);
    }

    public static boolean useDebugLogging() {
        //prefs = null;
        //validatePrefs();
        return false;
    }

    public static boolean persistNotifications() {
        validatePrefs();
        return prefs.getBoolean(PREF_PERSIST_NOTIFICATIONS, true);
    }

    public static boolean allowBackgroundStarts() {
        validatePrefs();
        return prefs.getBoolean(PREF_ALLOW_BACKGROUND_STARTS, true);
    }

    public static boolean openProxyOnAllInterfaces() {
        validatePrefs();
        return prefs.getBoolean(PREF_OPEN_PROXY_ON_ALL_INTERFACES, false);
    }

    public static boolean useVpn() {
        validatePrefs();
        return prefs.getBoolean(PREF_USE_VPN, false);
    }

    public static void putUseVpn(boolean value) {
        validatePrefs();
        putBoolean(PREF_USE_VPN, value);
    }


    public static SharedPreferences getSharedPrefs (Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
