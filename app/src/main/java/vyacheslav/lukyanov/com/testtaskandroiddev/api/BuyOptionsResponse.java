package vyacheslav.lukyanov.com.testtaskandroiddev.api;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BuyOptionsResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {

        private String price;
        @SerializedName("filterPriceOptions")
        private List<FilterPriceOption> filterPriceOptions = null;
        @SerializedName("clickFilterPrice")
        private Integer clickFilterPrice;

        public String getPrice() {
            return price;
        }

        public List<FilterPriceOption> getFilterPriceOptions() {
            return filterPriceOptions;
        }

        public Integer getClickFilterPrice() {
            return clickFilterPrice;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "price='" + price + '\'' +
                    ", filterPriceOptions=" + filterPriceOptions +
                    ", clickFilterPrice=" + clickFilterPrice +
                    '}';
        }
    }

    public class FilterPriceOption {

        private String label;
        private String _class;
        private String price;
        private List<Integer> filters = null;
        private Integer percent;
        @SerializedName("is_default")
        private Integer isDefault;
        @SerializedName("id_filter")
        private Integer idFilter;

        public String getLabel() {
            return label;
        }

        public String getClass_() {
            return _class;
        }

        public String getPrice() {
            return price;
        }

        public List<Integer> getFilters() {
            return filters;
        }

        public Integer getPercent() {
            return percent;
        }

        public Integer getIsDefault() {
            return isDefault;
        }

        public Integer getIdFilter() {
            return idFilter;
        }

        @Override
        public String toString() {
            return "FilterPriceOption{" +
                    "label='" + label + '\'' +
                    ", _class='" + _class + '\'' +
                    ", price='" + price + '\'' +
                    ", filters=" + filters +
                    ", percent=" + percent +
                    ", isDefault=" + isDefault +
                    ", idFilter=" + idFilter +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BuyOptionsResponse{" +
                "data=" + data +
                '}';
    }
}
