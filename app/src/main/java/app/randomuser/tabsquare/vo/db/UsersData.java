package app.randomuser.tabsquare.vo.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UsersData implements Parcelable {

    @Index(unique = true)
    @Id(autoincrement = true)
    @SerializedName("resultid")
    private Long resultid;
    @SerializedName("data")
    public String data;
    @SerializedName("page")
    public String page;
    @SerializedName("lastUpdated")
    public Long lastUpdated;

    @Generated(hash = 2134971473)
    public UsersData(Long resultid, String data, String page, Long lastUpdated) {
        this.resultid = resultid;
        this.data = data;
        this.page = page;
        this.lastUpdated = lastUpdated;
    }

    @Generated(hash = 296630533)
    public UsersData() {
    }

    protected UsersData(Parcel in) {
        if (in.readByte() == 0) {
            resultid = null;
        } else {
            resultid = in.readLong();
        }
        data = in.readString();
        page = in.readString();
        if (in.readByte() == 0) {
            lastUpdated = null;
        } else {
            lastUpdated = in.readLong();
        }
    }

    public static final Creator<UsersData> CREATOR = new Creator<UsersData>() {
        @Override
        public UsersData createFromParcel(Parcel in) {
            return new UsersData(in);
        }

        @Override
        public UsersData[] newArray(int size) {
            return new UsersData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (resultid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(resultid);
        }
        dest.writeString(data);
        dest.writeString(page);
        if (lastUpdated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(lastUpdated);
        }
    }

    public Long getResultid() {
        return this.resultid;
    }

    public void setResultid(Long resultid) {
        this.resultid = resultid;
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

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
