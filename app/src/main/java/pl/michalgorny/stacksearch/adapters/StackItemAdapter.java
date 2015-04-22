package pl.michalgorny.stacksearch.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.pojos.StackItem;
import pl.michalgorny.stacksearch.ui.DetailsActivity;

import static pl.michalgorny.stacksearch.constants.Constants.WEB_URL_LINK;

/**
 *  Adapter for UltimateRecyclerView. Provides a binding from StackItem data to a result item view
 */
public class StackItemAdapter extends UltimateViewAdapter {
    private final List<StackItem> mListItems;

    public StackItemAdapter(List<StackItem> listItems) {
        mListItems = listItems;
    }

    @Override
    public StackItemViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.result_list_item, viewGroup, false);
        return new StackItemViewHolder(itemView);
    }

    @Override
    public int getAdapterItemCount() {
        return mListItems.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StackItemAdapter.StackItemViewHolder itemHolder = (StackItemAdapter.StackItemViewHolder)holder;
        StackItem stackItem = mListItems.get(position);

        itemHolder.link = stackItem.getLink();
        itemHolder.name.setText(stackItem.getOwner().getDisplayName());
        itemHolder.question.setText(stackItem.getTitle());
        itemHolder.answersCount.setText(itemHolder.answersCount.getContext().
                getString(R.string.answers_count_text) + stackItem.getAnswerCount().toString());
        Picasso.with(itemHolder.avatar.getContext())
                .load(stackItem.getOwner().getProfileImage())
                .resize(300, 300)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(itemHolder.avatar);
    }

    public static class StackItemViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.result_list_item_name) TextView name;
        @InjectView(R.id.result_list_item_question) TextView question;
        @InjectView(R.id.result_list_item_answers_count) TextView answersCount;
        @InjectView(R.id.result_list_item_avatar) ImageView avatar;

        String link;

        public StackItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), DetailsActivity.class);
                    i.putExtra(WEB_URL_LINK, link);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
