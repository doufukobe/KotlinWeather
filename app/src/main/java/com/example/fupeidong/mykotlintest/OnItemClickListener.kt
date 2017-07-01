package com.example.fupeidong.mykotlintest

import android.view.View
import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/6/25.
 */
public interface OnItemClickListener {
    operator fun invoke(forecase: Domain.Forecast)
}