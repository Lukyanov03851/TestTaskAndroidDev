package vyacheslav.lukyanov.com.testtaskandroiddev;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vyacheslav.lukyanov.com.testtaskandroiddev.api.ApiClient;
import vyacheslav.lukyanov.com.testtaskandroiddev.api.BuyOptionsResponse;
import vyacheslav.lukyanov.com.testtaskandroiddev.api.ProfileResponse;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();
    private static int SEEK_BAR_MIN_VALUE = 50;
    private static int SEEK_BAR_MAX_VALUE = 1000;
    private static int SEEK_BAR_STEP_VALUE = 50;
//    int id = 1126;
    int id = 9890;

    ConstraintLayout mainLayout;
    ProgressBar progressBar;
    TextView tvPartner, tvSellerRateSuccess, tvSellerRateFail, tvSalesPercent, tvIsOnline, tvCountry, tvHotSalesCnt;
    ImageView avatar;
    TextView tvVisitors;
    CheckBox checkBoxOnlyMobile, checkBoxNoMobile, checkBoxOnlyTopTier, checkBoxPrimeFilter;
    EditTextUrl editTextUrl;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mainLayout = findViewById(R.id.main_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        progressBar = findViewById(R.id.progress_bar);

        tvPartner = findViewById(R.id.partner);
        tvSellerRateSuccess = findViewById(R.id.tv_seller_rate_success);
        tvSellerRateFail = findViewById(R.id.tv_seller_rate_fail);
        tvSalesPercent = findViewById(R.id.tv_sales_percent_value);
        tvIsOnline = findViewById(R.id.tv_is_online);
        tvCountry = findViewById(R.id.tv_country);
        tvHotSalesCnt = findViewById(R.id.tv_hot_sales_cnt);
        avatar = findViewById(R.id.img_avatar);
        button = findViewById(R.id.btn_solo_price);
        button.setOnClickListener(v -> {
            getProfileBuyOptions();
            checkURL();
        });
        setUpSeekBar();

        setUpCheckBoxes();
        editTextUrl = findViewById(R.id.edt_url);
        getProfileData();

    }

    private void setUpSeekBar(){
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(SEEK_BAR_MIN_VALUE);

        tvVisitors = findViewById(R.id.tv_visitors);

        setVisitors(SEEK_BAR_MIN_VALUE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double value = Math.round((progress * (SEEK_BAR_MAX_VALUE - SEEK_BAR_MIN_VALUE)) / 1000);
                int displayValue = (((int) value + SEEK_BAR_MIN_VALUE) / SEEK_BAR_STEP_VALUE) * SEEK_BAR_STEP_VALUE;
                seekBar.setProgress(displayValue);
                setVisitors(displayValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setVisitors(int progress){
        String visitorsValue = getString(R.string.visitors_value, progress);
        tvVisitors.setText(visitorsValue);
    }

    private void setUpCheckBoxes() {
        checkBoxOnlyMobile = findViewById(R.id.checkbox_only_mobile);
        checkBoxNoMobile = findViewById(R.id.checkbox_no_mobile);
        checkBoxOnlyTopTier = findViewById(R.id.checkbox_only_top_tier);
        checkBoxPrimeFilter = findViewById(R.id.checkbox_prime_filter);

        checkBoxOnlyMobile.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                checkBoxNoMobile.setChecked(false);
            }
        });

        checkBoxNoMobile.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                checkBoxOnlyMobile.setChecked(false);
            }
        });
    }

    private void checkURL() {
        String url = editTextUrl.getText().toString();
        if (!isValidUrl(url)){
            editTextUrl.setError(getString(R.string.url_not_valid), getDrawable(R.drawable.ic_fire));
        }
    }

    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        if(m.matches())
            return true;
        else
            return false;
    }

    private void getProfileData() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 9124);

        ApiClient.RestApiClient apiClient = ApiClient.getClient();
        Call<ProfileResponse> call = apiClient.getProfile(hashMap);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                progressBar.setVisibility(View.GONE);
                ProfileResponse profile = response.body();
                if (response.code() == 200 && profile != null) {
                    Log.d(TAG, "PROFILE = "+profile);
                    processProfile(profile);
                    mainLayout.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(ProfileActivity.this,"Something was wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this,"Something was wrong", Toast.LENGTH_LONG).show();
                Log.d("onFailure", "t.getMessage()"+t.getMessage());
            }
        });
    }

    private void getProfileBuyOptions() {
        progressBar.setVisibility(View.VISIBLE);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 9124);

        ApiClient.RestApiClient apiClient = ApiClient.getClient();
        Call<BuyOptionsResponse> call = apiClient.getProfileBuyOptions(hashMap);
        call.enqueue(new Callback<BuyOptionsResponse>() {
            @Override
            public void onResponse(Call<BuyOptionsResponse> call, Response<BuyOptionsResponse> response) {
                progressBar.setVisibility(View.GONE);
                BuyOptionsResponse buyOptionsResponse = response.body();
                if (response.code() == 200 && buyOptionsResponse != null) {
                    Log.e(TAG, "BuyOptionsResponse = "+buyOptionsResponse);
                    double soloPrice = calculatePrice(buyOptionsResponse);
                    button.setText(getString(R.string.add_to_cart, (int)soloPrice));
                }else {
                    Toast.makeText(ProfileActivity.this,"Something was wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BuyOptionsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("onFailure", "t.getMessage()"+t.getMessage());
            }
        });
    }

    private void processProfile(ProfileResponse response) {
        tvPartner.setText(response.getData().getUser().getPartner());
        tvSellerRateSuccess.setText(response.getData().getUser().getSellerRateSuccess());
        tvSellerRateFail.setText(response.getData().getUser().getSellerRateFail());

        String percent = String.valueOf(response.getData().getUser().getSalesPercent())+"%";
        tvSalesPercent.setText(percent);

        new AvatarAsyncTask(avatar).execute(response.getData().getUser().getAvatar());

        tvIsOnline.setVisibility(response.getData().getUser().getIsOnline()==1?View.VISIBLE:View.GONE);
        tvCountry.setText(response.getData().getUser().getCountryName());

        String hotSalesCnt = getString(R.string.hot_sales_cnt, response.getData().getUser().getProfileSolos().getHotSalesCnt());

        tvHotSalesCnt.setText(hotSalesCnt);
    }

    private double calculatePrice(BuyOptionsResponse buyOptionsResponse) {
        double sumOfFiltersPerClick = 0;
        ArrayList<CheckBox> filters = getFilters();
        for (BuyOptionsResponse.FilterPriceOption option: buyOptionsResponse.getData().getFilterPriceOptions()){
            for (CheckBox checkBox: filters){
                if (option.getLabel().equals(checkBox.getText().toString())){
                    sumOfFiltersPerClick += Double.valueOf(option.getPrice());
                }
            }
        }


        int orderClicks = buyOptionsResponse.getData().getClickFilterPrice();
        double soloPrice = Math.ceil(Math.round(Double.valueOf(buyOptionsResponse.getData().getPrice()) * orderClicks)
                +
                Math.round(sumOfFiltersPerClick * orderClicks) + 3.00 );
        Log.e(TAG, "SOLO PRICE = "+soloPrice+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return soloPrice;
    }

    private ArrayList<CheckBox> getFilters(){
        ArrayList<CheckBox> filters = new ArrayList<>();
        if (checkBoxOnlyMobile.isChecked()){
            filters.add(checkBoxOnlyMobile);
        }

        if (checkBoxNoMobile.isChecked()){
            filters.add(checkBoxNoMobile);
        }

        if (checkBoxOnlyTopTier.isChecked()){
            filters.add(checkBoxOnlyTopTier);
        }

        if (checkBoxPrimeFilter.isChecked()){
            filters.add(checkBoxPrimeFilter);
        }

        return filters;
    }

}
