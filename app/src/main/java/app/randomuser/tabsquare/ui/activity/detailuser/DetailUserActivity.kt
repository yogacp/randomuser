package app.randomuser.tabsquare.ui.activity.detailuser

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import app.randomuser.tabsquare.R
import app.randomuser.tabsquare.ui.common.BaseActivity
import app.randomuser.tabsquare.vo.api.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_user.*
import org.jetbrains.anko.appcompat.v7.toolbar
import javax.inject.Inject

class DetailUserActivity : BaseActivity(), DetailUserContract.View {

    @Inject
    lateinit var mPresenter: DetailUserPresenter

    companion object {
        val TAG_USER_HASH = "user_hash"
    }

    var mUserHash = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_user
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
        initalizeData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun initalizeData() {
        mPresenter.mView = this

        val bundle = intent.extras
        if (bundle != null) {
            if (bundle.containsKey(TAG_USER_HASH)) {
                mUserHash = bundle.getString(TAG_USER_HASH)
                mPresenter.getDetailUser(mUserHash)
            }
        }
    }

    override fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun setupData(result: Result?) {
        result?.let {
            loadImageToImageView(it.picture.large,imgProfile)
            txtName.text        = "${it.name.first.capitalize()} ${it.name.last.capitalize()}"
            txtRegister.text    = it.registered
            txtPhone.text       = it.phone
            txtID.text          = "${it.id.name} - ${it.id.value}"
            txtEmail.text       = it.email
            txtUsername.text    = it.login.username
            txtDateOfBirth.text = it.dob
            txtAddress.text     = "${it.location.street}, ${it.location.city}, ${it.location.state}, ${it.location.postcode}"

            imgProfile.setOnClickListener {
                mActivityNavigation.navigateToPopupImageUser(result.picture.large)
            }
        }
    }

    override fun loadImageToImageView(mImagesUrl: String, imgView: ImageView) {
        Picasso.get()
                .load(Uri.parse(mImagesUrl))
                .placeholder(R.drawable.user)
                .fit()
                .centerInside()
                .into(imgView)
    }
}