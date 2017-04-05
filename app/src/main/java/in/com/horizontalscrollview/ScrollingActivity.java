package in.com.horizontalscrollview;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.com.horizontalscrollview.scrolls.Item;
import in.com.horizontalscrollview.scrolls.Shop;
import in.com.horizontalscrollview.scrolls.ShopAdapter;
import in.com.scrollviewlib.DiscreteScrollView;
import in.com.scrollviewlib.Orientation;
import in.com.scrollviewlib.transform.ScaleTransformer;

public class ScrollingActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener {
    private List<Item> data;
    private Shop shop;

    private TextView currentItemName;
    private TextView currentItemPrice;
    private ImageView rateItemButton;
    private DiscreteScrollView itemPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        currentItemName = (TextView) findViewById(R.id.item_name);
        currentItemPrice = (TextView) findViewById(R.id.item_price);

        shop = Shop.get();
        data = shop.getData();
        itemPicker = (DiscreteScrollView) findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.setOnItemChangedListener(this);
        itemPicker.setAdapter(new ShopAdapter(data));
        itemPicker.setItemTransitionTimeMillis(200);//DiscreteScrollViewOptions.getTransitionTime()
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        onItemChanged(data.get(0));
/*
        findViewById(R.id.item_btn_rate).setOnClickListener(this);
        findViewById(R.id.item_btn_buy).setOnClickListener(this);
        findViewById(R.id.item_btn_comment).setOnClickListener(this);*/

        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.btn_smooth_scroll).setOnClickListener(this);
        findViewById(R.id.btn_transition_time).setOnClickListener(this);
    }
    private void onItemChanged(Item item) {
        currentItemName.setText(item.getName());
        currentItemPrice.setText(item.getPrice());
        //changeRateButtonState(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.home:
                finish();
                break;
            case R.id.btn_transition_time:
               // DiscreteScrollViewOptions.configureTransitionTime(itemPicker);
                break;
            case R.id.btn_smooth_scroll:
                DiscreteScrollViewOptions.smoothScrollToUserSelectedPosition(itemPicker, v);
                break;
            default:
                showUnsupportedSnackBar();
                break;
        }
    }
    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        onItemChanged(data.get(position));
    }

    private void showUnsupportedSnackBar() {
        Snackbar.make(itemPicker, R.string.msg_unsupported_op, Snackbar.LENGTH_SHORT).show();
    }

}
