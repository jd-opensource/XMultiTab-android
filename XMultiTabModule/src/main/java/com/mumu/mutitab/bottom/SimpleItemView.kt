package com.mumu.mutitab.bottom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.ImageView

/**
 * @author libinbin
 * @description:
 * @date :2022/9/13 2:30 下午
 */

@SuppressLint("AppCompatCustomView")
class SimpleItemView
@JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr), Checkable {

    private var checked = false

    private var itemData: ITabItem? = null

    var displayImage: IDisplayImage? = null

    fun setData(item: ITabItem) {
        this.itemData = item
    }

    override fun setChecked(checked: Boolean) {
        this.checked = checked
        itemData?.let { data ->
            displayImage?.displayImage(
                if (checked) data.getSelectImageUrl() else data.getUnSelectImageUrl(),
                this
            )
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {

    }
}