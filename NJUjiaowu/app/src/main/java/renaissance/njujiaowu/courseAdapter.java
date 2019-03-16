package renaissance.njujiaowu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class courseAdapter extends BaseAdapter {

    List<Map.Entry<String,String>> courses;
    Context context;

    public courseAdapter(List<Map.Entry<String, String>> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {

        return courses.get(position).getKey();
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        courseAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new courseAdapter.ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.course_item,parent,false);
            viewHolder.tv=(TextView)convertView.findViewById(R.id.textView1);
//            viewHolder.tv.setText(courses.get(position).getKey());
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (courseAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(courses.get(position).getKey());

//        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        viewHolder.tv_name.setText(toDos.get(position).getKey());
//        String[] info = toDos.get(position).getValue().split("\\|");
//        viewHolder.tv_content.setText(info[0]);
//        viewHolder.tv_ddl.setText(info[1]);
//        viewHolder.bt_finished.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("homeWorkItem",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                String key = toDos.get(position).getKey();
//                toDos.remove(position);
//                notifyDataSetChanged();
//                editor.remove(key);
//                editor.commit();
//            }
//        });
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
