package com.example.seemystore

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seemystore.activities.STORE_ID_EXTRA
import com.example.seemystore.activities.StoreActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem_store_details.view.*
import java.util.ArrayList

class StoreListAdapter() :
        RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {

    private val mStoreList: MutableList<Store> = ArrayList()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var store: Store? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            val showStoreIntent = Intent(context, StoreActivity::class.java)
            showStoreIntent.putExtra(STORE_ID_EXTRA, store?.storeID)

            context.startActivity(showStoreIntent)
        }

        fun bindStore(store: Store) {
            this.store = store
            view.tv_name.text = store.name
            view.tv_phone.text = store.phone
            Picasso.get()
                    .load(store.storeLogoURL)
                    .into(view.img_store)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_store_details, parent, false)
        return (ViewHolder(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemStore = getItemAtPosition(position)
        holder.bindStore(itemStore)
    }

    override
    fun getItemCount() = mStoreList.size

    fun getItemAtPosition(position: Int): Store {
        return mStoreList[position]
    }

    fun clear() {
        val size = mStoreList.size
        if (size <= 0) {
            return
        }
        for (i in 0 until size) {
            mStoreList.removeAt(0)
        }
        mStoreList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun addAll(storeList: List<Store>) {
        for (position in storeList.indices) {
            addItem(storeList.get(position), position)
        }
    }

    fun addItem(store: Store, position: Int) {
        mStoreList.add(position, store)
        notifyItemInserted(position)
    }

    fun add(store: Store) {
        mStoreList.add(store)
    }
}