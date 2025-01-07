package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.model.User
import java.util.Collections

class UserListAdapter(private val callback: Listener) : RecyclerView.Adapter<ListUserViewHolder>() {
    private var users: List<User> = listOf()

    fun updateList(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    fun getUserAtPosition(position: Int): User {
        return users[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return ListUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(users, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(users, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    interface Listener {
        fun onClickDelete(user: User)
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }
}