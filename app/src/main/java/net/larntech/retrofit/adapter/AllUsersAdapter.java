package net.larntech.retrofit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.retrofit.R;
import net.larntech.retrofit.model.response.AllUsersResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.PendingConsumerAdapterVh> implements Filterable {

    private List<AllUsersResponse.UsersBean> userBeanList;
    private List<AllUsersResponse.UsersBean> getUserModelListFiltered;
    private Context context;
    private SelectedConsumer selectedConsumer;

    public AllUsersAdapter(SelectedConsumer selectedConsumer) {
        super();
        this.selectedConsumer = selectedConsumer;
    }

    @NonNull
    @Override
    public PendingConsumerAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PendingConsumerAdapterVh(LayoutInflater.from(context).inflate(R.layout.users_list,null));
    }

    public void setItems(List<AllUsersResponse.UsersBean> list) {
        this.userBeanList = list;
        this.getUserModelListFiltered = userBeanList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull PendingConsumerAdapterVh holder, int position) {

        AllUsersResponse.UsersBean usersBean = userBeanList.get(position);

        String userName = "UserName : "+usersBean.getUsername();
        String expiryDate = "Expiry Date : "+usersBean.getExpiry();
        String prefix = usersBean.getUsername().substring(0,1);


        holder.tvUsername.setText(userName);
        holder.tvExpiryDate.setText(expiryDate);
        holder.tvPrefix.setText(prefix);
        holder.rvPrefix.setBackground(generateRandomColor());

        holder.itemView.setOnClickListener(view -> selectedConsumer.selectedAllConsumers(usersBean));




    }





    public GradientDrawable generateRandomColor(){
        Random r = new Random();
        int red= r.nextInt(255 + 1);
        int green= r.nextInt(255 + 1);
        int blue= r.nextInt(255 + 1);

        GradientDrawable draw = new GradientDrawable();
        draw.setShape(GradientDrawable.OVAL);
        draw.setColor(Color.rgb(red,green,blue));
        return draw;
    }


    @Override
    public int getItemCount() {
        if(userBeanList != null && userBeanList.size() > 0) {
            return userBeanList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0){
                    filterResults.count = getUserModelListFiltered.size();
                    filterResults.values = getUserModelListFiltered;

                }else{
                    String searchChr = charSequence.toString().toLowerCase();

                    List<AllUsersResponse.UsersBean> resultData = new ArrayList<>();

                    for(AllUsersResponse.UsersBean userModel: getUserModelListFiltered){
                        if(userModel.getUsername().toLowerCase().contains(searchChr)){
                            resultData.add(userModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                userBeanList = (List<AllUsersResponse.UsersBean>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public interface SelectedConsumer{
        void selectedAllConsumers(AllUsersResponse.UsersBean usersBean);
    }

    public class PendingConsumerAdapterVh extends RecyclerView.ViewHolder {

        TextView tvUsername;
        TextView tvExpiryDate;
        TextView tvPrefix;
        RelativeLayout rvPrefix;


        public PendingConsumerAdapterVh(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUserName);
            tvExpiryDate = itemView.findViewById(R.id.tvExpiryDate);
            tvPrefix = itemView.findViewById(R.id.prefix);
            rvPrefix = itemView.findViewById(R.id.rvImaged);

        }
    }
}