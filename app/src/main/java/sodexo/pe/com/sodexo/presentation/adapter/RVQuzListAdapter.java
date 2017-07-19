package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.QuizView;

/**
 * Created by ronaldvelasquez on 13/12/16.
 */

public class RVQuzListAdapter extends RecyclerView.Adapter<RVQuzListAdapter.VHQuizList> {

    private List<QuizEntity> list;
    private QuizView quizView;
    private Context context;

    public RVQuzListAdapter(QuizView view, Context context) {
        this.list = new ArrayList<>();
        this.quizView = view;
        this.context = context;
    }

    @Override
    public VHQuizList onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHQuizList(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, null));
    }

    @Override
    public void onBindViewHolder(VHQuizList holder, int position) {
        final QuizEntity quizEntity = list.get(position);
        int visibility = quizEntity.isCompanyQuiz()?View.INVISIBLE:View.VISIBLE;
        holder.star.setVisibility(visibility);
        holder.tvTitle.setText(quizEntity.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizEntity.isQuizResolved()) {
                    Toast.makeText(context, "Esta encuesta ya fue resuelta. Por favor seleccione otra.", Toast.LENGTH_SHORT).show();
                } else {
                    quizView.showQuizDetail(quizEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<QuizEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class VHQuizList extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.no_company_star)
        ImageView star;
        public VHQuizList(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
