package com.stifler.basecommonmodule.demo.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.stifler.basecommonmodule.demo.R;


public class ViewInitHelper {


    public static SpannableString getPriceInCinemaList(String str) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(Utils.getResources().getColor(R.color.color_ff0000));
        spanString.setSpan(span, 4, spanString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

//	public static String getMoiveType(String filmType) {
//		String type = null;
//		try {
//			switch (Integer.valueOf(filmType)) {
//			case 0:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_2d);
//				break;
//			case 1:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_3d);
//				break;
//			case 2:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_2d_imax);
//				break;
//			case 3:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_3d_imax);
//				break;
//			case 4:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_4D);
//				break;
//			case 6:
//				type = QBaoApplication.getApplication().getString(
//						R.string.str_Dmax);
//				break;
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return type;
//	}

    /**
     * 将指定的textview润色赋值
     *
     * @param tv
     * @param regionalStrings 要赋值的字符串数组
     * @param colors          对应的颜色数组
     * @param sizes           对应的字号数组
     */
    public static void initTextViewWithSpannableString(TextView tv,
                                                       String[] regionalStrings, String[] colors, String[] sizes) {
        tv.setText("");
        SpannableString[] spannableStrings = ViewInitHelper.getSpannableString(
                regionalStrings, colors, sizes);
        if (null == spannableStrings) {
            return;
        }
        for (SpannableString ss : spannableStrings) {
            tv.append(ss);
        }
    }

    /**
     * 获取SpannableString 注意：暂时未做边界检查，使用时请自行检查边界
     *
     * @param regionalStrings
     * @param colors
     * @return
     * @author baidonghui
     */
    public static SpannableString[] getSpannableString(
            String[] regionalStrings, String[] colors, String[] sizes) {
        if (regionalStrings.length == 0
                || sizes.length != regionalStrings.length
                || colors.length != regionalStrings.length)
            return null;

        SpannableString[] spanStrings = new SpannableString[regionalStrings.length];

        for (int i = 0; i < regionalStrings.length; i++) {
            spanStrings[i] = new SpannableString(regionalStrings[i]);
            ForegroundColorSpan fColorSpan = new ForegroundColorSpan(
                    Integer.valueOf(colors[i]));
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(
                    (int) (DisplayMetricsUtils.getDensity() * Integer
                            .valueOf(sizes[i])));
            spanStrings[i].setSpan(fColorSpan, 0, spanStrings[i].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanStrings[i].setSpan(sizeSpan, 0, spanStrings[i].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spanStrings;
    }

    public static SpannableString getName(String cinemaName, String movie,
                                          int size1, int size2) {
        if (TextUtils.isEmpty(cinemaName)) {
            cinemaName = " ";
        }
        if (TextUtils.isEmpty(movie)) {
            movie = " ";
        }
        SpannableString spanString = new SpannableString(cinemaName + " "
                + movie);
        AbsoluteSizeSpan spanSzieLeft = new AbsoluteSizeSpan(
                (int) (size1 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieLeft, 0, cinemaName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan spanSzieMiddle = new AbsoluteSizeSpan(
                (int) (size2 * DisplayMetricsUtils.getDensity()));
        spanString.setSpan(spanSzieMiddle, cinemaName.length() + 1,
                spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }



    public static int getIntFromString(String str, int defaultValue) {
        int value = defaultValue;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            try {
                value = Integer.valueOf(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static String getConcertPriceArea(String priceString) {
        String priceArea = "0";
        String previousPrice = "";
        if (!TextUtils.isEmpty(priceString)) {
            if (priceString.contains("~")) {
                StringBuffer sb = new StringBuffer();
                String[] prices = priceString.split("~");
                for (String price : prices) {
                    try {
                        if (!previousPrice.equals(price)) {
                            sb.append(Utils.formatAmountWithQB(Integer.valueOf(price))).append("~");
                            previousPrice = price;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                priceArea = sb.toString();

            } else {
                priceArea = Utils.formatAmountWithQB(Integer.valueOf(priceString));
            }
        }
        return Utils.getString(R.string.str_yuan, priceArea);
    }


}
