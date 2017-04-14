/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.util.money;

import java.math.BigDecimal;

/**
 * BigDecimal.valueOf(amount)或者  new BigDecimal("amout"); 不会精度丢失
 */
public class MathUtil {
    private static final int SCALE_TAX = 5;

    public static boolean equals(double amount1, double amount2, double eps) {
        if (amount1 == amount2)
            return true;
        return Math.abs(amount1 - amount2) < eps;
    }

    public static double roundEual(double amount, int num) {
        return BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(num), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundTax(double amount, double subTotal, double total) {
        return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(subTotal)).divide(BigDecimal.valueOf(total), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundTax(double amount, double taxRate, boolean isIncludeTax) {
        if (isIncludeTax) {
            return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(taxRate)).divide(BigDecimal.valueOf(taxRate + 100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(taxRate)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

/*	public static double roundServiceFee(double subTotal, double discountAmt, double taxAmt, double serviceFee , boolean isIncludeTax, boolean isServiceAfterTax) {
        if (isIncludeTax || !isServiceAfterTax) {
			return BigDecimal.valueOf(subTotal).subtract(BigDecimal.valueOf(discountAmt)).multiply(BigDecimal.valueOf(serviceFee)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
		} else {
			return BigDecimal.valueOf(subTotal).subtract(BigDecimal.valueOf(discountAmt)).add(BigDecimal.valueOf(taxAmt)).multiply(BigDecimal.valueOf(serviceFee)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}*/

    public static double roundServiceFee(double subTotal, double taxAmt, double servicePercentage, boolean isIncludeTax, boolean isServiceAfterTax) {
        if (isIncludeTax || !isServiceAfterTax) {
            return BigDecimal.valueOf(subTotal).multiply(BigDecimal.valueOf(servicePercentage)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return BigDecimal.valueOf(subTotal).add(BigDecimal.valueOf(taxAmt)).multiply(BigDecimal.valueOf(servicePercentage)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }


    public static double roundDiscount(double subTotal, double taxAmt, double percent, boolean isIncludeTax, boolean isDiscountAfterTax) {
        if (isIncludeTax || isDiscountAfterTax) {
            return BigDecimal.valueOf(subTotal).multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return BigDecimal.valueOf(subTotal).add(BigDecimal.valueOf(taxAmt)).multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static double roundAmount(double price, double percentage) {
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundTotal(double subTotal, double taxAmt, double serviceFee, double deliveryFee, double gratuity, double discountAmt, boolean isIncludeTax) {
        if (isIncludeTax) {
            return BigDecimal.valueOf(subTotal).subtract(BigDecimal.valueOf(discountAmt)).add(BigDecimal.valueOf(serviceFee)).add(BigDecimal.valueOf(deliveryFee)).add(BigDecimal.valueOf(gratuity)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return BigDecimal.valueOf(subTotal).subtract(BigDecimal.valueOf(discountAmt)).add(BigDecimal.valueOf(serviceFee)).add(BigDecimal.valueOf(deliveryFee)).add(BigDecimal.valueOf(gratuity)).add(BigDecimal.valueOf(taxAmt)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static double roundChange(double pay, double paid, double due) {
        return BigDecimal.valueOf(pay).add(BigDecimal.valueOf(paid)).subtract(BigDecimal.valueOf(due)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundSubtract(double amount2, double amount1) {
        return BigDecimal.valueOf(amount2).subtract(BigDecimal.valueOf(amount1)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundPercentageFive(double amount, double totalAmount) {
        return roundPercentage(amount, totalAmount);
    }

    public static double roundPercentage(double amount, double totalAmount) {
        if (totalAmount == 0) {
            return 0;
        } else {
            return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalAmount), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static double roundPercentageIncl(double amount, double totalAmount) {
        if (totalAmount == 0) {
            return 0;
        } else {
            return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(totalAmount).add(BigDecimal.valueOf(amount)), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static double roundAmount(double amount, int percentage) {
        return BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100), SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double roundCashExpected(double startCash, double paidIn, double paidOut, double cashOrders) {
        return BigDecimal.valueOf(startCash).add(BigDecimal.valueOf(paidIn)).add(BigDecimal.valueOf(cashOrders)).subtract(BigDecimal.valueOf(paidOut)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double subtract(double amount1, double amount2) {
        return BigDecimal.valueOf(amount1).subtract(BigDecimal.valueOf(amount2)).setScale(SCALE_TAX, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    //四捨五入接近5
    public static double rounding(double i, double v) {
        return Math.round(i / v) * v;
    }

    //四捨五入不僅為
    public static double roundingFloor(double i, double v) {
        return Math.floor(i / v) * v;
    }

    //四捨五入僅為
    public static double roundingCeil(double i, double v) {
        return Math.ceil(i / v) * v;
    }

    private static final double EARTH_RADIUS = 6378137; //赤道半径(单位m)

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于余弦定理求两经纬度距离
     * http://www.cnblogs.com/zhoug2020/p/3950933.html
     * http://blog.csdn.net/sanyuesan0000/article/details/51683464
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位km
     */
    public static double earthDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);
        if (radLat1 < 0) radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0) radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0) radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0) radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0) radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0) radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);
        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        return theta * EARTH_RADIUS;
    }
}
