package renaissance.njujiaowu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class GradeAdapter extends BaseAdapter {

    List<Map.Entry<String,String>> grades;
    Context context;

    public GradeAdapter(List<Map.Entry<String, String>> grades, Context context) {
        this.grades = grades;
        this.context = context;
    }

    @Override
    public int getCount() {
        return grades.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GradeAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new GradeAdapter.ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.grade_item,parent,false);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_courseName);
            viewHolder.tv_score = (TextView)convertView.findViewById(R.id.tv_courseScore) ;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (GradeAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(grades.get(position).getKey());
        viewHolder.tv_score.setText(grades.get(position).getValue());
//        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder{
        TextView tv_name,tv_score;
    }
}
