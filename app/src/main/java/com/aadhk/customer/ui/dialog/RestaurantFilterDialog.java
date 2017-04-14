package com.aadhk.customer.ui.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.CompanyRequest;
import com.aadhk.customer.bean.FoodStyles;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.adapter.FoodTypeAdapter;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FlowLayoutManager;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.utils.DisplayUtil;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.ButtonFlat;

import org.acra.ACRA;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 01/12/2016.
 */

public class RestaurantFilterDialog extends BaseDialog implements View.OnClickListener {
    private TextView tvFoodStyleM;
    private RecyclerView lv;
    private ButtonFlat btnDelivery, btnDineIn, btnTakeOut;
    private ButtonFlat btnReset, btnConfirm;
    private List<FoodStyles> data;
    private FoodTypeAdapter foodAdapter;
    private int restaurantType = 0;

    public RestaurantFilterDialog(Context context, List<FoodStyles> data) {
        super(context, R.layout.dialog_restaurant_filter);
        this.data = data;
        setCancelable(true);
        tvFoodStyleM = $(R.id.tvFoodStyleM);
        btnDelivery = $(R.id.btnDelivery);
        btnDineIn = $(R.id.btnDineIn);
        btnTakeOut = $(R.id.btnTakeOut);
        btnReset = $(R.id.btnReset);
        btnConfirm = $(R.id.btnConfirm);
        btnReset.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        btnDelivery.setTag((int)Constant.TYPE_DELIVERY_VALUE);
        btnDineIn.setTag((int)Constant.TYPE_DINEIN_VALUE);
        btnTakeOut.setTag((int)Constant.TYPE_TAKEOUT_VALUE);
        btnDelivery.setOnClickListener(flatButtonListener);
        btnDineIn.setOnClickListener(flatButtonListener);
        btnTakeOut.setOnClickListener(flatButtonListener);

        if(data == null || data.size() == 0){
            tvFoodStyleM.setVisibility(View.GONE);
            data = new ArrayList<>();
        }
        lv = (RecyclerView) findViewById(R.id.lv);
        FlowLayoutManager layoutManager = new FlowLayoutManager();
        layoutManager.setScrollEnabled(false);
        lv.setLayoutManager(layoutManager);
//        lv.setLayoutManager(new GridLayoutManager(context, 3));
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = (data.size() / 2 + (data.size()%3 == 0 ? 0 :1)) * DisplayUtil.dip2px(33);
        lv.setLayoutParams(params);   //auto change height, not same as ListView
        foodAdapter = new FoodTypeAdapter(getContext(), data);
        lv.setAdapter(foodAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnReset:
                reset();
                break;
            case R.id.btnConfirm:
                CompanyRequest request = new CompanyRequest();
                boolean[] record = foodAdapter.getRecord();
                List<FoodStyles> foodStyles = new ArrayList<>();
                for (int i = 0; i < record.length; i++) {
                    if (record[i]) foodStyles.add(data.get(i));
                }
                request.setRestaurantType(restaurantType);
                request.setFoodStyles(foodStyles);
                request.setPage(1);
                PreferenceUtil preferenceUtil = new PreferenceUtil(context);
                request.setLongitude(preferenceUtil.getLastLocationLNG());
                request.setLatitude(preferenceUtil.getLastLocationLAT());
                if (onConfirmListener != null) onConfirmListener.onConfirm(request);
                dismiss();
                break;
        }
    }

    View.OnClickListener flatButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            boolean isSelected = ((restaurantType & tag) == tag);
            if (isSelected) {     //选中 -> 原形
                v.setBackgroundColor(context.getResources().getColor(R.color.secondBg));
                changeFlatButtonColor(v, R.color.black);
                restaurantType = restaurantType & ~tag;
            } else {              //原形 -> 选中
                v.setBackgroundColor(context.getResources().getColor(R.color.thirdBg));
                changeFlatButtonColor(v, R.color.colorAccent);
                restaurantType = restaurantType | tag;
            }
        }
    };

    public void reset(){
        restaurantType = 0;
        backToNormal(btnDelivery);
        backToNormal(btnDineIn);
        backToNormal(btnTakeOut);
        foodAdapter = new FoodTypeAdapter(getContext(), data);
        lv.setAdapter(foodAdapter);
    }

    private void backToNormal(View v) {
        v.setBackgroundColor(context.getResources().getColor(R.color.secondBg));
        changeFlatButtonColor(v, R.color.black);
    }

    private static void changeFlatButtonColor(View v, int colorRes) {
        Button btn = (ButtonFlat) v;
        //  通过反射来控制内部TextView
        try {
            Field textButton = btn.getClass().getDeclaredField("textButton");
            textButton.setAccessible(true);
            //Here you can get the textview, then you can do whatever you want
            TextView textView = (TextView) textButton.get(btn);
            textView.setTextColor(CustomerApplication.getInstance().getResources().getColor(colorRes));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
    }

    private View mView;  //参考点

    public void setAnchorView(View mView) {
        this.mView = mView;
    }

    /*
    * lp.x与lp.y表示相对于原始位置的偏移.
    * 当参数值包含Gravity.LEFT时,对话框出现在左边,所以lp.x就表示相对左边的偏移,负值忽略.
    * 当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略.
    * 当参数值包含Gravity.TOP时,对话框出现在上边,所以lp.y就表示相对上边的偏移,负值忽略.
    * 当参数值包含Gravity.BOTTOM时,对话框出现在下边,所以lp.y就表示相对下边的偏移,负值忽略.
    */
    @Override
    public void show() {
        int[] scPosition = new int[2];
        mView.getLocationOnScreen(scPosition);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.START | Gravity.TOP);
        lp.x = mView.getPaddingLeft();
        lp.y = mView.getHeight();                  // 新位置Y坐标
        lp.dimAmount = 0.1f;                   //设置弹出后Activity背景透明度
        dialogWindow.setAttributes(lp);
        super.show();
    }
}
