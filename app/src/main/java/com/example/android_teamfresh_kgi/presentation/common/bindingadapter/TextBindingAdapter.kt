package com.example.android_teamfresh_kgi.presentation.common.bindingadapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android_teamfresh_kgi.R
import java.text.DecimalFormat
import kotlin.math.roundToInt

@BindingAdapter("priceAmount")
fun applyPriceFormat(view: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")// 3자리 마다 콤마
    view.text = view.context.getString(R.string.unit_discount_currency, decimalFormat.format(price))
}


@BindingAdapter("priceAmount", "strikeThrough")
fun applyPriceAndStrikeStyle(view: TextView, price: Int, strikeThrough: Boolean) {
    applyPriceFormat(view, price)
    if(strikeThrough) {
        view.textSize = 10F
        view.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}


@BindingAdapter("dcPrice","price") // 판매가격
fun applySelPrice(view: TextView, dcPrice: Int, price: Int) { // 판매가격 view
    if(price == dcPrice) {
        applyPriceFormat(view, price)
        // 복원
        view.paintFlags = Paint.LINEAR_TEXT_FLAG
        view.textSize = 13F
        view.setTextColor(ColorStateList.valueOf(Color.BLACK))
    } else {
        view.setTextColor(view.context.getColor(R.color.text_gray))
        applyPriceAndStrikeStyle(view, price, true)
    }
}

@BindingAdapter("drPrice","drDcPrice")  // 할인율 계산
fun applyDiscountRate(view: TextView, price: Int, dcPrice: Int) { // 할인율 view
    if(price == dcPrice) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
        val discountRate =  (((price - dcPrice) / price.toDouble()) * 100).roundToInt()
        view.text = view.context.getString(R.string.unit_discount_rate, discountRate)
    }
}


@BindingAdapter("dcPrice", "dcDcPrice") // 할인된 가격 View
fun applyDcPrice(view: TextView, price: Int, dcPrice: Int) {
    if(price != dcPrice) {
        applyPriceFormat(view,dcPrice)
        view.visibility = View.VISIBLE

    }else{
        view.visibility = View.GONE
    }
}


@BindingAdapter("option")
fun applyOption(view: TextView, option: String?) {
    if(option.equals("Y") && option != null) {
        view.text = option
        //복원
        view.visibility = View.VISIBLE
    }

}
@BindingAdapter("optionValue")
fun applyOptionValue(view: TextView, optionValue: String?) {
    if(optionValue != null) {
        view.text = optionValue
        // 복원
        view.visibility = View.VISIBLE
    }else{
        view.text = ""
        view.visibility = View.GONE
    }

}
