package base.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    /**
     * @param parent            ViewGroup
     * @param bindingProvider   创建视图绑定的函数(ViewBinding::inflate)
     */
    constructor(
        parent: ViewGroup,
        bindingProvider: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    ) : this(bindingProvider(LayoutInflater.from(parent.context), parent, false))
}