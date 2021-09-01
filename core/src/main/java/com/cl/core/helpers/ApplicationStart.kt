//package com.cl.core.helpers
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.content.pm.PackageInfo
//import android.content.pm.PackageManager
//import android.util.Log
//
//enum class AppStart {
//    FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
//}
//
//object ApplicationStart {
//
//    private val TAG = "Ithoob"
//    /**
//     * The app version code (not the version name!) that was used on the last
//     * start of the app.
//     */
//    private val LAST_APP_VERSION = "1"
//
//    /**
//     * Caches the result of [.checkAppStart]. To allow idempotent method
//     * calls.
//     */
//    private var appStart: AppStart? = null
//
//    /**
//     * Finds out started for the first time (ever or in the current version).
//     *
//     * @return the type of app start
//     */
//    fun checkAppStart(context: Context, sharedPreferences: SharedPreferences): AppStart? {
//        val pInfo: PackageInfo
//        try {
//            pInfo = context.packageManager.getPackageInfo(
//                    context.packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT)
//            val lastVersionCode = sharedPreferences.getInt(
//                    LAST_APP_VERSION, -1)
//            // String versionName = pInfo.versionName;
//            val currentVersionCode = pInfo.versionCode
//            appStart = checkAppStart(currentVersionCode, lastVersionCode)
//
//            // Update version in preferences
//            sharedPreferences.edit().putInt(LAST_APP_VERSION, currentVersionCode).apply() // must use commit here or app may not update prefs in time and app will loop into walkthrough
//        } catch (e: PackageManager.NameNotFoundException) {
//            Timber.i( "Unable to determine current app version from package manager. Defensively assuming normal app start.")
//        }
//
//        return appStart
//    }
//
//    private fun checkAppStart(currentVersionCode: Int, lastVersionCode: Int): AppStart {
//        when {
//            lastVersionCode == -1 -> return AppStart.FIRST_TIME
//            lastVersionCode < currentVersionCode -> return AppStart.FIRST_TIME_VERSION
//            lastVersionCode > currentVersionCode -> {
//                return AppStart.NORMAL
//            }
//            else -> return AppStart.NORMAL
//        }
//    }
//}
//
