package com.exam.upraxis.module.adapter

import androidx.recyclerview.widget.DiffUtil
import com.exam.upraxis.R
import com.exam.upraxis.common.base.SimpleListAdapter
import com.exam.upraxis.databinding.ViewholderItemUserBinding
import com.exam.upraxis.domain.model.User

class UserListItemAdapter(
  private val onClickItem: ((item: User) -> Unit)
) : SimpleListAdapter<ViewholderItemUserBinding, User>(
  R.layout.viewholder_item_user,
    UserAdapterDiffCallback
) {

  override fun bind(
    holder: ViewHolder<ViewholderItemUserBinding>,
    item: User,
    position: Int
  ) {
    val context = holder.itemView.context
    holder.itemView.setOnClickListener {
      onClickItem.invoke(item)
    }
    holder.binding.apply {
      user = item
    }
  }
}

object UserAdapterDiffCallback : DiffUtil.ItemCallback<User>() {

  override fun areItemsTheSame(
    oldItem: User,
    newItem: User
  ): Boolean = oldItem.username == newItem.username

  override fun areContentsTheSame(
    oldItem: User,
    newItem: User
  ): Boolean = oldItem == newItem
}