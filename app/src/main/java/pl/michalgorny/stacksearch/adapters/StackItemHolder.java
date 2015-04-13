package pl.michalgorny.stacksearch.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skocken.efficientadapter.lib.viewholder.AbsViewHolder;
import com.squareup.picasso.Picasso;

import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.pojos.StackItem;

/**
 *  ViewHolder for RecyclerView.Adapter. It is used by SimpleAdapter.
 */
public class StackItemHolder extends AbsViewHolder<StackItem>{

    public StackItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void updateView(Context context, StackItem stackItem) {
        TextView name = findViewByIdEfficient(R.id.result_list_item_name);
        TextView question = findViewByIdEfficient(R.id.result_list_item_question);
        TextView answersCount = findViewByIdEfficient(R.id.result_list_item_answers_count);
        ImageView avatar = findViewByIdEfficient(R.id.result_list_item_avatar);

        name.setText(stackItem.getOwner().getDisplayName());
        question.setText(stackItem.getTitle());
        answersCount.setText(getContext().getString(R.string.answers_count_text) + stackItem.getAnswerCount().toString());
        Picasso.with(getContext())
                .load(stackItem.getOwner().getProfileImage())
                .resize(300, 300)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(avatar);
    }
}
