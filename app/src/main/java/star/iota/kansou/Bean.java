package star.iota.kansou;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class Bean {
    private final int type;
    private final HashMap<String, ArrayList<ItemBean>> category;

    public Bean(int type, HashMap<String, ArrayList<ItemBean>> category) {
        this.type = type;
        this.category = category;
    }

    public int getType() {
        return type;
    }

    HashMap<String, ArrayList<ItemBean>> getCategory() {
        return category;
    }

    static class ItemBean implements Parcelable {
        public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
            @Override
            public ItemBean createFromParcel(Parcel in) {
                return new ItemBean(in);
            }

            @Override
            public ItemBean[] newArray(int size) {
                return new ItemBean[size];
            }
        };
        private final String firstColumn;
        private final String secondColumn;
        private final String thirdColumn;

        ItemBean(String firstColumn, String secondColumn, String thirdColumn) {
            this.firstColumn = firstColumn;
            this.secondColumn = secondColumn;
            this.thirdColumn = thirdColumn;
        }

        ItemBean(Parcel in) {
            firstColumn = in.readString();
            secondColumn = in.readString();
            thirdColumn = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(firstColumn);
            dest.writeString(secondColumn);
            dest.writeString(thirdColumn);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        String getFirstColumn() {
            return firstColumn;
        }

        String getSecondColumn() {
            return secondColumn;
        }

        String getThirdColumn() {
            return thirdColumn;
        }
    }
}
