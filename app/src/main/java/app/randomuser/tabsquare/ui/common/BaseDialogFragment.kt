package app.randomuser.tabsquare.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.randomuser.tabsquare.ui.common.navigation.ActivityNavigation
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseDialogFragment : DialogFragment() {

    @Inject
    lateinit var mActivityNavigation: ActivityNavigation

    var mView : View? = null
    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId () : Int

    /**
     * This method will be executed after view has been create in fragment
     */
    protected abstract fun onLoadFragment(saveInstance: Bundle?)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.let {
            mView  = inflater.inflate(getLayoutId(), container, false)
        }
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onLoadFragment(savedInstanceState)
    }

}