package pl.valery.myapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager

object PermissionUtils {

    @get:ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU, codename = "T")
    private inline val isAtLeastT33: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU


    val POST_NOTIFICATIONS =
        if (isAtLeastT33) Manifest.permission.POST_NOTIFICATIONS else "android.permission.POST_NOTIFICATIONS"
    val NOTIFICATION_PERMISSIONS = arrayOf(POST_NOTIFICATIONS)

    fun hasNotificationPermissions(context: Context): Boolean {
        return if (isAtLeastT33) {
            hasPermissions(context, *NOTIFICATION_PERMISSIONS)
        } else {
            // Android < 13: notification permission is implied. This is not the same as areNotificationsEnabled(),
            // which might be false if the user has intentionally disabled notifications for the app.
            true
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        val permissionsArray = permissions.toList().toTypedArray()
        val grantResults = getGrantResults(context, permissionsArray)
        return verifyPermissions(grantResults)
    }

    fun getGrantResults(context: Context, permissions: Array<String>?): IntArray? {
        return permissions?.takeIf { it.isNotEmpty() }?.map {
            if (!isAtLeastT33 && POST_NOTIFICATIONS == it) {
                PackageManager.PERMISSION_GRANTED
            } else {
                ActivityCompat.checkSelfPermission(context, it)
            }
        }?.toIntArray()
    }

    fun verifyPermissions(grantResults: IntArray?): Boolean {
        // Verify that each required permission has been granted, otherwise return false.
        return grantResults != null &&
                grantResults.isNotEmpty() &&
                grantResults.all { it == PackageManager.PERMISSION_GRANTED }
    }

    fun verifyPermissions(result: Map<String, Boolean>?): Boolean {
        return !result.isNullOrEmpty() && result.all { it.value }
    }

    fun requestNotificationPermission(fragmentManager: FragmentManager) {
        RequestPermissionFragment.Builder()
            .permissions(*NOTIFICATION_PERMISSIONS)
            .show(fragmentManager)
    }
}
