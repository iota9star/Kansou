package star.iota.kansou;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;


class ThreeViewHolder extends BaseViewHolder {
    @BindView(R.id.text_view_first_column)
    TextView mTextViewFirstColumn;
    @BindView(R.id.text_view_second_column)
    TextView mTextViewSecondColumn;
    @BindView(R.id.text_view_third_column)
    TextView mTextViewThirdColumn;

    ThreeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bindView(Bean.ItemBean bean) {
        mTextViewFirstColumn.setText(Html.fromHtml(bean.getFirstColumn()));
        mTextViewSecondColumn.setText(Html.fromHtml(bean.getSecondColumn()));
        mTextViewThirdColumn.setText(Html.fromHtml(bean.getThirdColumn()));
        mTextViewSecondColumn.setMovementMethod(LinkMovementMethod.getInstance());
        mTextViewThirdColumn.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
