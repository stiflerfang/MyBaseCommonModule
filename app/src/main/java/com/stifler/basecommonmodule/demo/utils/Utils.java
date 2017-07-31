package com.stifler.basecommonmodule.demo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.R;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static Context mContext;

    private static Toast mToast = null;

    static {
        mContext = DemoApplication.getIntance();
    }

    private static long lastClickTime;

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        Utils.mContext = mContext;
    }



    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showKeyBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void closeKeyBoard(Activity context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 用application
     * context获取string，防止调用fragment.getString()时出现fragment脱离当前activity的情况
     *
     * @param resId
     * @return
     */
    public static String getString(int resId, Object... formatArgs) {
        final Resources resources = mContext.getResources();

        if (resources != null) {
            return resources.getString(resId, formatArgs);
        }
        return "";
    }

    /**
     * 用application
     * context获取string，防止调用fragment.getString()时出现fragment脱离当前activity的情况
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getString(resId);
        }
        return "";
    }

    public static float getDimension(int resId) {
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getDimension(resId);
        }
        return 0F;
    }

    public static Drawable getDrawable(int resId){
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getDrawable(resId);
        }
        return null;
    }

    public static int getColor(int resId){
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getColor(resId);
        }
        return -1;
    }

    public static Resources getResources() {
        return mContext.getResources();
    }


    /**
     * 获取渠道号
     *
     * @return 默认返回“qianbao_android”
     */
    public static String getChannel() {
//        try {
//            ApplicationInfo ai = mContext.getPackageManager()
//                    .getApplicationInfo(mContext.getPackageName(),
//                            PackageManager.GET_META_DATA);
//            Bundle bundle = ai.metaData;
//            if (bundle == null) {
//                return Utils.getString(R.string.str_channel_name);
//            }
//            String channel = bundle.getString("UMENG_CHANNEL");
//            return channel;
//        } catch (NameNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
            return Utils.getString(R.string.str_channel_name);
//        }
    }

    /**
     * 当前应用包名
     *
     * @return 当前应用包名
     */
    public static String getPacketName() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return info.packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "com.qbao.ticket";
        }
    }

    /**
     * 获取apk文件的版本号
     *
     * @return 获取apk文件的版本号
     */
    public static String getVersion(String apkPath) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                    PackageManager.GET_ACTIVITIES);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 判断网络是否可用
     *
     * @return true可用, false不可用
     */
    public static boolean isNetValid() {
        boolean isNetworkConntected = false;
        if (mContext != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();

            if (activeNetworkInfo != null) {
                isNetworkConntected = activeNetworkInfo.isConnected();
            }
        }

        return isNetworkConntected;
    }

    /**
     * 安装更新
     *
     * @param filePath
     */
    public static void installApk(String filePath) {
        File file = new File(filePath);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }


    /**
     * 获取imei号
     *
     * @return
     */
    public static String getImei() {
        String imei = ((TelephonyManager) mContext
                .getSystemService(Activity.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    public static String getVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static int getVersionCode() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    mContext.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getDevId() {
        String devIdStr = "";
        StringBuffer devId = new StringBuffer();
        devId.append(getImei());
        devId.append(";");
        devId.append(getSerialNumber());
        devId.append(";");
        devId.append(getAndroidId());
        devId.append(";");
        if (!TextUtils.isEmpty(devId)) {
            devIdStr = MD5.getHashString(devId.toString());
        }
        return devIdStr;
    }

    public static String getMacAddress() {
        // 获取mac地址：
        String macAddress = "00:00:00:00:00:00";
        try {
            WifiManager wifiMgr = (WifiManager) mContext
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr
                    .getConnectionInfo());
            if (null != info) {
                if (!TextUtils.isEmpty(info.getMacAddress())) {
                    macAddress = info.getMacAddress();
                } else {
                    return macAddress;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return macAddress;
        }
        return macAddress;
    }


    public static boolean isLegelDigitForGrade(String grade) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("\\d{1,2}(\\.[0-9])?");
        flag = pattern.matcher(grade).matches();
        return flag;
    }

    public static String getRMB() {
        return /*((char) 165) + " "*/"￥";
    }

    /**
     * 格式化有效小数
     *
     * @return
     */
    public static String formatDecimals(double value, int digits) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(digits);
        return format.format(value);
    }

    public static String formatRMB(BigDecimal value) {
        DecimalFormat balanceFormat = new DecimalFormat(",##0.00");
        value = value.divide(BigDecimal.valueOf(100));
        return balanceFormat.format(value.doubleValue());
    }

    public static String formatTwoRMB(Double value) {
        value = new java.math.BigDecimal(value + "").setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(value);
        return dd;
    }

    public static String formatRMBWithodDot(BigDecimal value) {
        DecimalFormat balanceFormat = new DecimalFormat(",###");
        value = value.divide(BigDecimal.valueOf(100));
        return balanceFormat.format(value.doubleValue());
    }

    public static String formatQBBi(BigDecimal value) {
        if (BigDecimal.ZERO.equals(value)) {
            return value.toString();
        }
        DecimalFormat balanceFormat = new DecimalFormat(",###");
        return balanceFormat.format(value);
    }

    public static String formatMoney(BigDecimal value) {
        DecimalFormat balanceFormat = new DecimalFormat(",##0.00");
        return balanceFormat.format(value.doubleValue());
    }

    public static String formatAmountWithRMB(long price) {
        return formatAmountWithRMBOrQB(price, false, false);
    }

    public static String formatAmountWithQB(long price) {
        return formatAmountWithRMBOrQB(price, true, false);
    }

    public static String formatAmountWithRMBOrQB(long price, boolean isQB, boolean isNeedSeparate) {
        DecimalFormat decimalFormat = new DecimalFormat(isNeedSeparate ? "###,##0.00" : "#####0.00");
        BigDecimal bigDecimal = new BigDecimal(price);
        if (isQB) {
            bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100));
        }
        String amount = decimalFormat.format(bigDecimal);
        return eliminateUselessZero(amount);
    }

    private static String eliminateUselessZero(String amount) {
        StringBuffer sb = new StringBuffer();
        if (amount.contains(".")) {
            String[] numbers = amount.split("\\.");
            String decimalString = cutZero(numbers[1]);
            sb.append(numbers[0]);
            if (TextUtils.isEmpty(decimalString)) {
            } else {
                sb.append(".").append(decimalString);
            }
        } else {
            sb.append(amount);
        }
        return sb.toString();
    }

    private static String cutZero(String number) {
        boolean needContinue = false;
        String tempNumber = new StringBuffer(number).reverse().toString();
        char[] chars = tempNumber.toCharArray();
        StringBuffer sb = new StringBuffer();
        int length = tempNumber.length();
        for (int i = 0; i < length; i++) {
            if (!String.valueOf(chars[i]).equals("0") || needContinue) {
                sb.append(chars[i]);
                needContinue = true;
            } else {

            }
        }
        return sb.reverse().toString();
    }

    public static String getFloatToString(String str) {
        Float f = Float.parseFloat(str);
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(f);
        return dd;
    }

    public static long rmb2QBBi(String value) {
        return Integer.valueOf(value).intValue() * 100;
    }

    public static long qbb2Rmb(String value) {
        return Integer.valueOf(value).intValue() / 100;
    }

    public static String getLegalString(String str, String defaultString) {
        String legalString = "";
        if (isStringLegal(str)) {
            legalString = str;
        } else {
            if (isStringLegal(defaultString)) {
                legalString = defaultString;
            } else {
                legalString = "";
            }
        }
        return legalString;
    }

    public static boolean isStringLegal(String str) {
        boolean flag = false;
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase("null")) {
            flag = true;
        }
        return flag;
    }


    /**
     * 得到经过加密的密码
     *
     * @return
     */
    public static String getPassword(String name, String password) {
        String pwd_encrypt = getEncryptValue(password, name);
        return pwd_encrypt;
    }

    /**
     * 将content加密
     *
     * @return
     */
    public static String getEncryptValue(String content, String key) {
        return MD5.getHashString(content + "{" + key + "}");
    }

    @SuppressLint("SimpleDateFormat")
    public static int getDateInterval(String startTime, String endTime) {
        long betweenTime = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date dateBegin = sdf.parse(startTime);// 通过日期格式的parse()方法将字符串转换成日期
            Date date = sdf.parse(endTime);
            betweenTime = getDateInterval(date.getTime(), dateBegin.getTime());
        } catch (Exception e) {
        }
        return (int) (betweenTime);
    }

    public static long getDateInterval(long beginDate, long endDate) {
        long dayInterval = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        try {
            String beginDateStr = formatter.format(beginDate);
            Date beginDateWithoutHMS = formatter.parse(beginDateStr);
            String endDateStr = formatter.format(endDate);
            Date endDateWithoutHMS = formatter.parse(endDateStr);
            long betweenTime = beginDateWithoutHMS.getTime() - endDateWithoutHMS.getTime();
            dayInterval = betweenTime / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayInterval;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeAfterInterval(String startTime, String interval) {
        String afterTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date date = sdf.parse(startTime);// 通过日期格式的parse()方法将字符串转换成日期
            long l = date.getTime() + Integer.valueOf(interval) * 1000 * 60;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            afterTime = formatter.format(Long.valueOf(l));
        } catch (Exception e) {
        }
        return afterTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateAfterIntervalInDay(String startTime,
                                                   int interval) {
        String afterTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = sdf.parse(startTime);// 通过日期格式的parse()方法将字符串转换成日期
            long l = date.getTime() + interval * 1000 * 60 * 60 * 24;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            afterTime = formatter.format(Long.valueOf(l));
        } catch (Exception e) {
        }
        return afterTime;
    }

    /**
     * 获取当前时间格式 yyyy-MM-dd HH:mm:ss.SSSZ
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return format.format(System.currentTimeMillis());
    }

    public static boolean compareVersion(String localVersion,
                                         String remoteVersion) {
        if (TextUtils.isEmpty(localVersion) || TextUtils.isEmpty(remoteVersion)) {
            return false;
        }
        String[] local = localVersion.split("\\.");
        String[] remote = remoteVersion.split("\\.");
        int localSize = local.length;
        int remoteSize = remote.length;
        if (remoteSize > localSize) {
            String[] resetLocal = new String[remoteSize];
            for (int i = 0; i < localSize; i++) {
                resetLocal[i] = local[i];
            }
            for (int i = localSize; i < remoteSize; i++) {
                resetLocal[i] = "0";
            }
            local = resetLocal;
        } else if (remoteSize < localSize) {
            String[] resetRemote = new String[localSize];
            for (int i = 0; i < remoteSize; i++) {
                resetRemote[i] = remote[i];
            }
            for (int i = remoteSize; i < localSize; i++) {
                resetRemote[i] = "0";
            }
            remote = resetRemote;
        }
        try {
            for (int i = 0; i < localSize; i++) {
                int remoteInt = Integer.parseInt(remote[i]);
                int localInt = Integer.parseInt(local[i]);
                if (remoteInt < localInt) {
                    return false;
                }
                if (remoteInt > localInt) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isMobileNo(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("1\\d{10}");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean checkPwdValidityNew(String password){
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}$");
            Matcher m = p.matcher(password);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;

    }

    public static String getTextFromEditTextNoTrim(EditText editText) {
        if (editText != null) {
            return editText.getText().toString();
        } else {
            return null;
        }
    }

    public static String calcDistance(double distance) {
        String str = DemoApplication.getIntance().getString(R.string.str_m, "0");
        if (distance >= 0 && distance < 1000) {
            DecimalFormat balanceFormat = new DecimalFormat("##0.0");
            str = DemoApplication.getIntance().getString(R.string.str_m,
                    balanceFormat.format(distance));
        } else if (distance >= 1000) {
            str = DemoApplication.getIntance().getString(R.string.str_km,
                    formatDistance(new BigDecimal(distance)));
        }
        return str;
    }

    public static String formatDistance(BigDecimal value) {
        DecimalFormat balanceFormat = new DecimalFormat("##0.0");
        value = value.divide(BigDecimal.valueOf(1000));
        return balanceFormat.format(value.doubleValue());
    }

    public static int parseStringToInt(String content) {
        int value = 0;
        try {
            value = Integer.parseInt(content);
        } catch (NumberFormatException e) {
            value = 0;
        }
        return value;
    }


    public static String getSign(String content) {
        String pwd_encrypt = MD5.getHashString(content);
        return pwd_encrypt;
    }

    public static String getSign2(String content) {
        final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] bytes = content.getBytes("utf-8");
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] digestBytes = digest.digest(bytes);
            final int nBytes = digestBytes.length;
            char[] result = new char[2 * nBytes];
            int j = 0;
            for (int i = 0; i < nBytes; i++) {
                // Char for top 4 bits
                result[j++] = HEX[(0xF0 & digestBytes[i]) >>> 4];
                // Bottom 4
                result[j++] = HEX[(0x0F & digestBytes[i])];
            }
            String encrupt = new String(result);
            return encrupt;

        } catch (Exception e) {
            return "";
        }

    }

    public static final Pattern NAME_ADDR_EMAIL_PATTERN = Pattern
            .compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    public static boolean isEmailAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        String s = extractAddrSpec(address);
        Matcher match = EMAIL_ADDRESS_PATTERN.matcher(s);
        return match.matches();
    }

    private static String extractAddrSpec(String address) {
        Matcher match = NAME_ADDR_EMAIL_PATTERN.matcher(address);

        if (match.matches()) {
            return match.group(2);
        }
        return address;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTime(int seconds) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        return format.format(seconds * 1000);
    }

    public static String formatNoSTime(long timeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(timeMillis);
    }
    public static String formatTime(long timeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(timeMillis);
    }

    public static String formatDetailTime(long timeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return format.format(timeMillis);
    }
    public static String format_Time(long timeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timeMillis);
    }

    public static boolean isLegitimate(String content_str) {
        if (content_str == null || "".equals(content_str)) {
            return false;
        }
        Pattern p = Pattern.compile("^[\\x00-\\xff&&[^\\x20]]*$");
        Matcher m = p.matcher(content_str);
        boolean flag = m.matches();
        return flag;
    }


    public static String getPhoneOSVersion(){
        return Build.VERSION.RELEASE;
    }

    /**
     * type=0 为宽 type=1为高 length 为效果图的长度
     *
     * @param length
     * @param type
     * @return
     * @Description:根据比例计算长度
     */

    /**
     * 是否是Wifi网络
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        boolean isWifi = false;
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info != null) {
            isWifi = info.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return isWifi;
    }

    /**
     * 是否点击过快
     *
     * @return
     * @author baidonghui
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static String getEncryptionAccount(String account, int prefixAmount, int suffix) {
        StringBuffer newAccount = new StringBuffer();
        String temp = "";

        if (account != null && !"".equals(account)) {
            int middleLength = account.length() - prefixAmount - suffix;
            int i = 0;
            int j = 0;
            while (i < prefixAmount && !account.equals("")) {
                newAccount.append(account.substring(0, 1));
                i++;
                account = account.substring(1);
            }
            if (middleLength > 0) {
                for (int k = 0; k < middleLength; k++) {
                    newAccount.append("*");
                }
            }
            while (j < suffix && !account.equals("")) {
                temp = account.substring(account.length() - 1, account.length()) + temp;
                j++;
                account = account.substring(0, account.length() - 1);
            }
            newAccount.append(temp);
        } else {
            newAccount.append("******");
        }
        return newAccount.toString();
    }

    /**
     * 设置不改变padding的背景
     * 因为android有设置背景后，padding无效的问题
     *
     * @param view
     * @param resId
     * @autor wujiajun
     */
    public static void setBackgroundResourceWithPadding(View view, int resId) {
        int bottom = view.getPaddingBottom();
        int top = view.getPaddingTop();
        int right = view.getPaddingRight();
        int left = view.getPaddingLeft();
        view.setBackgroundResource(resId);
        view.setPadding(left, top, right, bottom);
    }

    /**
     * 计算地图经纬度之间的距离
     *
     * @param latA
     * @param lngA
     * @param latB
     * @param lngB
     * @return 距离 单位：米
     * @autor wujiajun
     */
    public static float calculateMapDistance(double latA, double lngA, double latB, double lngB) {
        Location locationA = new Location("point A");
        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);
        Location locationB = new Location("point B");
        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);
        return locationA.distanceTo(locationB);
    }



    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(long birthday) {
        long curTime = System.currentTimeMillis();
        Calendar birCalendar = Calendar.getInstance();
        Calendar curCalendar = Calendar.getInstance();
        birCalendar.setTimeInMillis(birthday);
        curCalendar.setTimeInMillis(curTime);
        int age = 0;
        age = curCalendar.get(Calendar.YEAR) - birCalendar.get(Calendar.YEAR);
        if (curCalendar.get(Calendar.MONTH) < birCalendar.get(Calendar.MONTH)) {
            age -= 1;
        } else if (curCalendar.get(Calendar.MONTH) == birCalendar.get(Calendar.MONTH)) {
            if (curCalendar.get(Calendar.DAY_OF_MONTH) < birCalendar.get(Calendar.DAY_OF_MONTH)) {
                age -= 1;
            }
        }
        if (age < 0) {
            age = 0;
        }
        return age;
    }

    /**
     * 根据时间返回日历
     *
     * @param time
     * @return
     */
    public static Calendar getCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    /**
     * 设置景点日期显示
     *
     * @param tv
     * @param timeMillis
     */
    public static void setDateDisplay(TextView tv, long timeMillis, String color) {
        String[] msg = new String[2];
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("M月dd日");

        Calendar curCalendar = Calendar.getInstance();
        Calendar tomorrowCalendar = Calendar.getInstance();
        Calendar displayCalendar = Calendar.getInstance();
        curCalendar.setTimeInMillis(System.currentTimeMillis());
        tomorrowCalendar.setTimeInMillis(curCalendar.getTimeInMillis());
        tomorrowCalendar.add(Calendar.DAY_OF_MONTH, 1);
        displayCalendar.setTimeInMillis(timeMillis);
        int curYear = curCalendar.get(Calendar.YEAR);
        int tomorrowYear = tomorrowCalendar.get(Calendar.YEAR);
        int displayYear = displayCalendar.get(Calendar.YEAR);
        int curMonth = curCalendar.get(Calendar.MONTH);
        int tomorrowMonth = tomorrowCalendar.get(Calendar.MONTH);
        int displayMonth = displayCalendar.get(Calendar.MONTH);
        int curDay = curCalendar.get(Calendar.DAY_OF_MONTH);
        int tomorrowDay = tomorrowCalendar.get(Calendar.DAY_OF_MONTH);
        int displayDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
        if (curYear != displayYear) {
            msg[0] = displayYear + "年\n";
            msg[1] = format.format(timeMillis);
        } else if (curYear == displayYear && curMonth == displayMonth && curDay == displayDay) {
            msg[0] = "今天\n";
            msg[1] = format.format(timeMillis);
        } else if (tomorrowYear == displayYear && tomorrowMonth == displayMonth && tomorrowDay == displayDay) {
            msg[0] = "明天\n";
            msg[1] = format.format(timeMillis);
        } else {
            msg[0] = "";
            format.applyPattern("M月dd日");
            msg[1] = format.format(timeMillis);
        }

//        ViewInitHelper.initTextViewWithSpannableString(tv, msg, new String[]{color, color}, new String[]{"15", "11"});
    }

    public static String getDate(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy.MM.dd");
        String d1 = format.format(milliseconds);
        return d1 + " " + getWeekDay(milliseconds);
    }

    public static String getSimpleDate(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy.MM.dd");
        return format.format(milliseconds);
    }

    public static String getWeekDay(long milliseconds) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
        if (Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周一";
        }
        if (Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周二";
        }
        if (Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周三";
        }
        if (Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周四";
        }
        if (Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周五";
        }
        if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周六";
        }
        if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周日";
        }
        return "周一";
    }

    public static boolean isIdCard(String text) {
        String regx = "[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[\\d|X|x]";
        return text.matches(regx);
    }

    /**
     * 测试当前摄像头能否被使用
     * [url=home.php?mod=space&uid=309376]@return[/url]
     */

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
//                if (DEBUG)
//                    DatabaseUtils.dumpCursor(cursor);

                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public static String getDeviceTypeName() {
        return android.os.Build.MODEL;
    }

    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getDeviceIP() {
        String ip = "";
        WifiManager wifiManager = (WifiManager) DemoApplication.getIntance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            ip = inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (SocketException ex) {
                ex.printStackTrace();
            }
        } else {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            ip = intToIp(ipAddress);
        }
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static String getSerialNumber() {
        return Build.SERIAL;
    }

    public static String getAndroidId() {
        return android.provider.Settings.Secure.getString(
                DemoApplication.getIntance().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
    }

    public static String formatAlpha(float alpha) {
        if (alpha <= 0) {
            alpha = 0;
        }
        String value = "ff";
        value = Integer.toHexString((int) ((1 - alpha) * 256));
        if (value.length() == 1) {
            value = "0" + value;
        }
        return value;
    }

    /**
     * 是否打开系统GPS
     * @return true 开启
     */
    public static boolean isOpenAndroidLocation() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }



    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
