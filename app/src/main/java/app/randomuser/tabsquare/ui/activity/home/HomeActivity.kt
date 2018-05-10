package app.randomuser.tabsquare.ui.activity.home

import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import app.randomuser.tabsquare.R
import app.randomuser.tabsquare.adapters.setUp
import app.randomuser.tabsquare.helper.Helper
import app.randomuser.tabsquare.ui.common.BaseActivity
import app.randomuser.tabsquare.utils.AppConstants.GENDER.Companion.FEMALE
import app.randomuser.tabsquare.utils.AppConstants.GENDER.Companion.MALE
import app.randomuser.tabsquare.utils.InfiniteScrollListener
import app.randomuser.tabsquare.vo.api.Result
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_list_user.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity: BaseActivity(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mPresenter: HomePresenter

    companion object {
        val REFRESH_DATA = "refresh_data"
        val LOAD_MORE_DATA = "load_more_data"
    }

    var reqPage = 1
    var resultCount = 10
    var mUserList = ArrayList<Result>()

    lateinit var mLayoutManager : RecyclerView.LayoutManager
    lateinit var mScrollViewListener : InfiniteScrollListener

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mPresenter.mView = this
        setupUI()
        setupListener()
        loadUserList()
    }

    override fun loadUserList() {
        mUserList.clear()
        mPresenter.checkUserData(reqPage.toString(),resultCount.toString(), REFRESH_DATA)
    }

    override fun onBackPressed() {
        showExitPopup()
    }

    override fun setupUI() {
        homeSwipeLayout.setOnRefreshListener(this)
        mLayoutManager = GridLayoutManager(applicationContext, 2)
        llOfflineView.setOnClickListener {
            mPresenter.checkUserData(reqPage.toString(),resultCount.toString(), LOAD_MORE_DATA)
        }
    }

    override fun onRefresh() {
        homeSwipeLayout.isRefreshing = false
        loadUserList()
    }

    override fun clearUserList() {
        mUserList.clear()
    }

    override fun setUserList(resultList: List<Result>) {
        for(result in resultList) {
            mUserList.add(result)
            mPresenter.saveUserDetailData(result.login.md5, result)
        }
    }

    override fun setupListener() {
        mScrollViewListener = object : InfiniteScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                reqPage = page + 1
                mPresenter.checkUserData(reqPage.toString(),resultCount.toString(), LOAD_MORE_DATA)

                val cursize = rvUserList.adapter.itemCount
                view.post(object : Runnable{
                    override fun run() {
                        rvUserList.adapter.notifyItemRangeInserted(cursize, mUserList.size - 1)
                    }
                })
            }
        }
    }

    override fun setAdapter() {
        rvUserList.addOnScrollListener(mScrollViewListener)
        rvUserList.setUp(
                mUserList,
                R.layout.item_list_user,
                {
                    loadImageToImageView(it.picture.large,imgUser)
                    tvUserTitle.text = "${it.name.first.capitalize()} ${it.name.last.capitalize()}"
                    tvUserPhone.text = it.phone
                    tvUserContent.text = "${it.location.street.capitalize()}, ${it.location.city.capitalize()}, ${it.location.state.capitalize()}, ${it.location.postcode}"

                    when(it.gender) {
                        FEMALE -> {
                            imgGender.visibility = View.VISIBLE
                            imgGender.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_gender_female))
                        }
                        MALE -> {
                            imgGender.visibility = View.VISIBLE
                            imgGender.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_gender_male))
                        }
                        else -> {
                            imgGender.visibility = View.GONE
                        }
                    }
                },
                {
                    toast("User ${it.login.md5} clicked")
                },
                mLayoutManager
        )
    }

    override fun loadImageToImageView(mImagesUrl: String, imgView: ImageView) {
        imgView.visibility = View.VISIBLE
        Picasso.get()
                .load(Uri.parse(mImagesUrl))
                .placeholder(R.drawable.progressbar)
                .fit()
                .centerInside()
                .into(imgView)
    }

    override fun showEmptyResult() {
        rlEmptyUserList.visibility = View.VISIBLE
    }

    override fun showErrorResult(message: String) {
        rlErrorUserList.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        avLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideEmptyResult() {
        rlEmptyUserList.visibility = View.GONE
    }

    override fun hideErrorResult() {
        rlErrorUserList.visibility = View.GONE
    }

    override fun hideProgressBar() {
        avLoadingIndicator.visibility = View.GONE
    }

    override fun showOfflineView() {
        toast("Cannot load more data")
        llOfflineView.visibility = View.VISIBLE
    }

    override fun hideOfflineView() {
        llOfflineView.visibility = View.GONE
    }

    override fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    override fun exitApp() {
        finishAffinity()
        System.exit(0)
    }

    override fun showExitPopup() {
        val dialogSource = "2"
        val btnPositive = "Keluar"
        val btnNegative = "Batal"

        mHelper.showPopupDialog(this,
                "Konfirmasi",
                "Apakah Anda ingin keluar dari Aplikasi ?",
                dialogSource,
                btnPositive,
                btnNegative,
                object : Helper.CallbackDialog {
                    override fun onButtonPositiveClicked() {
                        exitApp()
                    }

                    override fun onButtonNegativeClicked() {

                    }

                })
    }
}