package app.randomuser.tabsquare.vo.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class UsersData implements Parcelable {

    @Index(unique = true)
    @SerializedName("resultid")
    public Long resultid;
    @SerializedName("data")
    public String data;
    @SerializedName("lastUpdated")
    public Long lastUpdated;

    protected UsersData(Parcel in) {
        if (in.readByte() == 0) {
            resultid = null;
        } else {
            resultid = in.readLong();
        }
        data = in.readString();
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
        if (lastUpdated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(lastUpdated);
        }
    }
}
