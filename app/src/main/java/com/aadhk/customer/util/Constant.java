package com.aadhk.customer.util;


public class Constant {

//    public final static String URL = "http://47.90.44.164:8080";

//    public final static String URL = "http://47.91.143.157:80";
    public final static String URL = "http://192.168.1.119:8080";
    public final static String BASE_URL = URL + "/api/";
    public final static String MC_PAYMENT_URL = "https://maptest.mcpayment.net/api/MasterPassAPI/";

    //hockeyapp
    public static final String HOCKEYAPP_APPID = "d86aa0d212184d70b6bfd3ec243afe3f";

    //    public final static boolean DEVELOPER_MODE = true;
    public static final boolean TEST_MODEL = false;

    //餐厅支持点餐类型
    public final static short TYPE_NONE = -1;
    public final static short TYPE_DELIVERY = 1;
    public final static short TYPE_TAKEOUT = 2;
    public final static short TYPE_DINEIN = 3;

    //餐厅点餐类型值
    public final static short TYPE_DELIVERY_VALUE = 1;
    public final static short TYPE_DINEIN_VALUE = 2;
    public final static short TYPE_TAKEOUT_VALUE = 4;

    //TABLE ID

    public static final int TABLE_ID_TAKEOUT = 0;
    public static final int TABLE_ID_DELIVERY = -1;
    public static final int TABLE_ID_PICK_UP = -2;

    //和POS相关  订单类型
    public static final int ORDER_TYPE_DINEIN_CUSTOMER = 4;
    public static final int ORDER_TYPE_TAKEOUT_CUSTOMER = 5;
    public static final int ORDER_TYPE_DELIVERY_CUSTOMER = 6;

    //delivery status
    public static final int DELIVERY_STATUS_NO = 0;
    public static final int DELIVERY_STATUS_ING = 1;
    public static final int DELIVERY_STATUS_FINISH = 2;

    //customer app order status
    //1. 待接单  2. 已接单  3. 拒绝接单  4. 待退款  5. 已退款   6. 拒绝退款  7. 自动取消订单   8. 已完成
    public static final short ORDER_STATUS_REQUEST = 1;
    public static final short ORDER_STATUS_HANDLING = 2;
    public static final short ORDER_STATUS_REJECTED = 3;
    public static final short ORDER_STATUS_REFUNDING = 4;
    public static final short ORDER_STATUS_REFUNDED = 5;
    public static final short ORDER_STATUS_REFUND_REJECTED = 6;
    public static final short ORDER_STATUS_CANCEL = 7;
    public static final short ORDER_STATUS_FINISH = 8;


    public static final int STATUS_ORDER_PRE = -1;//预下单
    public static final int STATUS_ORDER_NEW = 0;
    public static final int STATUS_ORDER_PAY = 1;
    public static final int STATUS_ORDER_VOID = 2;
    public static final int STATUS_ORDER_MOVE = 3;
    public static final int STATUS_ORDER_REFUND = 4;
    public static final int STATUS_ORDER_PAY_LATER = 5;
    public static final int STATUS_ORDER_PAY_UNCLOSE = 6;

    //提现状态
    public static final int STATUS_WITHRRAW_HANDLEING = 0;
    public static final int STATUS_WITHRRAW_FINISHED = 1;
    public static final int STATUS_WITHRRAW_REJECTED = 2;

    public final static short FLAG_UNLOGIN = 0;
    public final static short FLAG_LOGIN_NO_CONNECT_MASTERPASS = 1;
    public final static short FLAG_LOGIN_COMPLETED = 2;

    public final static String BUNDLE_FLAG = "bundle_login_flag";
    public final static String BUNDLE_TAKR_ORDER = "bundle_take_order";
    public final static String BUNDLE_TAKR_ORDER_COMPANY_ID = "bundle_take_order_company_id";
    public final static String BUNDLE_TAKR_ORDER_TABLE_ID = "bundle_take_order_table_id";
    public final static String BUNDLE_TAKR_ORDER_TABLE_NAME = "bundle_take_order_table_name";
    public final static String BUNDLE_SEARCH_RESULT = "bundle_search_result";
    public final static String BUNDLE_SEARCH_KEYWORD = "bundle_search_keyword";
    public final static String BUNDLE_FINISH_ORDER = "bundle_finish_order";
    public final static String BUNDLE_FINISH_COMPANY = "bundle_finish_company";
    public final static String BUNDLE_FINISH_ORDER_TYPE = "bundle_finish_order_type";
    public final static String BUNDLE_QR_CODE = "bundle_qr_code";
    public final static String BUNDLE_LOCATION = "bundle_location";
    public final static String BUNDLE_LOCATION_TYPE = "bundle_location_type";
    public final static String BUNDLE_LOCATION_VALUE = "bundle_location_value";
    public final static String BUNDLE_LOCATION_LONGITUDE = "bundle_location_longitude";
    public final static String BUNDLE_LOCATION_LATITUDE = "bundle_location_latitude";
    public final static String BUNDLE_LOCATION_MAX_DISTANCE = "bundle_location_max_distance";
    public final static String BUNDLE_DETAIL_ORDER = "bundle_delete_order";
    public final static String BUNDLE_AMOUNT_OPERATE_ORDER = "bundle_amount_operate_order";
    public final static String BUNDLE_PAYMENT_ORDER = "bundle_payment_order";

    public final static short LOCATION_TYPE_EDIT = 0;
    public final static short LOCATION_TYPE_PICKER = 1;
    public final static short LOCATION_TYPE_LOCATION = 2;

    public final static int REQUEST_QR_CODE = 10010;
    public final static int REQUEST_ADDRESS = 10011;
    public final static int REQUEST_LOGIN = 10012;
    public final static int REQUEST_GPS = 10013;
    public final static int REQUEST_COE_SEARCH = 10014;
    public final static int REQUEST_MC_PAYMENT = 10015;

    public final static short OPERATION_ADD = 1;
    public final static short OPERATION_UPDATE = 2;
    public final static short OPERATION_DELETE = 3;

    public final static short LABEL_NO = 0;
    public final static short LABEL_HOME = 1;
    public final static short LABEL_SCHOOL = 2;
    public final static short LABEL_COMPANY = 3;

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    public static final String PREF_USER_ID = "prefUserId";
    public static final String PREF_USER_NAME = "prefUserName";
    public static final String PREF_USER_PASSWORD = "prefUserPassword";
    public static final String PREF_USER_EMAIL = "prefUserEmail";
    public static final String PREF_USER_TELEPHONE = "prefUserTelephone";
    public static final String PREF_USER_ADDRESS_1 = "prefUserAddress1";
    public static final String PREF_USER_ADDRESS_2 = "prefUserAddress2";
    public static final String PREF_USER_ADDRESS_3 = "prefUserAddress3";

    public static final String PREF_SETTING_CURRENCY = "prefSettingCurrency";
    public static final String PREF_SETTING_CURRENCY_POSITION = "prefSettingCurrencyPosition";
    public static final String PREF_SETTING_DECIMAL_PLACE = "prefSettingDecimalPlace";
    public static final String PREF_SETTING_WITHDRAW_INTERVAL = "prefSettingWithDrawInterval";
    public static final String PREF_SETTING_WITHDRAW_MINIMUM = "prefSettingWithDrawMinimum";

    public static final short SEARCH_TYPE_HOT = 0;
    public static final short SEARCH_TYPE_HISTROY = 1;

    public final static String PREF_DEF_DATE = "yyyy-MM-dd";
    public final static String PREF_DEF_DATE_TIME = "yyyy-MM-dd HH:mm";
    public final static String PREF_DEF_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public final static String PREF_DEF_DATE_YYYYMMDD = "yyyyMMdd";
    public final static String PREF_DEF_MONTH_DAY_YEAR = "MMM d, yyyy";
    public final static String PREF_DEF_MONTH_YEAR = "MMM yyyy";
    public final static String PREF_DEF_MONTH_DAY = "MMM d";
    public final static String PREF_DEF_YEAR = "yyyy";
    public final static String FILE_DATE_FORMAT = "yyyy_MM_dd";
    public final static String DATE_FORMAT_NO_YEAR = "MM-dd";
    public final static String TIME_FORMAT_24 = "HH:mm";
    public final static String TIME_FORMAT_12 = "hh:mm a";
    public final static String TIME_FORMAT_NO_YEAR = "MM-dd HH:mm";
    public final static String TIME_FORMAT_24_SECOND = "HH:mm:ss";
    public final static String TIME_FORMAT_SECOND = "hh:mm:ss a";
    public final static String TIME_FORMAT = "HH:mm a";
    public final static String TIME_FORMAT_LOGIN = "MMM d. EEE";
    public final static String TIME_FORMAT_TAKE_OUT = "MMM dd hh:mm a";

    public final static String TIME_FORMAT_PUNCH = "yyyy-MM-dd hh:mm a";

    public static final int TYPE_FROMDATE = 1;
    public static final int TYPE_FROMTIME = 1;
    public static final int TYPE_TODATE = 2;
    public static final int TYPE_TOTIME = 2;

    /**
     * 搜索经纬度范围 数据来源 http://www.gpsspg.com/maps.htm
     * center   		 22.5437818091,114.0529337461
     * left-center 	 22.5411813692,113.9980165234
     * top-center	 	 22.5753947819,114.0468853422
     * radius-lat = 22.5437818091 - 22.5411813692    =  0.0026004399 = 0.003
     * radius-lon = 114.0468853422 - 114.0529337461  =  0.0060484039 = 0.006
     */
    public final static double RADIUS_LATITUDE = 0.003;
    public final static double RADIUS_LONGITUDE = 0.006;

    public static final int MAX_DISTANCE = 500000;  // 50km

    public static final String PREF_LAST_LOCATION_LNG = "pref_last_location_lng";
    public static final String PREF_LAST_LOCATION_LAT = "pref_last_location_lat";

    /**
     * 1=根据餐厅关键词搜索餐厅数据
     * 2=根据菜式风格关键词搜索菜式风格
     * 4=根据菜式风格关键词搜索餐厅数据
     * 8=根据关键词搜索餐厅名称和菜式风格
     * <p>
     * Type=3时， 为下图中搜索，type=1 & 2 = 3, 优先搜索餐厅数据，再搜索菜式风格，比例为2:1,按照maxCount来算详细个数，默认为10:5;
     * Type=4时，为下图点击菜式风格结果时，根据菜式风格搜索餐厅，type=4;
     * Type=8时，为个人/热点搜索，或者下图的搜索按钮，既搜索餐厅名称又搜索菜式名称
     */
    public final static short SEARCH_C_BY_CNAME = 1;
    public final static short SEARCH_FT_BY_FTNAME = 2;
    public final static short SEARCH_C_BY_FTNAME = 4;
    public final static short SEARCH_C_BY_CNAME_AND_FTNAME = 8;

    public final static String RXBUS_LOGIN_ACTION = "rxbus_login";
    public final static String RXBUS_FINISH_ORDER_QR_SCAN = "rxbus_finish_order_qr_scan";
    public final static String RXBUS_GOT_LOCATION_SUCCEED = "rxbus_got_location_succeed";
    public final static String RXBUS_GOT_LOCATION_FAIL = "rxbus_got_location_fail";
    public final static String RXBUS_REQUEST_LOCATION = "rxbus_request_location";
    public final static String RXBUS_FRESH_CAR_SHOP_DATA = "rxbus_fresh_car_shop_data";
    public final static String RXBUS_FRESH_ORDER_LIST = "rxbus_fresh_order_list";


    public static final short MAXCOUNT = 20;
    public static final short SEARCH_MAXCOUNT = 15;

    public static final int TYPE_VIEW = 0;
    public static final int TYPE_DELETE = 1;
    public static final int TYPE_REFUND = 2;
    public static final int TYPE_CANCEL = 3;


    //about money    very very important
    public static final int DISCOUNT_TYPE_NORMAL = 0;
    public static final int DISCOUNT_TYPE_CUSTOMER = 1;
    public static final int DISCOUNT_TYPE_PROMOTION = 2;
    public static final int DISCOUNT_TYPE_USER_MODIFY = 3;

    public static final int PROMOTION_DISCOUNT_TYPE_AMOUNT = 0;
    public static final int PROMOTION_DISCOUNT_TYPE_PERCENAGE = 1;
    public static final int PROMOTION_DISCOUNT_TYPE_FIXED_PRICE = 2;

    public static final int PromotionActivity_PROMOTION_DISCOUNT_PRICE = 1; //时段售价
    public static final int PromotionActivity_PROMOTION_DISCOUNT_QUANTITY = 2; //买2送1
    public static final int PromotionActivity_PROMOTION_DISCOUNT_COMBINATION = 3; //套餐


    // for tax
    public static final int TYPE_TAX = 1;
    public static final int TYPE_TAKEOUT_TAX = 2;

    public static final int ALL_TAXES = 0;
    public static final int TAX_ID_1 = 1;
    public static final int TAX_ID_2 = 2;
    public static final int TAX_ID_3 = 3;
    public static final int NO_TAX = 4;

    public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";

}


