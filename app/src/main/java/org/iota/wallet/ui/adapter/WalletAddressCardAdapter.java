/*
 * Copyright (C) 2017 IOTA Foundation
 *
 * Authors: pinpong, adrianziser, saschan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.iota.wallet.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.iota.wallet.R;
import org.iota.wallet.ui.dialog.WalletAddressesItemDialog;

import java.util.List;

public class WalletAddressCardAdapter extends RecyclerView.Adapter<WalletAddressCardAdapter.ViewHolder> {

    private List<String> addresses;
    private final Context context;

    public WalletAddressCardAdapter(Context context, List<String> listItems) {
        this.context = context;
        this.addresses = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_wallet_address, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String transfer = getItem(position - 1);

        holder.setIsRecyclable(false);

        holder.addressLabel.setText(transfer);
    }

    private String getItem(int position) {
        return addresses.get(position + 1);
    }

    public void setAdapterList(List<String> transfers) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        final TextView addressLabel;

        private ViewHolder(View itemView) {
            super(itemView);

            addressLabel = itemView.findViewById(R.id.item_wa_address);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(final View v) {

            Bundle bundle = new Bundle();
            bundle.putString("address", addressLabel.getText().toString());

            WalletAddressesItemDialog dialog = new WalletAddressesItemDialog();
            dialog.setArguments(bundle);
            dialog.show(((Activity) context).getFragmentManager(), null);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Toast.makeText(v.getContext(), context.getString(R.string.messages_not_yet_implemented), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
