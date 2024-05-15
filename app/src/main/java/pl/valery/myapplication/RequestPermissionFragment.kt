package pl.valery.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class RequestPermissionFragment : Fragment() {

    private val contract: ActivityResultContracts.RequestMultiplePermissions =
        ActivityResultContracts.RequestMultiplePermissions()

    private val launcher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(contract) { result ->
            if (PermissionUtils.verifyPermissions(result)) {
                showToast(R.string.permission_available)
            } else {
                showToast(R.string.permission_denied)
            }
        }

    private fun startPermissionFlow() {
        if (requireArguments().containsKey(PERMISSIONS)) {
            val permissions = requireArguments().getStringArray(PERMISSIONS) as Array<String>
            launcher.launch(permissions)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startPermissionFlow()

    }
    private fun showToast(@StringRes messageId: Int) {
        Toast.makeText(requireContext(), messageId, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "RequestPermissionFragment"

        private const val PERMISSIONS = "permissions"
        private const val MESSAGE = "message"

    }

    class Builder {

        private val fragment: RequestPermissionFragment = RequestPermissionFragment()
        private val arguments: Bundle = Bundle()

        init {
            fragment.arguments = arguments
        }

        fun permissions(vararg permissions: String): Builder {
            arguments.putStringArray(PERMISSIONS, permissions)
            return this
        }

        fun show(fragmentManager: FragmentManager) {
            fragmentManager.commit {
                add(fragment, TAG)
            }
        }
    }
}
