package app.randomuser.tabsquare.vo.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserDetailData implements Parcelable {

    @Index(unique = true)
    @Id(autoincrement = true)
    @SerializedName("userid")
    public Long userid;
    @SerializedName("md5")
    public String md5;
    @SerializedName("page")
    public String page;
    @SerializedName("data")
    public String data;
    @SerializedName("lastUpdated")
    public Long lastUpdated;

    @Generated(hash = 1819435531)
    public UserDetailData(Long userid, String md5, String page, String data,
            Long lastUpdated) {
        this.userid = userid;
        this.md5 = md5;
        this.page = page;
        this.data = data;
        this.lastUpdated = lastUpdated;
    }

    @Generated(hash = 1103649994)
    public UserDetailData() {
    }

    protected UserDetailData(Parcel in) {
        if (in.readByte() == 0) {
            userid = null;
        } else {
            userid = in.readLong();
        }
        md5 = in.readString();
        page = in.readString();
        data = in.readString();
        if (in.readByte() == 0) {
            lastUpdated = null;
        } else {
            lastUpdated = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(userid);
        }
        dest.writeString(md5);
        dest.writeString(page);
        dest.writeString(data);
        if (lastUpdated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(lastUpdated);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static final Creator<UserDetailData> CREATOR = new Creator<UserDetailData>() {
        @Override
        public UserDetailData createFromParcel(Parcel in) {
            return new UserDetailData(in);
        }

        @Override
        public UserDetailData[] newArray(int size) {
            return new UserDetailData[size];
        }
    };
}
