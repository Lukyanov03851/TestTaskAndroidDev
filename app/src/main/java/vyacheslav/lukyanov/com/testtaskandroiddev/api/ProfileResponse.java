package vyacheslav.lukyanov.com.testtaskandroiddev.api;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data{
        private User user;

        public User getUser() {
            return user;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "user=" + user +
                    '}';
        }
    }

    public class User{
        private String partner;
        @SerializedName("seller_rate_success")
        private String sellerRateSuccess;
        @SerializedName("seller_rate_fail")
        private String sellerRateFail;
        @SerializedName("sales_percent")
        private int salesPercent;
        private String avatar;
        @SerializedName("is_online")
        private int isOnline;
        @SerializedName("country_name")
        private String countryName;
        private ProfileSolos profileSolos;

        public String getPartner() {
            return partner;
        }

        public String getSellerRateSuccess() {
            return sellerRateSuccess;
        }

        public String getSellerRateFail() {
            return sellerRateFail;
        }

        public int getSalesPercent() {
            return salesPercent;
        }

        public String getAvatar() {
            return avatar;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public String getCountryName() {
            return countryName;
        }

        public ProfileSolos getProfileSolos() {
            return profileSolos;
        }

        @Override
        public String toString() {
            return "User{" +
                    "partner='" + partner + '\'' +
                    ", sellerRateSuccess='" + sellerRateSuccess + '\'' +
                    ", sellerRateFail='" + sellerRateFail + '\'' +
                    ", salesPercent=" + salesPercent +
                    ", avatar='" + avatar + '\'' +
                    ", isOnline=" + isOnline +
                    ", countryName='" + countryName + '\'' +
                    ", profileSolos=" + profileSolos +
                    '}';
        }

    }

    public class ProfileSolos{
        @SerializedName("hot_sales_cnt")
        private String hotSalesCnt;

        public String getHotSalesCnt() {
            return hotSalesCnt;
        }

        @Override
        public String toString() {
            return "ProfileSolos{" +
                    "hotSalesCnt='" + hotSalesCnt + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProfileResponse{" +
                "data=" + data +
                '}';
    }
}
